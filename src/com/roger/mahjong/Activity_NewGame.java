package com.roger.mahjong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.roger.mahjong.DialogFragment_Add.AddInputListener;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Activity_NewGame extends Activity implements AddInputListener {
	//20150217，添加PlayerInfo类
	public PlayerInfo PlayerInfoP1;
	public PlayerInfo PlayerInfoP2;
	public PlayerInfo PlayerInfoP3;
	public PlayerInfo PlayerInfoP4;
	//*************************
	public int JDVALUE;
	public String riqi;
	
	//添加记录后，修改以下变量
	public String p1Value;
	public String p2Value;
	public String p3Value;
	public String p4Value;
	public String strDetail;

	//4位玩家每局的输赢数值
	private int intP1;
	private int intP2;
	private int intP3;
	private int intP4;
	
	int InputFlag;	//记录输入数据的标志位，以确定输入是否有效，和谁输谁赢
	boolean InputOk;
	//public int intP1Data;	//测试阶段使用
	
	//fragment实例
	Fragment_Main fragment_Main;
	Fragment_P1 fragment_P1;
	Fragment_P2 fragment_P2;
	Fragment_P3 fragment_P3;
	Fragment_P4 fragment_P4;
	Fragment_Detail fragment_Detail;
	int FragmentFlag;	//保存当前显示的是哪个Fragment，此为下策，找到优雅的方法后替换
	
	ArrayList<HashMap<String, String>> mylist;
	ArrayList<HashMap<String, String>> memlist;
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
			
			PlayerInfoP1.Name=bundle.getString("p1name");
			PlayerInfoP2.Name=bundle.getString("p2name");
			PlayerInfoP3.Name=bundle.getString("p3name");
			PlayerInfoP4.Name= bundle.getString("p4name");
			JDVALUE = bundle.getInt("jdvalue");
			riqi = bundle.getString("riqi");
			//intP1Data = 1;
			
			mylist = new ArrayList<HashMap<String,String>>();
			SN = 1;
			PlayerInfoP1.Rounds = 0;
			PlayerInfoP2.Rounds = 0;
			PlayerInfoP3.Rounds = 0;
			PlayerInfoP4.Rounds = 0;			
		}
		else if(GameFlag.equalsIgnoreCase("goon"))
		{
			PlayerInfoP1 = new PlayerInfo();
			PlayerInfoP2 = new PlayerInfo();
			PlayerInfoP3 = new PlayerInfo();
			PlayerInfoP4 = new PlayerInfo();
			
			PlayerInfoP1.Name=bundle.getString("p1name");
			PlayerInfoP2.Name=bundle.getString("p2name");
			PlayerInfoP3.Name=bundle.getString("p3name");
			PlayerInfoP4.Name= bundle.getString("p4name");
			
			JDVALUE = bundle.getInt("jds");
			riqi = bundle.getString("riqi");
			//intP1Data = 1;
			
			mylist = new ArrayList<HashMap<String,String>>();
			mylist = (ArrayList<HashMap<String,String>>)bundle.getSerializable("xlist");
			
			//20150217提取每一局的记录到各个玩家变量中
			for (HashMap<String, String> hashMap: mylist)
			{
				PlayerInfoP1.lstRecordPerRound.add(Integer.valueOf(hashMap.get("p1value")));
				PlayerInfoP2.lstRecordPerRound.add(Integer.valueOf(hashMap.get("p2value")));
				PlayerInfoP3.lstRecordPerRound.add(Integer.valueOf(hashMap.get("p3value")));
				PlayerInfoP4.lstRecordPerRound.add(Integer.valueOf(hashMap.get("p4value")));				
			}
			PlayerInfoP1.Update4LostWin();
			PlayerInfoP2.Update4LostWin();
			PlayerInfoP3.Update4LostWin();
			PlayerInfoP4.Update4LostWin();
			String str="好的继续："+PlayerInfoP1.Name+String.valueOf(PlayerInfoP1.LoseWin)+PlayerInfoP2.Name+String.valueOf(PlayerInfoP2.LoseWin)+PlayerInfoP3.Name+String.valueOf(PlayerInfoP3.LoseWin)+PlayerInfoP4.Name+String.valueOf(PlayerInfoP4.LoseWin)+"\n"+String.valueOf(mylist.size());
			Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();			
			//**************************************
			
			SN = mylist.size();
			PlayerInfoP1.Rounds = SN;
			PlayerInfoP2.Rounds = SN;
			PlayerInfoP3.Rounds = SN;
			PlayerInfoP4.Rounds = SN;
			
			SN++;
			
			PlayerInfoP1.LoseWin = bundle.getInt("p1sum");
			PlayerInfoP2.LoseWin = bundle.getInt("p2sum");
			PlayerInfoP3.LoseWin = bundle.getInt("p3sum");
			PlayerInfoP4.LoseWin = bundle.getInt("p4sum");
			
			PlayerInfoP1.WinNumber = bundle.getInt("p1win");
			PlayerInfoP2.WinNumber = bundle.getInt("p2win");
			PlayerInfoP3.WinNumber = bundle.getInt("p3win");
			PlayerInfoP4.WinNumber = bundle.getInt("p4win");

			PlayerInfoP1.JDS = bundle.getInt("p1jds");
			PlayerInfoP2.JDS = bundle.getInt("p2jds");
			PlayerInfoP3.JDS = bundle.getInt("p3jds");
			PlayerInfoP4.JDS = bundle.getInt("p4jds");
		}
	
		ActionBar actionBar = getActionBar();
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		actionBar.setDisplayShowTitleEnabled(false);
		
		Tab Tab_Main = actionBar.newTab().setText("主");
		Tab Tab_P1 = actionBar.newTab().setText(PlayerInfoP1.Name);
		Tab Tab_P2 = actionBar.newTab().setText(PlayerInfoP2.Name);
		Tab Tab_P3 = actionBar.newTab().setText(PlayerInfoP3.Name);
		Tab Tab_P4 = actionBar.newTab().setText(PlayerInfoP4.Name);
		Tab Tab_Detail = actionBar.newTab().setText("详");

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
			Toast.makeText(getApplicationContext(), "添加记录", Toast.LENGTH_SHORT).show();
			DialogFragment_Add dialogFragment_Add = new DialogFragment_Add();
			dialogFragment_Add.show(getFragmentManager(), "添加记录");
			return true;
		case R.id.action_exit:
			Toast.makeText(getApplicationContext(), "退出应用", Toast.LENGTH_SHORT).show();
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
		
		//根据返回的字符设置InputFlag位的值
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
			case 238:	//p1赢
			{
				intP2 = Integer.valueOf(this.p2Value) * (-1);
				intP3 = Integer.valueOf(this.p3Value) * (-1);
				intP4 = Integer.valueOf(this.p4Value) * (-1);				
				intP1 = (intP2 + intP3 + intP4) * (-1);
				
				InputOk = true;
				break;
			}
			case 221:	//p2赢
			{
				intP1 = Integer.valueOf(this.p1Value) * (-1);
				intP3 = Integer.valueOf(this.p3Value) * (-1);
				intP4 = Integer.valueOf(this.p4Value) * (-1);				
				intP2 = (intP1 + intP3 + intP4) * (-1);

				InputOk = true;				
				break;
			}
			case 187:	//p3赢
			{
				intP1 = Integer.valueOf(this.p1Value) * (-1);
				intP2 = Integer.valueOf(this.p2Value) * (-1);
				intP4 = Integer.valueOf(this.p4Value) * (-1);				
				intP3 = (intP1 + intP2 + intP4) * (-1);
				
				InputOk = true;				
				break;
			}
			case 119:	//p4赢
			{
				intP1 = Integer.valueOf(this.p1Value) * (-1);
				intP2 = Integer.valueOf(this.p2Value) * (-1);
				intP3 = Integer.valueOf(this.p3Value) * (-1);				
				intP4 = (intP1 + intP2 + intP3) * (-1);
				
				InputOk = true;				
				break;
			}
			case 50:	//p2包p1
			{
				intP2 = Integer.valueOf(this.p2Value) * (-1);
				intP3 = 0;				
				intP4 = 0;
				intP1 = intP2 * (-1);
				
				InputOk = true;				
				break;
			}
			case 84:	//p3包p1
			{
				intP2 = 0;				
				intP3 = Integer.valueOf(this.p3Value) * (-1);
				intP4 = 0;
				intP1 = intP3 * (-1);
				
				InputOk = true;
				break;				
			}
			case 152:	//p4包p1
			{
				intP2 = 0;				
				intP3 = 0;
				intP4 = Integer.valueOf(this.p4Value) * (-1);
				intP1 = intP4 * (-1);
				
				InputOk = true;				
				break;				
			}
			case 49:	//p1包p2
			{
				intP1 = Integer.valueOf(this.p1Value) * (-1);				
				intP3 = 0;
				intP4 = 0;
				intP2 = intP1 * (-1);
				
				InputOk = true;
				break;				
			}
			case 100:	//p3包p2
			{
				intP1 = 0;				
				intP3 = Integer.valueOf(this.p3Value) * (-1);
				intP4 = 0;
				intP2 = intP3 * (-1);
				
				InputOk = true;				
				break;				
			}			
			case 168:	//p4包p2
			{
				intP1 = 0;				
				intP3 = 0;
				intP4 = Integer.valueOf(this.p4Value) * (-1);
				intP2 = intP4 * (-1);
								
				InputOk = true;				
				break;				
			}			
			case 81:	//p1包p3
			{
				intP1 = Integer.valueOf(this.p1Value) * (-1);				
				intP2 = 0;
				intP4 = 0;
				intP3 = intP1 * (-1);
								
				InputOk = true;				
				break;				
			}
			case 98:	//p2包p3
			{
				intP1 = 0;				
				intP2 = Integer.valueOf(this.p2Value) * (-1);
				intP4 = 0;
				intP3 = intP2 * (-1);

				InputOk = true;				
				break;				
			}			
			case 200:	//p4包p3
			{
				intP1 = 0;				
				intP2 = 0;
				intP4 = Integer.valueOf(this.p4Value) * (-1);
				intP3 = intP4 * (-1);
								
				InputOk = true;				
				break;
			}
			case 145:	//p1包p4
			{
				intP1 = Integer.valueOf(this.p1Value) * (-1);				
				intP2 = 0;
				intP3 = 0;
				intP4 = intP1 * (-1);
								
				InputOk = true;
				break;
			}			
			case 162:	//p2包p4
			{
				intP1 = 0;				
				intP2 = Integer.valueOf(this.p2Value) * (-1);
				intP3 = 0;
				intP4 = intP2 * (-1);
								
				InputOk = true;				
				break;
			}			
			case 196:	//p3包p4
			{
				intP1 = 0;				
				intP2 = 0;
				intP3 = Integer.valueOf(this.p3Value) * (-1);
				intP4 = intP3 * (-1);
								
				InputOk = true;				
				break;
			}
		}

		if(InputOk)	//输入的数据有效
		{
			//20150217存入个人记录中
			PlayerInfoP1.lstRecordPerRound.add(intP1);
			PlayerInfoP2.lstRecordPerRound.add(intP2);
			PlayerInfoP3.lstRecordPerRound.add(intP3);
			PlayerInfoP4.lstRecordPerRound.add(intP4);
			PlayerInfoP1.Rounds=PlayerInfoP1.lstRecordPerRound.size();
			PlayerInfoP2.Rounds=PlayerInfoP2.lstRecordPerRound.size();
			PlayerInfoP3.Rounds=PlayerInfoP3.lstRecordPerRound.size();
			PlayerInfoP4.Rounds=PlayerInfoP4.lstRecordPerRound.size();
			//*********************
			
			//存入ArrayList
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("SN", Utility.FormatData1To9(String.valueOf(SN)));
			map.put("p1value", String.valueOf(intP1));
			map.put("p2value", String.valueOf(intP2));
			map.put("p3value", String.valueOf(intP3));
			map.put("p4value", String.valueOf(intP4));
			//取时间
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
			Date curDate = new Date(System.currentTimeMillis());
			map.put("shijian", formatter.format(curDate));
			mylist.add(map);
			
			//更新PlayerInfo成员数据
			PlayerInfoP1.Update4LostWin();
			PlayerInfoP2.Update4LostWin();
			PlayerInfoP3.Update4LostWin();
			PlayerInfoP4.Update4LostWin();			
			
			//存入数据库中
			writeInDatabase(intP1, intP2, intP3, intP4, formatter.format(curDate), riqi);
			this.checkGameRec();
			
			SN++;

			this.updatePlayerInfoFromDB();
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
			fragment_P2.updata();
			break;
		case 3:
			fragment_P3.updata();
			break;
		case 4:
			fragment_P4.updata();
			break;
		case 5:
			fragment_Detail.updata();
			break;			
		}
	}
	
	private void writeInDatabase(int p1data, int p2data, int p3data, int p4data, String shijian, String riqi)
	{
		//20150730更新
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(this, "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		//添加每一局的记录
		db.execSQL("insert into TableGameRec(p1value, p2value, p3value, p4value, shijian, riqi) values(?,?,?,?,?,?)", new Object[]{p1data,p2data,p3data,p4data,shijian,riqi});
		db.close();	
	}
	
	private void updatePlayerInfoFromDB()
	{
		//20150730更新		
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(MyApplication.getContext(), "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM ViewGameInfo WHERE riqi='" + Utility.LoadGameRiqi() + "'", null);
		if(cursor.moveToFirst())
		{
			do
			{
				this.PlayerInfoP1.LoseWin = cursor.getInt(cursor.getColumnIndex("p1sum"));
				this.PlayerInfoP1.WinNumber = cursor.getInt(cursor.getColumnIndex("p1win"));
				this.PlayerInfoP1.LoseNumber = cursor.getInt(cursor.getColumnIndex("p1lose"));
				this.PlayerInfoP1.JDS = cursor.getInt(cursor.getColumnIndex("p1jds"));

				this.PlayerInfoP2.LoseWin = cursor.getInt(cursor.getColumnIndex("p2sum"));
				this.PlayerInfoP2.WinNumber = cursor.getInt(cursor.getColumnIndex("p2win"));
				this.PlayerInfoP2.LoseNumber = cursor.getInt(cursor.getColumnIndex("p2lose"));
				this.PlayerInfoP2.JDS = cursor.getInt(cursor.getColumnIndex("p2jds"));

				this.PlayerInfoP3.LoseWin = cursor.getInt(cursor.getColumnIndex("p3sum"));
				this.PlayerInfoP3.WinNumber = cursor.getInt(cursor.getColumnIndex("p3win"));
				this.PlayerInfoP3.LoseNumber = cursor.getInt(cursor.getColumnIndex("p3lose"));
				this.PlayerInfoP3.JDS = cursor.getInt(cursor.getColumnIndex("p3jds"));

				this.PlayerInfoP4.LoseWin = cursor.getInt(cursor.getColumnIndex("p4sum"));
				this.PlayerInfoP4.WinNumber = cursor.getInt(cursor.getColumnIndex("p4win"));
				this.PlayerInfoP4.LoseNumber = cursor.getInt(cursor.getColumnIndex("p4lose"));
				this.PlayerInfoP4.JDS = cursor.getInt(cursor.getColumnIndex("p4jds"));

				String str1 = this.PlayerInfoP1.Name + "->" + "输赢:"+this.PlayerInfoP1.LoseWin+"赢:"+this.PlayerInfoP1.WinNumber+" 输:"+this.PlayerInfoP1.LoseNumber+" 金:"+this.PlayerInfoP1.JDS;
				String str2 = this.PlayerInfoP2.Name + "->" + "输赢:"+this.PlayerInfoP2.LoseWin+"赢:"+this.PlayerInfoP2.WinNumber+" 输:"+this.PlayerInfoP2.LoseNumber+" 金:"+this.PlayerInfoP2.JDS;
				String str3 = this.PlayerInfoP3.Name + "->" + "输赢:"+this.PlayerInfoP3.LoseWin+"赢:"+this.PlayerInfoP3.WinNumber+" 输:"+this.PlayerInfoP3.LoseNumber+" 金:"+this.PlayerInfoP3.JDS;
				String str4 = this.PlayerInfoP4.Name + "->" + "输赢:"+this.PlayerInfoP4.LoseWin+"赢:"+this.PlayerInfoP4.WinNumber+" 输:"+this.PlayerInfoP4.LoseNumber+" 金:"+this.PlayerInfoP4.JDS;
				Log.d("roger", str1);
				Log.d("roger", str2);
				Log.d("roger", str3);
				Log.d("roger", str4);

			}while(cursor.moveToNext());
		}
		cursor.close();	
		db.close();
	}	
	
	private void checkGameRec()
	{
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(this, "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		
		Cursor cursor = db.rawQuery("select * from TableGameRec where riqi='"+ riqi +"'", null);
		if(cursor.moveToFirst())
		{
			do
			{
				int p1value = cursor.getInt(cursor.getColumnIndex("p1value"));
				int p2value = cursor.getInt(cursor.getColumnIndex("p2value"));
				int p3value = cursor.getInt(cursor.getColumnIndex("p3value"));
				int p4value = cursor.getInt(cursor.getColumnIndex("p4value"));
				String riqi = cursor.getString(cursor.getColumnIndex("riqi"));
				Log.d("roger", "东：" + p1value + " 南：" + p2value + " 西：" + p3value + " 北：" + p4value + " 日期：" + riqi);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("roger", "onPause() in Activity_NewGame.java");
	}
	
	
}
