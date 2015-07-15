package com.roger.mahjong;

import com.roger.mahjong.PlayerInfo.MyLine;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.Window;

public class MySurfaceView extends SurfaceView implements Callback {
	private SurfaceHolder holder;
	private Context context;
	//private int PlayerValue;
	//private String strPlayerValue;
	private int ScreenWidth;
	private int ScreenHeight;
	private int myTextSize;
	
	private PlayerInfo playerInfo;
	
	public MySurfaceView(Context aContext, PlayerInfo aPlayerInfo)
	{
		super(aContext);
		
		context=aContext;
		Rect outRect = new Rect();
		((Activity)context).getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
		ScreenWidth = outRect.width();
		ScreenHeight = outRect.height();
		myTextSize = 40*ScreenWidth/480;
		playerInfo = aPlayerInfo;
		playerInfo.Prepare4Draw(ScreenWidth);	
		holder = this.getHolder();
		holder.addCallback(this);
	}
	
	/*
	public MySurfaceView(Context aContext, int aPlayerValue)
	{
		super(aContext);
		
		context=aContext;
		Rect outRect = new Rect();
		((Activity)context).getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
		ScreenWidth = outRect.width();
		ScreenHeight = outRect.height();
		myTextSize = 160*ScreenWidth/480;
		
		//PlayerValue=aPlayerValue;
		holder = this.getHolder();
		holder.addCallback(this);
	}
	*/
	
