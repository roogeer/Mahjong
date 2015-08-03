package com.roger.mahjong;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

public class PlayerInfo {
	//�ڲ��࣬���ڼ�¼�߶ε������������ɫ
	public class MyLine
	{
		public Point P0;
		public Point P1;
		public int color;
		
		public MyLine(Point p0, Point p1, int color)
		{
			this.P0 = p0;
			this.P1 = p1;
			this.color = color;
		}
	}	
	
	public String Name;								//�������
	public ArrayList<Integer> lstRecordPerRound;	//��¼��Ϸ��ÿһ�ѵ���Ӯ���
	public ArrayList<Integer> lstRecordPerSum;		//��¼��Ϸ���ۼ���Ӯ���
	public int LoseWin;								//��¼��Ϸ�ߵ�ǰ����Ӯ���
	public int JDS;									//�����Ľ���
	public int Rounds;								//��Ϸ�ܾ���
	public int LoseValue;							//����������ֵ
	public int LoseRound;							//��С��ֵ���ֵľ���
	public int LoseNumber;							//��ľ���
	public int WinValue;							//Ӯ�õ������ֵ
	public int WinRound;							//�����ֵ���ֵľ���
	public int WinNumber;							//Ӯ�ľ���
	public ArrayList<MyLine> lstLinesTemp;			//��Ҫ����Ļ�ϳ��ֵĵ㣬����0ֵ��Խ�ж�
	public ArrayList<MyLine> lstLines;				//��Ҫ����Ļ�ϳ��ֵĵ�
	private int ScreenWidth;						//��Ļ�����x������ֵ
	public int ScreenYZero;							//��Ļ0ֵ���y������
	
	public PlayerInfo(String aName, ArrayList<Integer> aLstRecordPerRound, int aJDS, int aRounds)
	{
		this.Name = aName;
		this.lstRecordPerRound = aLstRecordPerRound;
		this.JDS = aJDS;
		this.Rounds = aRounds;
	}
	
	public PlayerInfo()
	{
		this.Name="";
		this.lstRecordPerRound = new ArrayList<Integer>();
		this.lstRecordPerSum = new ArrayList<Integer>();
		this.LoseWin = 0;
		this.JDS = 0;
		this.Rounds=0;
		this.LoseValue=0;
		this.LoseRound=0;
		this.LoseNumber=0;		
		this.WinValue=0;
		this.WinRound=0;
		this.WinNumber=0;
		this.lstLinesTemp = new ArrayList<PlayerInfo.MyLine>();
		this.lstLines = new ArrayList<PlayerInfo.MyLine>();
	}

	public void Update4LostWin()
	{
		//����ÿһ����ɺ�ĵ�ǰ��Ӯ���
		this.lstRecordPerSum.clear();
		int sum=0;
		for (int x : this.lstRecordPerRound) {
			sum += x;
			this.lstRecordPerSum.add(sum);
		}
	}
	
	public void Prepare4Draw(int aScreenWidth)
	{
		//����2�֣�ֱ�ӷ��أ�û���߶ο�������
		if(Rounds < 2)
			return;
		
		this.ScreenWidth = aScreenWidth;
		/*
		WinValue=lstRecordPerSum.get(0);
		WinRound=1;
		LostValue=lstRecordPerSum.get(0);
		LostRound=1;
		*/
		WinValue = 0;
		LoseValue = 0;
		WinRound = 0;
		LoseRound = 0;

		//for(int i=1; i < lstRecordPerSum.size(); i++)
		for(int i=0; i < lstRecordPerSum.size(); i++)
		{
			int x = lstRecordPerSum.get(i);
			if(x > WinValue)
			{
				WinValue = x;
				WinRound = i + 1;
			}
			if(x < LoseValue)
			{
				LoseValue = x;
				LoseRound = i + 1;
			}
		}
		
		//׼�������߶���Ϣ��ÿһ�׶β��ж��Ƿ�Խ0ֵ��
		lstLinesTemp.clear();
		for(int i=1; i<lstRecordPerSum.size(); i++)
		{
			lstLinesTemp.add(CalculateLine(i+1, lstRecordPerSum.get(i-1), lstRecordPerSum.get(i)));
		}
		
		//�ڶ��׶Σ��ж��Ƿ�Խ0ֵ�㣬�����Խ����Ҫ��ֳ�2��
		CalculateLine();
	}
	
