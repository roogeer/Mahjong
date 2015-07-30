package com.roger.mahjong;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MySurfaceView4Main extends SurfaceView implements Callback {
	private SurfaceHolder holder;
	private MyThread myThread1;
	private MyThread myThread2;
	private MyThread myThread3;
	private MyThread myThread4;	
	
	private int ScreenWidth;
	private int ScreenHeight;
	private int myTextSize;	
	
	//public MySurfaceView4Main(Context context, int p1Sum, int p2Sum, int p3Sum, int p4Sum)
	public MySurfaceView4Main(Context context, PlayerInfo p1, PlayerInfo p2, PlayerInfo p3, PlayerInfo p4)
	{
		super(context);
		
		holder = this.getHolder();
		holder.addCallback(this);

		Rect outRect = new Rect();
		((Activity)context).getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
		ScreenWidth = outRect.width();
		ScreenHeight = outRect.height();
		myTextSize = 40*ScreenWidth/480;		
		
		myThread1 = new MyThread(holder, 100, 100+0*ScreenHeight/8, p1);
		myThread2 = new MyThread(holder, 100, 100+2*ScreenHeight/8, p2);
		myThread3 = new MyThread(holder, 100, 100+4*ScreenHeight/8, p3);
		myThread4 = new MyThread(holder, 100, 100+6*ScreenHeight/8, p4);		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		myThread1.isRun = true;
		myThread2.isRun = true;
		myThread3.isRun = true;
		myThread4.isRun = true;		
		myThread1.start();
		myThread2.start();
		myThread3.start();
		myThread4.start();		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		myThread1.isRun = false;
		myThread2.isRun = false;
		myThread3.isRun = false;
		myThread4.isRun = false;		
	}

	class MyThread extends Thread
	{
		private SurfaceHolder holder;
		private int px;
		private int py;
		private int EndNumber;
		private int absEndNumber;
		public PlayerInfo playerInfo;
		public boolean isRun;
		
		
		public MyThread(SurfaceHolder holder, int x, int y, PlayerInfo aPlayerInfo)
		{
			this.holder = holder;
			this.px = x;
			this.py = y;
			this.playerInfo = aPlayerInfo;
			this.EndNumber = playerInfo.LoseWin;
			this.absEndNumber = Math.abs(EndNumber);
			isRun = true;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int count =0;
			while(isRun)
			{
				Canvas c=null;
				try
				{
					synchronized (holder) {
						//c = holder.lockCanvas();
						Rect rect=new Rect(px-50, py-50, px+360, py+50);
						c=holder.lockCanvas(rect);
						c.drawColor(Color.BLACK);
						Paint p = new Paint();
						p.setTextSize(myTextSize);
						p.setAntiAlias(true);
						//20150220Ìí¼Ó
						p.setColor(Color.GRAY);
						c.drawText(playerInfo.Name, px, py, p);
						//************
						if(this.EndNumber>0)
							p.setColor(Color.RED);
						else if(this.EndNumber<0)
							p.setColor(Color.GREEN);
						else
							p.setColor(Color.WHITE);
								
						//Rect r = new Rect(100,50,300,250);
						//c.drawRect(r, p);
						
						TextPaint tp=new TextPaint();
						tp.setTextSize(myTextSize);
						String str= String.valueOf(playerInfo.Name);
						int playerValueWidth=(int)tp.measureText(str);
						c.drawText(String.valueOf(count), px+playerValueWidth+50, py, p);
						count++;
						if(this.absEndNumber< count)
							isRun=false;
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					if(c!=null)
					{
						holder.unlockCanvasAndPost(c);
					}
				}
			}
		}
	}
}
