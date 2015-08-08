package com.roger.mahjong;

import com.roger.mahjong.PlayerInfo.MyLine;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.Window;

public class MySurfaceView extends SurfaceView implements Callback {
	private SurfaceHolder holder;
	private Context context;
	private int ScreenWidth;
	private int ScreenHeight;
	private int WindowWidth;
	private int WindowHeight;
	private int myTextSize;
	
	private boolean runFlag = true;
	private Thread myThread;
	private Paint paint;
	
	private PlayerInfo playerInfo;
	
	public MySurfaceView(Context aContext, PlayerInfo aPlayerInfo)
	{
		super(aContext);

		Log.i("roger", "MySurfaceView构造函数, name:"+aPlayerInfo.Name);
		
		context=aContext;
		Rect outRect = new Rect();
		((Activity)context).getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
		ScreenWidth = outRect.width();
		ScreenHeight = outRect.height();
		WindowWidth = ScreenWidth;
		WindowHeight = ScreenWidth;
		myTextSize = Utility.GetTextSizeFactor();
		playerInfo = aPlayerInfo;
		playerInfo.Prepare4Draw(ScreenWidth);
		holder = this.getHolder();
		holder.addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder,int format,int width,int height) {
		// TODO Auto-generated method stub
		Log.i("roger", "in surfaceChanged()");
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		Log.i("roger", "in draw()");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i("roger", "in surFaceCreated()");

		myThread = new Thread(new MyRunnable());
		runFlag = true;
		myThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i("roger", "in surfaceDestroyed()");
		runFlag = false;
		myThread.interrupt();
	}

	private void initPaint()
	{
		paint = new Paint();
		paint.setTextSize(Utility.GetTextSizeFactor()*3);
		paint.setColor(Color.GRAY);
	}
	