	private void CalculateLine()
	{
		//�����0ֵ��
		int zeroy = 0;
		if(WinValue<0)
		{
			zeroy = 0;
		}
		else if (LoseValue>0)
		{
			zeroy = ScreenWidth;
		}
		else
		{
			zeroy = (int)(ScreenWidth*100/(WinValue-LoseValue) * Math.abs((0-WinValue))/100);
		}
		
		//Log.i("roger", "WinValue"+String.valueOf(WinValue));
		//Log.i("roger", "LostValue"+String.valueOf(LoseValue));
		ScreenYZero = zeroy;
		int zerox = 0;
		//ȡ�߶ε���������ж��Ƿ�Խ��0ֵ��
		int x0;
		int y0;
		int x1;
		int y1;
		float k;
		int b;
		
		int count =1;
		lstLines.clear();
		
		for (MyLine tempLine : lstLinesTemp)
		{
			x0 = tempLine.P0.x;
			y0 = tempLine.P0.y;
			x1 = tempLine.P1.x;
			y1 = tempLine.P1.y;
			
			//Log.i("roger", "count:"+String.valueOf(count++));
			//Log.i("roger", "x0:"+String.valueOf(x0));
			//Log.i("roger", "x1:"+String.valueOf(x1));
			//Log.i("roger", "y0:"+String.valueOf(y0));
			//Log.i("roger", "y1:"+String.valueOf(y1));
			//Log.i("roger", "zeroy:"+String.valueOf(zeroy));

			//y=kx+b
			if( (y0 < zeroy) && (y1> zeroy) )
			{
				//����ֵ��Խ����ֵ��
				k=(float)((y1 - y0)*100.0/(x1-x0)/100.0);
				//Log.i("roger", "k:"+String.valueOf(k));
				b=(int)(y1 - k * x1);
				//Log.i("roger", "b:"+String.valueOf(b));
				zerox =(int)((zeroy - b)*100.0/k/100.0);
				//Log.i("roger", "zerox:"+String.valueOf(zerox));
				lstLines.add(new MyLine(new Point(x0, y0), new Point(zerox,zeroy), Color.RED));
				lstLines.add(new MyLine(new Point(zerox, zeroy), new Point(x1,y1), Color.GREEN));
			}
			else if( (y0 > zeroy) && (y1 < zeroy) )
			{
				//�Ӹ�ֵ��Խ����ֵ��
				k=(float)((y1 - y0)*100.0/(x1-x0)/100.0);
				//Log.i("roger", "k:"+String.valueOf(k));				
				b=(int)(y1 - k * x1);
				//Log.i("roger", "b:"+String.valueOf(b));				
				zerox =(int)((zeroy - b)*100/k/100);
				//Log.i("roger", "zerox:"+String.valueOf(zerox));				
				lstLines.add(new MyLine(new Point(x0, y0), new Point(zerox,zeroy), Color.GREEN));
				lstLines.add(new MyLine(new Point(zerox, zeroy), new Point(x1,y1), Color.RED));				
			}
			else
			{
				if(y0 < zeroy || y1 < zeroy)
				{
					lstLines.add(new MyLine(new Point(x0,y0), new Point(x1,y1), Color.RED));
				}
				else if(y0 > zeroy || y1 > zeroy)
				{
					lstLines.add(new MyLine(new Point(x0,y0), new Point(x1,y1), Color.GREEN));
				}
				else
				{
					lstLines.add(new MyLine(new Point(x0,y0), new Point(x1,y1), Color.WHITE));
				}
			}
		}
	}
	
	private MyLine CalculateLine(int SN, int ValueA, int ValueB)
	{
		/*
		Log.i("roger", "ValueA"+String.valueOf(ValueA));
		Log.i("roger", "ValueB"+String.valueOf(ValueB));
		Log.i("roger", "WinValue"+String.valueOf(WinValue));
		Log.i("roger", "LostValue"+String.valueOf(LostValue));
		Log.i("roger", "ScreenWidth"+String.valueOf(ScreenWidth));
		Log.i("roger", "Rounds"+String.valueOf(this.Rounds));
		*/
		int beginPointSn= SN -1;
		//Log.i("roger", "beginPointSn"+String.valueOf(beginPointSn));
		int endPointSn = SN;
		//Log.i("roger", "endPointSn"+String.valueOf(endPointSn));
		int x0 = (int)((beginPointSn-1) * (ScreenWidth*100/(this.Rounds-1))/100);
		//Log.i("roger", "x0"+String.valueOf(x0));
		int x1 = (int)((endPointSn-1) * (ScreenWidth*100/(this.Rounds-1))/100);
		//Log.i("roger", "x1"+String.valueOf(x1));
		int y0 = (int)(ScreenWidth*100/(WinValue-LoseValue) * Math.abs((ValueA-WinValue))/100);
		//Log.i("roger", "y0"+String.valueOf(y0));
		int y1 = (int)(ScreenWidth*100/(WinValue-LoseValue) * Math.abs((ValueB-WinValue))/100);
		//Log.i("roger", "y1"+String.valueOf(y1));
		return new MyLine(new Point(x0, y0), new Point(x1, y1), Color.RED);
	}
}
