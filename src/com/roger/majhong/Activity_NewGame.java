package com.roger.majhong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.roger.majhong.DialogFragment_Add.AddInputListener;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Activity_NewGame extends Activity implements AddInputListener {
	//20150217�����PlayerInfo��
	public PlayerInfo PlayerInfoP1;
	public PlayerInfo PlayerInfoP2;
	public PlayerInfo PlayerInfoP3;
	public PlayerInfo PlayerInfoP4;
	//*************************
	
	
	//public String playerOneName;
	//public String playerTwoName;
	//public String playerThreeName;
	//public String playerFourName;
	public int JDS;
	
	//��Ӽ�¼���޸����±���
	public String p1Value;
	public String p2Value;
	public String p3Value;
	public String p4Value;
	public String strDetail;

	//4λ���ÿ�ֵ���Ӯ��ֵ
	private int intP1;
	private int intP2;
	private int intP3;
	private int intP4;
	
	//4λ����ۼ���Ӯ��ֵ
	//public int intP1Sum;
	//public int intP2Sum;
	//public int intP3Sum;
	//public int intP4Sum;
	
	//4λ����ۼƽ�����
	//public int intP1SumJds;
	//public int intP2SumJds;
	//public int intP3SumJds;
	//public int intP4SumJds;
	
	int InputFlag;	//��¼�������ݵı�־λ����ȷ�������Ƿ���Ч����˭��˭Ӯ
	boolean InputOk;
	//public int intP1Data;	//���Խ׶�ʹ��
	
	//fragmentʵ��
	Fragment_Main fragment_Main;
	Fragment_P1 fragment_P1;
	Fragment_P2 fragment_P2;
	Fragment_P3 fragment_P3;
	Fragment_P4 fragment_P4;
	Fragment_Detail fragment_Detail;
	int FragmentFlag;	//���浱ǰ��ʾ�����ĸ�Fragment����Ϊ�²ߣ��ҵ����ŵķ������滻
	
	ArrayList<HashMap<String, String>> mylist;
	int SN;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newgame);
		
		Intent it = this.getIntent();
		Bundle bundle = it.getExtras();
		
		String GameFlag = bundle.getString("gameflag");
		
		if(GameFlag.equalsIgnoreCase("newgame"))
		{
			PlayerInfoP1 = new PlayerInfo();
			PlayerInfoP2 = new PlayerInfo();
			PlayerInfoP3 = new PlayerInfo();
			PlayerInfoP4 = new PlayerInfo();
			
			//playerOneName = bundle.getString("playeronename");
			PlayerInfoP1.Name=bundle.getString("playeronename");
			
			//playerTwoName = bundle.getString("playertwoname");
			PlayerInfoP2.Name=bundle.getString("playertwoname");
			
			//playerThreeName = bundle.getString("playerthreename");
			PlayerInfoP3.Name=bundle.getString("playerthreename");
			
			//playerFourName = bundle.getString("playerfourname");
			PlayerInfoP4.Name= bundle.getString("playerfourname");
			JDS = bundle.getInt("jds");
			//intP1Data = 1;
			
			mylist = new ArrayList<HashMap<String,String>>();
			SN = 1;
			PlayerInfoP1.Rounds = 0;
			PlayerInfoP2.Rounds = 0;
			PlayerInfoP3.Rounds = 0;
			PlayerInfoP4.Rounds = 0;			
			//intP1Sum = 0;
			//intP2Sum = 0;
			//intP3Sum = 0;
			//intP4Sum = 0;
			
			//intP1SumJds = 0;
			//intP2SumJds = 0;
			//intP3SumJds = 0;
			//intP4SumJds = 0;				
		}
		else if(GameFlag.equalsIgnoreCase("goon"))
		{
			PlayerInfoP1 = new PlayerInfo();
			PlayerInfoP2 = new PlayerInfo();
			PlayerInfoP3 = new PlayerInfo();
			PlayerInfoP4 = new PlayerInfo();
			
			//playerOneName = bundle.getString("playeronename");
			//playerTwoName = bundle.getString("playertwoname");
			//playerThreeName = bundle.getString("playerthreename");
			//playerFourName = bundle.getString("playerfourname");			
			PlayerInfoP1.Name=bundle.getString("playeronename");
			PlayerInfoP2.Name=bundle.getString("playertwoname");
			PlayerInfoP3.Name=bundle.getString("playerthreename");
			PlayerInfoP4.Name= bundle.getString("playerfourname");
			

			JDS = bundle.getInt("jds");
			//intP1Data = 1;
			
			mylist = new ArrayList<HashMap<String,String>>();
			mylist = (ArrayList<HashMap<String,String>>)bundle.getSerializable("xlist");
			
			//20150217��ȡÿһ�ֵļ�¼��������ұ�����
			for (HashMap<String, String> hashMap: mylist)
			{
				PlayerInfoP1.lstRecordPerRound.add(Integer.valueOf(hashMap.get("playerOneData")));
				PlayerInfoP2.lstRecordPerRound.add(Integer.valueOf(hashMap.get("playerTwoData")));
				PlayerInfoP3.lstRecordPerRound.add(Integer.valueOf(hashMap.get("playerThreeData")));
				PlayerInfoP4.lstRecordPerRound.add(Integer.valueOf(hashMap.get("playerFourData")));				
			}
			PlayerInfoP1.Update4LostWin();
			PlayerInfoP2.Update4LostWin();
			PlayerInfoP3.Update4LostWin();
			PlayerInfoP4.Update4LostWin();
			String str="�õļ�����"+PlayerInfoP1.Name+String.valueOf(PlayerInfoP1.LostWin)+PlayerInfoP2.Name+String.valueOf(PlayerInfoP2.LostWin)+PlayerInfoP3.Name+String.valueOf(PlayerInfoP3.LostWin)+PlayerInfoP4.Name+String.valueOf(PlayerInfoP4.LostWin)+"\n"+String.valueOf(mylist.size());
			Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();			
			//**************************************
			
			SN = mylist.size();
			PlayerInfoP1.Rounds = SN;
			PlayerInfoP2.Rounds = SN;
			PlayerInfoP3.Rounds = SN;
			PlayerInfoP4.Rounds = SN;
			SN++;
			//intP1Sum = bundle.getInt("p1sum");
			//intP2Sum = bundle.getInt("p2sum");
			//intP3Sum = bundle.getInt("p3sum");
			//intP4Sum = bundle.getInt("p4sum");
			PlayerInfoP1.LostWin = bundle.getInt("p1sum");
			PlayerInfoP2.LostWin = bundle.getInt("p2sum");
			PlayerInfoP3.LostWin = bundle.getInt("p3sum");
			PlayerInfoP4.LostWin = bundle.getInt("p4sum");			
			
			//intP1SumJds = bundle.getInt("playeronejds");
			//intP2SumJds = bundle.getInt("playertwojds");
			//intP3SumJds = bundle.getInt("playerthreejds");
			//intP4SumJds = bundle.getInt("playerfourjds");
			PlayerInfoP1.JDS = bundle.getInt("playeronejds");
			PlayerInfoP2.JDS = bundle.getInt("playertwojds");
			PlayerInfoP3.JDS = bundle.getInt("playerthreejds");
			PlayerInfoP4.JDS = bundle.getInt("playerfourjds");			
		}
		
		//this.setTitle("New Game:"+playerOneName+" "+playerTwoName+" "+playerThreeName+" "+playerFourName+" "+ String.valueOf(JDS));
		
		ActionBar actionBar = getActionBar();
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		actionBar.setDisplayShowTitleEnabled(false);
		
		Tab Tab_Main = actionBar.newTab().setText("��");
		//Tab Tab_P1 = actionBar.newTab().setText(playerOneName);
		Tab Tab_P1 = actionBar.newTab().setText(PlayerInfoP1.Name);
		
		Tab Tab_P2 = actionBar.newTab().setText(PlayerInfoP2.Name);
		
		Tab Tab_P3 = actionBar.newTab().setText(PlayerInfoP3.Name);
		
		Tab Tab_P4 = actionBar.newTab().setText(PlayerInfoP4.Name);
		
		Tab Tab_Detail = actionBar.newTab().setText("��");

		fragment_Main = new Fragment_Main();
		MyTabListener mainListener=new MyTabListener(fragment_Main);
		Tab_Main.setTabListener(mainListener);
		
		fragment_P1 = new Fragment_P1();
		MyTabListener AListener=new MyTabListener(fragment_P1);
		Tab_P1.setTabListener(AListener);

		fragment_P2 = new Fragment_P2();
		MyTabListener BListener=new MyTabListener(fragment_P2);
		Tab_P2.setTabListener(BListener);

		fragment_P3 = new Fragment_P3();		
		MyTabListener CListener=new MyTabListener(fragment_P3);
		Tab_P3.setTabListener(CListener);
		
		fragment_P4 = new Fragment_P4();		
		MyTabListener DListener=new MyTabListener(fragment_P4);
		Tab_P4.setTabListener(DListener);
		
		fragment_Detail = new Fragment_Detail();
		MyTabListener detailListener=new MyTabListener(fragment_Detail);
		Tab_Detail.setTabListener(detailListener);		
		
		actionBar.addTab(Tab_Main);
		actionBar.addTab(Tab_P1);
		actionBar.addTab(Tab_P2);
		actionBar.addTab(Tab_P3);
		actionBar.addTab(Tab_P4);
		actionBar.addTab(Tab_Detail);
		
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu4newgame_activity, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_add:
			Toast.makeText(getApplicationContext(), "��Ӽ�¼", Toast.LENGTH_SHORT).show();
			DialogFragment_Add dialogFragment_Add = new DialogFragment_Add();
			dialogFragment_Add.show(getFragmentManager(), "��Ӽ�¼");
			return true;
		case R.id.action_exit:
			Toast.makeText(getApplicationContext(), "�˳�Ӧ��", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);			
		}
	}
	
	@Override
	public void onAddInputComplete(String p1Vlaue, String p2Vlaue,
			String p3Vlaue, String p4Value) {
		// TODO Auto-generated method stub
		this.p1Value = p1Vlaue;
		this.p2Value = p2Vlaue;
		this.p3Value = p3Vlaue;
		this.p4Value = p4Value;
		
		//���ݷ��ص��ַ�����InputFlagλ��ֵ
		InputFlag = 0;
		InputOk = false;
		if(this.p1Value.equalsIgnoreCase("-"))
			InputFlag = InputFlag | 0x00;
		else if(this.p1Value.equalsIgnoreCase("0"))
			InputFlag = InputFlag | 0x10;
		else InputFlag = InputFlag | 0x11;
		
		if(this.p2Value.equalsIgnoreCase("-"))
			InputFlag = InputFlag | 0x00;
		else if(this.p2Value.equalsIgnoreCase("0"))
			InputFlag = InputFlag | 0x20;
		else InputFlag = InputFlag | 0x22;
		
		if(this.p3Value.equalsIgnoreCase("-"))
			InputFlag = InputFlag | 0x00;
		else if(this.p3Value.equalsIgnoreCase("0"))
			InputFlag = InputFlag | 0x40;
		else InputFlag = InputFlag | 0x44;
		
		if(this.p4Value.equalsIgnoreCase("-"))
			InputFlag = InputFlag | 0x00;
		else if(this.p4Value.equalsIgnoreCase("0"))
			InputFlag = InputFlag | 0x80;
		else InputFlag = InputFlag | 0x88;
		
		
		intP1 = 0;
		intP2 = 0;
		intP3 = 0;
		intP4 = 0;

		switch(InputFlag)
		{
			case 238:	//p1Ӯ
			{
				intP2 = Integer.valueOf(this.p2Value) * (-1);
				intP3 = Integer.valueOf(this.p3Value) * (-1);
				intP4 = Integer.valueOf(this.p4Value) * (-1);				
				intP1 = (intP2 + intP3 + intP4) * (-1);
				
				//����ÿλ��ҵ���Ӯ���
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				
				//����ÿλ��ҵĽ�����
				if(Math.abs(intP2)>=JDS && Math.abs(intP3)>=JDS && Math.abs(intP4)>=JDS)
					//intP1SumJds++;
					PlayerInfoP1.JDS++;
				
				InputOk = true;
				break;
			}
			case 221:	//p2Ӯ
			{
				intP1 = Integer.valueOf(this.p1Value) * (-1);
				intP3 = Integer.valueOf(this.p3Value) * (-1);
				intP4 = Integer.valueOf(this.p4Value) * (-1);				
				intP2 = (intP1 + intP3 + intP4) * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				
				//����ÿλ��ҵĽ�����
				if(Math.abs(intP1)>=JDS && Math.abs(intP3)>=JDS && Math.abs(intP4)>=JDS)
					//intP2SumJds++;
					PlayerInfoP2.JDS++;
				
				InputOk = true;				
				break;
			}
			case 187:	//p3Ӯ
			{
				intP1 = Integer.valueOf(this.p1Value) * (-1);
				intP2 = Integer.valueOf(this.p2Value) * (-1);
				intP4 = Integer.valueOf(this.p4Value) * (-1);				
				intP3 = (intP1 + intP2 + intP4) * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				
				//����ÿλ��ҵĽ�����
				if(Math.abs(intP1)>=JDS && Math.abs(intP2)>=JDS && Math.abs(intP4)>=JDS)
					//intP3SumJds++;
					PlayerInfoP3.JDS++;
				
				InputOk = true;				
				break;
			}
			case 119:	//p4Ӯ
			{
				intP1 = Integer.valueOf(this.p1Value) * (-1);
				intP2 = Integer.valueOf(this.p2Value) * (-1);
				intP3 = Integer.valueOf(this.p3Value) * (-1);				
				intP4 = (intP1 + intP2 + intP3) * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				
				//����ÿλ��ҵĽ�����
				if(Math.abs(intP1)>=JDS && Math.abs(intP2)>=JDS && Math.abs(intP3)>=JDS)
					//intP4SumJds++;
					PlayerInfoP4.JDS++;
				
				InputOk = true;				
				break;
			}
			case 50:	//p2��p1
			{
				intP2 = Integer.valueOf(this.p2Value) * (-1);
				intP3 = 0;				
				intP4 = 0;
				intP1 = intP2 * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;				
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				
				InputOk = true;				
				break;
			}
			case 84:	//p3��p1
			{
				intP2 = 0;				
				intP3 = Integer.valueOf(this.p3Value) * (-1);
				intP4 = 0;
				intP1 = intP3 * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;				
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;
				
				InputOk = true;
				break;				
			}
			case 152:	//p4��p1
			{
				intP2 = 0;				
				intP3 = 0;
				intP4 = Integer.valueOf(this.p4Value) * (-1);
				intP1 = intP4 * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;				
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				InputOk = true;				
				break;				
			}
			case 49:	//p1��p2
			{
				intP1 = Integer.valueOf(this.p1Value) * (-1);				
				intP3 = 0;
				intP4 = 0;
				intP2 = intP1 * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;				
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				InputOk = true;
				break;				
			}
			case 100:	//p3��p2
			{
				intP1 = 0;				
				intP3 = Integer.valueOf(this.p3Value) * (-1);
				intP4 = 0;
				intP2 = intP3 * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;				
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				InputOk = true;				
				break;				
			}			
			case 168:	//p4��p2
			{
				intP1 = 0;				
				intP3 = 0;
				intP4 = Integer.valueOf(this.p4Value) * (-1);
				intP2 = intP4 * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				InputOk = true;				
				break;				
			}			
			case 81:	//p1��p3
			{
				intP1 = Integer.valueOf(this.p1Value) * (-1);				
				intP2 = 0;
				intP4 = 0;
				intP3 = intP1 * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				InputOk = true;				
				break;				
			}
			case 98:	//p2��p3
			{
				intP1 = 0;				
				intP2 = Integer.valueOf(this.p2Value) * (-1);
				intP4 = 0;
				intP3 = intP2 * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				InputOk = true;				
				break;				
			}			
			case 200:	//p4��p3
			{
				intP1 = 0;				
				intP2 = 0;
				intP4 = Integer.valueOf(this.p4Value) * (-1);
				intP3 = intP4 * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;				
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				InputOk = true;				
				break;
			}
			case 145:	//p1��p4
			{
				intP1 = Integer.valueOf(this.p1Value) * (-1);				
				intP2 = 0;
				intP3 = 0;
				intP4 = intP1 * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				InputOk = true;
				break;
			}			
			case 162:	//p2��p4
			{
				intP1 = 0;				
				intP2 = Integer.valueOf(this.p2Value) * (-1);
				intP3 = 0;
				intP4 = intP2 * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				InputOk = true;				
				break;
			}			
			case 196:	//p3��p4
			{
				intP1 = 0;				
				intP2 = 0;
				intP3 = Integer.valueOf(this.p3Value) * (-1);
				intP4 = intP3 * (-1);
				
				//intP1Sum += intP1;
				//intP2Sum += intP2;
				//intP3Sum += intP3;
				//intP4Sum += intP4;
				PlayerInfoP1.LostWin += intP1;
				PlayerInfoP2.LostWin += intP2;
				PlayerInfoP3.LostWin += intP3;
				PlayerInfoP4.LostWin += intP4;				
				InputOk = true;				
				break;
			}
		}
		
		//this.strDetail = this.playerOneName+p1Vlaue+";"+this.playerTwoName+p2Vlaue+";"+this.playerThreeName+p3Vlaue+";"+this.playerFourName+p4Value;
		//this.strDetail += "\n�����־Ϊ��"+String.valueOf(InputFlag);
		//this.strDetail += "\n"+this.playerOneName+String.valueOf(intP1)+";"+this.playerTwoName+String.valueOf(intP2)+";"+this.playerThreeName+String.valueOf(intP3)+";"+this.playerFourName+String.valueOf(intP4);
		//Toast.makeText(this, strDetail, Toast.LENGTH_SHORT).show();
		if(InputOk)	//�����������Ч
		{
			//20150217������˼�¼��
			PlayerInfoP1.lstRecordPerRound.add(intP1);
			PlayerInfoP2.lstRecordPerRound.add(intP2);
			PlayerInfoP3.lstRecordPerRound.add(intP3);
			PlayerInfoP4.lstRecordPerRound.add(intP4);
			PlayerInfoP1.Rounds=PlayerInfoP1.lstRecordPerRound.size();
			PlayerInfoP2.Rounds=PlayerInfoP2.lstRecordPerRound.size();
			PlayerInfoP3.Rounds=PlayerInfoP3.lstRecordPerRound.size();
			PlayerInfoP4.Rounds=PlayerInfoP4.lstRecordPerRound.size();
			PlayerInfoP1.Update4LostWin();
			PlayerInfoP2.Update4LostWin();
			PlayerInfoP3.Update4LostWin();
			PlayerInfoP4.Update4LostWin();
			//*********************
			
			//����ArrayList�����ݿ�
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("SN", Utility.FormatData1To9(String.valueOf(SN)));
			map.put("playerOneData", String.valueOf(intP1));
			map.put("playerTwoData", String.valueOf(intP2));
			map.put("playerThreeData", String.valueOf(intP3));
			map.put("playerFourData", String.valueOf(intP4));
			//ȡʱ��
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
			Date curDate = new Date(System.currentTimeMillis());
			Log.i("roger", formatter.format(curDate));
			map.put("timeData", formatter.format(curDate));
			mylist.add(map);
			
			//�������ݿ���
			writeInDatabase(intP1, intP2, intP3, intP4, formatter.format(curDate));
			
			SN++;
		}
		switch(FragmentFlag)
		{
		case 0:
			fragment_Main.updata();
			break;
		case 1:
			fragment_P1.updata();
			break;
		case 2:
			//fragment_P2.updata();
			break;
		case 3:
			//fragment_P3.updata();
			break;
		case 4:
			//fragment_P4.updata();
			break;
		case 5:
			//fragment_Detail.updata();
			break;			
		}
	}
	
	/*
	private String formatData(String numStr)
	{
		String result="";
		int num =Math.abs(Integer.parseInt(numStr));

		if(num < 10)
		{
			result+="0";
		}
		
		result+=String.valueOf(num);
		
		return result;
	}
	*/	

	private void writeInDatabase(int p1data, int p2data, int p3data, int p4data, String shijian)
	{
		MajhongDatabaseHelper dbHelper = new MajhongDatabaseHelper(this, "majhong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		//���ÿһ�ֵļ�¼
		db.execSQL("insert into GameRec_LastDetail(p1data, p2data, p3data, p4data, shijian) values(?,?,?,?,?)", new Object[]{p1data,p2data,p3data,p4data,shijian});
		
		//������ҽ���
		//String str ="update gameRec_lastinfo set p1jds="+String.valueOf(intP1SumJds)+", p2jds="+String.valueOf(intP2SumJds)+", p3jds="+String.valueOf(intP3SumJds)+", p4jds="+String.valueOf(intP4SumJds)+" where _id=1";
		String str ="update gameRec_lastinfo set p1jds="+String.valueOf(PlayerInfoP1.JDS)+", p2jds="+String.valueOf(PlayerInfoP2.JDS)+", p3jds="+String.valueOf(PlayerInfoP3.JDS)+", p4jds="+String.valueOf(PlayerInfoP4.JDS)+" where _id=1";
		Log.i("roger", str);
		db.execSQL(str);		
		
		db.close();	
	}
}
