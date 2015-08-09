package com.roger.mahjong;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MySurfaceView4Main extends SurfaceView implements Callback {
	private SurfaceHolder holder;
	//private MyThread myThread1;
	//private MyThread myThread2;
	//private MyThread myThread3;
	//private MyThread myThread4;
	private MyThread myThread;
	
	private int ScreenWidth;
	private int ScreenHeight;
	private float textSize;
	private PlayerInfo p1;
	private PlayerInfo p2;
	private PlayerInfo p3;
	private PlayerInfo p4;
	private int totalSum;
	private int charWidth;		//一个字符的宽度
	private int espStrWidth;	//-8888 字符串的宽度
	private int magicNumberRowX;
	private int magicNumberRowY;	
	
	//public MySurfaceView4Main(Context context, int p1Sum, int p2Sum, int p3Sum, int p4Sum)
	public MySurfaceView4Main(Context context, PlayerInfo aP1, PlayerInfo aP2, PlayerInfo aP3, PlayerInfo aP4)
	{
		super(context);
		
		this.p1 = aP1;
		this.p2 = aP2;
		this.p3 = aP3;
		this.p4 = aP4;
		
		this.totalSum = 0;
		if(p1.LoseWin>0)
			this.totalSum += p1.LoseWin;
		if(p2.LoseWin>0)
			this.totalSum += p2.LoseWin;
		if(p3.LoseWin>0)
			this.totalSum += p3.LoseWin;
		if(p4.LoseWin>0)
			this.totalSum += p4.LoseWin;
		
		Rect outRect = new Rect();
		((Activity)context).getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
		ScreenWidth = outRect.width();
		ScreenHeight = outRect.height();
		textSize = Utility.GetTextSizeFactor() * 3 * Utility.GetTextSizeFactor(ScreenWidth);
		
		TextPaint tp=new TextPaint();
		tp.setTextSize(Utility.GetTextSizeFactor() * 3 * Utility.GetTextSizeFactor(ScreenWidth));
		charWidth=(int)tp.measureText("8");
		espStrWidth = (int)tp.measureText("-8888");
		magicNumberRowX = charWidth/2;
		magicNumberRowY = (int)(100 * Utility.GetTextSizeFactor(ScreenWidth));
		//myThread1 = new MyThread(holder, magicNumberRowX, magicNumberRowY+0*ScreenHeight/8, p1, totalSum);
		//myThread2 = new MyThread(holder, magicNumberRowX, magicNumberRowY+2*ScreenHeight/8, p2, totalSum);
		//myThread3 = new MyThread(holder, magicNumberRowX, magicNumberRowY+4*ScreenHeight/8, p3, totalSum);
		//myThread4 = new MyThread(holder, magicNumberRowX, magicNumberRowY+6*ScreenHeight/8, p4, totalSum);
		
		holder = this.getHolder();
		holder.addCallback(this);		
		myThread = new MyThread(holder);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		/*
		myThread1.isRun = true;
		myThread2.isRun = true;
		myThread3.isRun = true;
		myThread4.isRun = true;		
		myThread1.start();
		myThread2.start();
		myThread3.start();
		myThread4.start();
		*/
		myThread.isRun = true;
		myThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		/*
		myThread1.isRun = false;
		myThread2.isRun = false;
		myThread3.isRun = false;
		myThread4.isRun = false;
		*/
		myThread.isRun = false;
	}

	class MyThread extends Thread
	{
		private SurfaceHolder holder;
		//private PlayerInfo p1;
		//private PlayerInfo p2;
		//private PlayerInfo p3;
		//private PlayerInfo p4;
		public boolean isRun;
		
		public MyThread(SurfaceHolder holder)
		{

			/*
			this.holder = holder;
			this.px = x;
			this.py = y;
			this.playerInfo = aPlayerInfo;
			this.EndNumber = playerInfo.LoseWin;
			this.absEndNumber = Math.abs(EndNumber);
			this.isRun = true;
			this.totalSum = aTotalSum;
			this.rect4Area_Black=new Rect(0, (int)(py-50 * Utility.GetTextSizeFactor(ScreenWidth)), ScreenWidth, (int)(py+50 * Utility.GetTextSizeFactor(ScreenWidth)));
			*/
			
			this.holder = holder;
			isRun = true;
		}
		
		private void clearScreen(Canvas c)
		{
			c.drawColor(Color.BLACK);			
		}
		
		private void drawPlayerInfo(Canvas c, PlayerInfo playerInfo, int px, int py, int totalSum)
		{
			//在画布上输出playerInfo的信息
			
			//显示玩家姓名
			Paint p = new Paint();
			p.setTextSize(textSize);
			p.setAntiAlias(true);
			p.setColor(Color.GRAY);
			c.drawText(playerInfo.Name, px, py, p);
			
			//取姓名字符串的宽度
			int playerNameWidth=(int)p.measureText(String.valueOf(playerInfo.Name));
			
			//画进度条背景
			p.setColor(Color.GRAY);
			RectF rectf=new RectF((int)(px+playerNameWidth+20*Utility.GetTextSizeFactor(ScreenWidth)), (int)(py-50 * Utility.GetTextSizeFactor(ScreenWidth)), ScreenWidth-charWidth-espStrWidth, (int)(py+10 * Utility.GetTextSizeFactor(ScreenWidth)));						
			c.drawRoundRect(rectf, 20f, 20f, p);
			
			if(playerInfo.LoseWin>0)
				p.setColor(Color.RED);
			else if(playerInfo.LoseWin<0)
				p.setColor(Color.GREEN);
			else
				p.setColor(Color.WHITE);

			//画进度条前景
			float prograssWidth = rectf.right-rectf.left;
			float currPrograssValue = Math.abs(playerInfo.LoseWin) * prograssWidth / totalSum;
			rectf=new RectF((int)(px+playerNameWidth+20*Utility.GetTextSizeFactor(ScreenWidth)), (int)(py-50 * Utility.GetTextSizeFactor(ScreenWidth)), (int)(px+playerNameWidth+20*Utility.GetTextSizeFactor(ScreenWidth)+currPrograssValue), (int)(py+10 * Utility.GetTextSizeFactor(ScreenWidth)));
			c.drawRoundRect(rectf, 20f, 20f, p);
			
			//显示数值
			c.drawText(String.valueOf(Math.abs(playerInfo.LoseWin)), ScreenWidth-espStrWidth-(charWidth/2), py, p);			
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRun)
			{
				Canvas c=null;
				try
				{
					synchronized (holder) {
						Rect rect=new Rect(0, 0, ScreenWidth, ScreenHeight);						
						c=holder.lockCanvas(rect);
						clearScreen(c);
						
						//p1信息输出
						drawPlayerInfo(c, p1, magicNumberRowX, magicNumberRowY+0*ScreenHeight/8, totalSum);
						
						//p2信息输出
						drawPlayerInfo(c, p2, magicNumberRowX, magicNumberRowY+2*ScreenHeight/8, totalSum);
						
						//p3信息输出
						drawPlayerInfo(c, p3, magicNumberRowX, magicNumberRowY+4*ScreenHeight/8, totalSum);
						
						//p4信息输出
						drawPlayerInfo(c, p4, magicNumberRowX, magicNumberRowY+6*ScreenHeight/8, totalSum);
						
						isRun=false;
						//myThread2 = new MyThread(holder, magicNumberRowX, magicNumberRowY+2*ScreenHeight/8, p2, totalSum);
						//myThread3 = new MyThread(holder, magicNumberRowX, magicNumberRowY+4*ScreenHeight/8, p3, totalSum);
						//myThread4 = new MyThread(holder, magicNumberRowX, magicNumberRowY+6*ScreenHeight/8, p4, totalSum);
						
						/*
						Paint p = new Paint();
						//p.setTextSize(Utility.GetTextSizeFactor() * 3 * Utility.GetTextSizeFactor(ScreenWidth));
						p.setTextSize(textSize);
						p.setAntiAlias(true);
						//20150220添加
						p.setColor(Color.GRAY);
						c.drawText(playerInfo.Name, px, py, p);
						
						//TextPaint tp=new TextPaint();
						//tp.setTextSize(Utility.GetTextSizeFactor() * 3 * Utility.GetTextSizeFactor(ScreenWidth));
						//tp.setTextSize(textSize);
						int playerNameWidth=(int)p.measureText(String.valueOf(playerInfo.Name));

						//画进度条背景
						p.setColor(Color.GRAY);
						RectF rectf=new RectF((int)(px+playerNameWidth+20*Utility.GetTextSizeFactor(ScreenWidth)), (int)(py-50 * Utility.GetTextSizeFactor(ScreenWidth)), ScreenWidth-charWidth-espStrWidth, (int)(py+10 * Utility.GetTextSizeFactor(ScreenWidth)));						
						c.drawRoundRect(rectf, 20f, 20f, p);
						
						//************
						if(this.EndNumber>0)
							p.setColor(Color.RED);
						else if(this.EndNumber<0)
							p.setColor(Color.GREEN);
						else
							p.setColor(Color.WHITE);

						//画进度条前景
						float prograssWidth = rectf.right-rectf.left;
						float currPrograssValue = count * prograssWidth / this.totalSum;
						rectf=new RectF((int)(px+playerNameWidth+20*Utility.GetTextSizeFactor(ScreenWidth)), (int)(py-50 * Utility.GetTextSizeFactor(ScreenWidth)), (int)(px+playerNameWidth+20*Utility.GetTextSizeFactor(ScreenWidth)+currPrograssValue), (int)(py+10 * Utility.GetTextSizeFactor(ScreenWidth)));
						Log.i("roger", "rectf.left:" + rectf.left + " rectf.top:" + rectf.top + " rectf.right:" + rectf.right + " rectf.bottom:" + rectf.bottom);						
						c.drawRoundRect(rectf, 20f, 20f, p);
						
						c.drawText(String.valueOf(count), ScreenWidth-espStrWidth-(charWidth/2), py, p);
						//count++;
						if(this.absEndNumber< count)
						*/
						//	isRun=false;
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