	@Override
	public void surfaceChanged(SurfaceHolder holder,int format,int width,int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Canvas c = null;
		try
		{
			synchronized (holder)
			{
				c = holder.lockCanvas();
				//int canvasHeight = c.getHeight();
				//int canvasWidth = c.getWidth();
				
				
				c.drawColor(Color.BLACK);
				Paint p =new Paint();
				
				/*
				Rect outRect = new Rect();
				((Activity)context).getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
				int drWidth = outRect.width();
				int drHeight = outRect.height();

				p.setColor(Color.GREEN);
				c.drawText("drWidth:"+drWidth+"  drHeight:"+drHeight, 100, 400, p);				
				
				c.drawText("canvasWidth:"+canvasWidth+"  canvasHeight:"+canvasHeight, 100, 600, p);
				*/				
				/*
				DisplayMetrics dm = new DisplayMetrics();
				((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
				int dmWidth = dm.widthPixels;
				int dmHeight = dm.heightPixels;				
				p.setColor(Color.GREEN);
				c.drawText("dmWidth:"+dmWidth+"  dmHeight:"+dmHeight, 100, 400, p);
				*/				
				
				/*
				p.setColor(Color.GREEN);
				c.drawLine(0, 0, canvasWidth-1, 0, p);
				c.drawLine(0, 0, 0, canvasHeight-1, p);
				c.drawLine(canvasWidth-1, canvasHeight-1, canvasWidth-1, 0, p);
				c.drawLine(canvasWidth-1, canvasHeight-1, 0, canvasHeight-1, p);
				*/
				
				//p.setTextSize(myTextSize);
				p.setAntiAlias(true);
				p.setStrokeWidth(3);
				//20150217添加修改
				//画坐标
				p.setColor(Color.GRAY);
				c.drawLine(0, 0,  1, ScreenWidth, p);
				c.drawLine(0, ScreenWidth-1, ScreenWidth-1, ScreenWidth, p);
				Log.i("roger", ":ScreenHeight"+ScreenHeight);
				//画0值点坐标
				p.setColor(Color.WHITE);
				c.drawLine(0,  playerInfo.ScreenYZero,  ScreenWidth-1, playerInfo.ScreenYZero, p);				
				/*
				int y0=0;
				for (int x : playerInfo.lstRecordPerSum)
				{
					c.drawText(String.valueOf(x), 100, 10+y0*20, p);
					y0++;
				}
				c.drawText("最大值："+String.valueOf(playerInfo.WinValue)+" 出现在第:"+String.valueOf(playerInfo.WinRound)+"局", 100, 10+y0*20, p);
				y0++;
				c.drawText("最小值："+String.valueOf(playerInfo.LostValue)+" 出现在第:"+String.valueOf(playerInfo.LostRound)+"局", 100, 10+y0*20, p);
				y0++;
				c.drawText("总局数："+String.valueOf(playerInfo.Rounds), 100, 10+y0*20, p);
				y0++;
				//*****************
				
				for(MyLine tempLine:playerInfo.lstLinesTemp)
				{
					c.drawText("线段("+String.valueOf(tempLine.P0.x)+", "+String.valueOf(tempLine.P0.y)+")-("+String.valueOf(tempLine.P1.x)+", "+String.valueOf(tempLine.P1.y)+")", 100 ,  10+y0*20, p);
					y0++;
				}
				c.drawText("================================================", 100 ,  10+y0*20, p);
				y0++;
				*/
				
				//画走势曲线图
				for(MyLine tempLine:playerInfo.lstLines)
				{
					//c.drawText("线段("+String.valueOf(tempLine.P0.x)+", "+String.valueOf(tempLine.P0.y)+")-("+String.valueOf(tempLine.P1.x)+", "+String.valueOf(tempLine.P1.y)+")", 100 ,  10+y0*20, p);
					//y0++;
					p.setColor(tempLine.color);
					c.drawLine(tempLine.P0.x, tempLine.P0.y, tempLine.P1.x, tempLine.P1.y, p);
				}
				
				//显示信息统计
				/*
				p.setTextSize(myTextSize);
				TextPaint tp=new TextPaint();
				tp.setTextSize(myTextSize);
				String str = "";
				int playerValueWidth = 0;				
				int y=ScreenWidth+80;
				int x=5;
				if (playerInfo.LostWin<0)
				{
					p.setColor(Color.GREEN);
					str = "-"+String.valueOf(playerInfo.LostWin*(-1));
					c.drawText(str, x, y, p);
				}
				else if(playerInfo.LostWin>0)
				{
					p.setColor(Color.RED);
					str = "+"+String.valueOf(playerInfo.LostWin);
					c.drawText(str, x, y, p);
				}
				p.setColor(Color.GRAY);
				playerValueWidth = (int)tp.measureText(str);
				x += playerValueWidth+20;
				str = "/";
				c.drawText(str, x, y, p);
				
				playerValueWidth = (int)tp.measureText(str);
				x += playerValueWidth+20;
				str = String.valueOf(playerInfo.Rounds);
				c.drawText(str, x, y, p);
				*/
				//c.drawText("ZeroY"+String.valueOf(playerInfo.ScreenYZero), 100, 10+y0*20, p);
				
				
				//FontMetrics fm=p.getFontMetrics();
				//int playerValueHeight= (int)Math.ceil(fm.descent-fm.ascent) + 2;
				
				/*20150217临时修改
				if(playerInfo.LostWin < 0)
				{
					p.setColor(Color.GREEN);
					strPlayerValue = "-"+String.valueOf(Math.abs(playerInfo.LostWin));
				}
				else if(playerInfo.LostWin>0)
				{
					p.setColor(Color.RED);
					strPlayerValue = "+"+String.valueOf(playerInfo.LostWin);
				}
				else {
					p.setColor(Color.WHITE);
					strPlayerValue = String.valueOf(playerInfo.LostWin);
				}

				TextPaint tp=new TextPaint();
				tp.setTextSize(myTextSize);
				int playerValueWidth=(int)tp.measureText(strPlayerValue);
				c.drawText(strPlayerValue, (ScreenWidth-playerValueWidth)/2, (ScreenHeight)/2, p);
				//20150217临时修改*/
				
				//c.drawText(String.valueOf(playerValueHeight), 0, 300, p);				
				/*
				c.drawText("这是我的SurfaceView测试", 100, 310, p);
				c.drawText("dmWidth:"+dmWidth+"  dmHeight:"+dmHeight+"  canvasHeight"+canvasHeight+"  canvasWidth"+canvasWidth+"  windowsHeight"+windowsHeight+"  windowsWidth"+windowsWidth+" " , 100, 400, p);
				c.drawText("drWidth:"+drWidth+"  drHeight:"+drHeight, 100, 500, p);
				
				//ActionBar高度
				TypedValue tv = new TypedValue();
				int actionBarHeight = 0;
				if(context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
				{
					actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
				}
				c.drawText("actionBarHeight:"+actionBarHeight, 100, 600, p);				
				*/
				
				/*
				p.setColor(Color.WHITE);
				Rect r=new Rect();
				r.left = canvasWidth/2-50;
				r.right = canvasWidth/2+50;
				r.top = canvasHeight/2-50;
				r.bottom = canvasHeight/2+50;
				c.drawRect(r, p);				
				*/
				
				/*
				p.setColor(Color.RED);
				c.drawLine(0, 0, canvasWidth, canvasHeight, p);
				c.drawLine(canvasWidth, 0, 0, canvasHeight, p);
				*/
				/*
				p.setColor(Color.YELLOW);
				c.drawLine(0, (canvasHeight+actionBarHeight)/2, canvasWidth, (canvasHeight+actionBarHeight)/2, p);

				p.setColor(Color.GREEN);
				c.drawLine(0, (canvasHeight-actionBarHeight)/2, canvasWidth, (canvasHeight-actionBarHeight)/2, p);				
				*/
				/*
				p.setColor(Color.CYAN);
				c.drawLine(0, (canvasHeight)/2, canvasWidth-1, (canvasHeight)/2, p);
				*/
			}
		}
		catch (Exception e)
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

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

}