	class MyRunnable implements Runnable {
		@Override
		public void run()
		{
			Canvas c = null;
			while(runFlag) {
				try {
					synchronized (holder) {
						c = holder.lockCanvas();
						if (null == c) {
							Log.i("roger", "Canvase==null");
						} else {
							Log.i("roger", "Canvase!=null");
						}

						// int canvasHeight = c.getHeight();
						// int canvasWidth = c.getWidth();

						c.drawColor(Color.BLACK);
						Paint p = new Paint();

						/*
						 * Rect outRect = new Rect();
						 * ((Activity)context).getWindow()
						 * .findViewById(Window.ID_ANDROID_CONTENT
						 * ).getDrawingRect(outRect); int drWidth =
						 * outRect.width(); int drHeight = outRect.height();
						 * 
						 * p.setColor(Color.GREEN);
						 * c.drawText("drWidth:"+drWidth+"  drHeight:"+drHeight,
						 * 100, 400, p);
						 * 
						 * c.drawText("canvasWidth:"+canvasWidth+"  canvasHeight:"
						 * + canvasHeight, 100, 600, p);
						 */
						/*
						 * DisplayMetrics dm = new DisplayMetrics();
						 * ((Activity)context
						 * ).getWindowManager().getDefaultDisplay
						 * ().getMetrics(dm); int dmWidth = dm.widthPixels; int
						 * dmHeight = dm.heightPixels; p.setColor(Color.GREEN);
						 * c.drawText("dmWidth:"+dmWidth+"  dmHeight:"+dmHeight,
						 * 100, 400, p);
						 */

						/*
						 * p.setColor(Color.GREEN); c.drawLine(0, 0,
						 * canvasWidth-1, 0, p); c.drawLine(0, 0, 0,
						 * canvasHeight-1, p); c.drawLine(canvasWidth-1,
						 * canvasHeight-1, canvasWidth-1, 0, p);
						 * c.drawLine(canvasWidth-1, canvasHeight-1, 0,
						 * canvasHeight-1, p);
						 */

						// p.setTextSize(myTextSize);
						p.setAntiAlias(true);
						p.setStrokeWidth(3);
						// 20150217添加修改
						// 画坐标
						p.setColor(Color.GRAY);
						//p.setColor(Color.BLUE);
						c.drawLine(0, 0, 0, WindowHeight, p);
						//p.setColor(Color.GREEN);
						c.drawLine(0, WindowHeight, WindowWidth - 1, WindowHeight, p);
						//p.setColor(Color.RED);
						c.drawLine(WindowWidth, WindowHeight, WindowWidth, 0, p);						
						//p.setColor(Color.YELLOW);
						c.drawLine(WindowWidth-1, 0, 0, 0, p);						

						// 画0值点坐标
						//p.setColor(Color.GRAY);
						//c.drawLine(0, playerInfo.ScreenYZero, WindowWidth - 1, playerInfo.ScreenYZero, p);
						/*
						 * int y0=0; for (int x : playerInfo.lstRecordPerSum) {
						 * c.drawText(String.valueOf(x), 100, 10+y0*20, p);
						 * y0++; } c
						 * .drawText("最大值："+String.valueOf(playerInfo.WinValue
						 * )+" 出现在第:" +String.valueOf(playerInfo.WinRound)+"局",
						 * 100, 10+y0*20, p); y0++;
						 * c.drawText("最小值："+String.valueOf(playerInfo.LostValue
						 * )+" 出现在第:"+String.valueOf(playerInfo.LostRound)+"局",
						 * 100, 10+y0*20, p); y0++;
						 * c.drawText("总局数："+String.valueOf(playerInfo.Rounds),
						 * 100, 10+y0*20, p); y0++; //*****************
						 * 
						 * for(MyLine tempLine:playerInfo.lstLinesTemp) {
						 * c.drawText(
						 * "线段("+String.valueOf(tempLine.P0.x)+", "+String
						 * .valueOf(tempLine
						 * .P0.y)+")-("+String.valueOf(tempLine.P1.x
						 * )+", "+String.valueOf(tempLine.P1.y)+")", 100 ,
						 * 10+y0*20, p); y0++; } c.drawText(
						 * "================================================" ,
						 * 100 , 10+y0*20, p); y0++;
						 */

						// 画走势曲线图
						for (MyLine tempLine : playerInfo.lstLines) {
							// c.drawText("线段("+String.valueOf(tempLine.P0.x)+", "+String.valueOf(tempLine.P0.y)+")-("+String.valueOf(tempLine.P1.x)+", "+String.valueOf(tempLine.P1.y)+")",
							// 100 , 10+y0*20, p);
							// y0++;
							p.setColor(tempLine.color);
							c.drawLine(tempLine.P0.x, tempLine.P0.y,
									tempLine.P1.x, tempLine.P1.y, p);
						}

						// 显示信息统计
						int rowY = 60;	// 第1行信息的Y坐标值
						
						if(null == paint)
							initPaint();
						float outputStrWidth = 0;
						float nextOutputX = 10;
						String outputStr = playerInfo.Name+":";
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);
						outputStrWidth = paint.measureText(outputStr);
						nextOutputX += outputStrWidth;
						
						if(playerInfo.LoseWin>0)
						{
							paint.setColor(Color.RED);
						}
						else if(playerInfo.LoseWin<0)
						{
							paint.setColor(Color.GREEN);
						}
						else
						{
							paint.setColor(Color.GRAY);
						}
						c.drawText(String.valueOf(playerInfo.LoseWin), nextOutputX, WindowHeight + rowY , paint);
						
						paint.setColor(Color.GRAY);
						outputStr = "HP:";
						float nextoutputX1 = ScreenWidth/2;
						float nextoutputX2 = ScreenWidth - paint.measureText("HP:-888/8888");
						
						nextOutputX = (nextoutputX1 > paint.measureText("HP:-888/8888")?nextoutputX1:nextoutputX2);
						float rightSideX = nextOutputX;	//第2行信息输出时，右侧也使用这个x坐标值
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY , paint);
						outputStrWidth = paint.measureText(outputStr);
						nextOutputX += outputStrWidth; 						
						
						if(playerInfo.LoseValue>0)
						{
							paint.setColor(Color.RED);
						}
						else if(playerInfo.LoseValue<0)
						{
							paint.setColor(Color.GREEN);
						}
						else
						{
							paint.setColor(Color.GRAY);
						}
						outputStr = String.valueOf(playerInfo.LoseValue);
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);
						outputStrWidth = paint.measureText(outputStr);
						nextOutputX += outputStrWidth;						
						
						paint.setColor(Color.GRAY);
						outputStr = "/";
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);
						outputStrWidth = paint.measureText(outputStr);
						nextOutputX += outputStrWidth;						
						
						if(playerInfo.WinValue>0)
						{
							paint.setColor(Color.RED);
						}
						else if(playerInfo.WinValue<0)
						{
							paint.setColor(Color.GREEN);
						}
						else
						{
							paint.setColor(Color.GRAY);
						}
						outputStr = String.valueOf(playerInfo.WinValue);
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);
						
						//第2行信息输出
						rowY += 100;	//第2行信息的Y坐标值 
						paint.setColor(Color.GRAY);
						outputStrWidth = 0;
						nextOutputX = 10;
						outputStr = "Lv:";
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);
						outputStrWidth = paint.measureText(outputStr);
						nextOutputX += outputStrWidth;
						
						if(playerInfo.WinNumber>0)
						{
							paint.setColor(Color.RED);
						}
						outputStr = String.valueOf(playerInfo.WinNumber);
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);
						outputStrWidth = paint.measureText(outputStr);
						nextOutputX += outputStrWidth;
						
						outputStr = "/";
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);
						outputStrWidth = paint.measureText(outputStr);
						nextOutputX += outputStrWidth;
						
						outputStr = String.valueOf(playerInfo.Rounds);;
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);						
						
						paint.setColor(Color.GRAY);
						nextOutputX = rightSideX;
						outputStr = "MP:";
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);
						outputStrWidth = paint.measureText(outputStr);
						nextOutputX += outputStrWidth;
						
						outputStr = String.valueOf(playerInfo.LoseRound);
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);
						outputStrWidth = paint.measureText(outputStr);
						nextOutputX += outputStrWidth;
						
						outputStr = "/";
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);
						outputStrWidth = paint.measureText(outputStr);
						nextOutputX += outputStrWidth;
						
						outputStr = String.valueOf(playerInfo.WinRound);
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);
						
						//第3行信息输出
						rowY +=100;		//第3行信息的Y坐标值
						paint.setColor(Color.GRAY);
						outputStrWidth = 0;
						nextOutputX = 10;						
						outputStr = "ESP:";
						c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);
						outputStrWidth = paint.measureText(outputStr);
						nextOutputX += outputStrWidth;

						if(0 == playerInfo.JDS)
						{
							paint.setColor(Color.GRAY);							
							outputStr = "--";
							c.drawText(outputStr, nextOutputX, WindowHeight + rowY, paint);							
						}
						else
						{
							paint.setColor(Color.YELLOW);
							for (int i = 0; i < playerInfo.JDS; i++) {
								outputStr = "▲ ";
								c.drawText(outputStr, nextOutputX,
										WindowHeight + rowY, paint);
								outputStrWidth = paint.measureText(outputStr);
								nextOutputX += outputStrWidth;
							}
						}
						/*
						 * p.setTextSize(myTextSize); TextPaint tp=new
						 * TextPaint(); tp.setTextSize(myTextSize); String str =
						 * ""; int playerValueWidth = 0; int y=ScreenWidth+80;
						 * int x=5; if (playerInfo.LostWin<0) {
						 * p.setColor(Color.GREEN); str =
						 * "-"+String.valueOf(playerInfo.LostWin*(-1));
						 * c.drawText(str, x, y, p); } else
						 * if(playerInfo.LostWin>0) { p.setColor(Color.RED); str
						 * = "+"+String.valueOf(playerInfo.LostWin);
						 * c.drawText(str, x, y, p); } p.setColor(Color.GRAY);
						 * playerValueWidth = (int)tp.measureText(str); x +=
						 * playerValueWidth+20; str = "/"; c.drawText(str, x, y,
						 * p);
						 * 
						 * playerValueWidth = (int)tp.measureText(str); x +=
						 * playerValueWidth+20; str =
						 * String.valueOf(playerInfo.Rounds); c.drawText(str, x,
						 * y, p);
						 */
						// c.drawText("ZeroY"+String.valueOf(playerInfo.ScreenYZero),
						// 100, 10+y0*20, p);

						// FontMetrics fm=p.getFontMetrics();
						// int playerValueHeight=
						// (int)Math.ceil(fm.descent-fm.ascent) + 2;

						/*
						 * 20150217临时修改 if(playerInfo.LostWin < 0) {
						 * p.setColor(Color.GREEN); strPlayerValue =
						 * "-"+String.valueOf(Math.abs(playerInfo.LostWin)); }
						 * else if(playerInfo.LostWin>0) {
						 * p.setColor(Color.RED); strPlayerValue =
						 * "+"+String.valueOf(playerInfo.LostWin); } else {
						 * p.setColor(Color.WHITE); strPlayerValue =
						 * String.valueOf(playerInfo.LostWin); }
						 * 
						 * TextPaint tp=new TextPaint();
						 * tp.setTextSize(myTextSize); int
						 * playerValueWidth=(int)tp.measureText(strPlayerValue);
						 * c.drawText(strPlayerValue,
						 * (ScreenWidth-playerValueWidth)/2, (ScreenHeight)/2,
						 * p); //20150217临时修改
						 */

						// c.drawText(String.valueOf(playerValueHeight), 0, 300,
						// p);
						/*
						 * c.drawText("这是我的SurfaceView测试", 100, 310, p);
						 * c.drawText("dmWidth:"
						 * +dmWidth+"  dmHeight:"+dmHeight+"  canvasHeight"
						 * +canvasHeight
						 * +"  canvasWidth"+canvasWidth+"  windowsHeight"
						 * +windowsHeight+"  windowsWidth"+windowsWidth+" " ,
						 * 100, 400, p);
						 * c.drawText("drWidth:"+drWidth+"  drHeight:"+drHeight,
						 * 100, 500, p);
						 * 
						 * //ActionBar高度 TypedValue tv = new TypedValue(); int
						 * actionBarHeight = 0;
						 * if(context.getTheme().resolveAttribute
						 * (android.R.attr.actionBarSize, tv, true)) {
						 * actionBarHeight =
						 * TypedValue.complexToDimensionPixelSize(tv.data,
						 * context.getResources().getDisplayMetrics()); }
						 * c.drawText("actionBarHeight:"+actionBarHeight, 100,
						 * 600, p);
						 */

						/*
						 * p.setColor(Color.WHITE); Rect r=new Rect(); r.left =
						 * canvasWidth/2-50; r.right = canvasWidth/2+50; r.top =
						 * canvasHeight/2-50; r.bottom = canvasHeight/2+50;
						 * c.drawRect(r, p);
						 */

						/*
						 * p.setColor(Color.RED); c.drawLine(0, 0, canvasWidth,
						 * canvasHeight, p); c.drawLine(canvasWidth, 0, 0,
						 * canvasHeight, p);
						 */
						/*
						 * p.setColor(Color.YELLOW); c.drawLine(0,
						 * (canvasHeight+actionBarHeight)/2, canvasWidth,
						 * (canvasHeight+actionBarHeight)/2, p);
						 * 
						 * p.setColor(Color.GREEN); c.drawLine(0,
						 * (canvasHeight-actionBarHeight)/2, canvasWidth,
						 * (canvasHeight-actionBarHeight)/2, p);
						 */
						/*
						 * p.setColor(Color.CYAN); c.drawLine(0,
						 * (canvasHeight)/2, canvasWidth-1, (canvasHeight)/2,
						 * p);
						 */
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (c != null) {
						holder.unlockCanvasAndPost(c);
						
						runFlag = false;
						Log.i("roger", "holder.unlockCanvasAndPost(c)");
					}
				}
			}
		}
	}
}
