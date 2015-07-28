package com.roger.mahjong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.roger.mahjong.DialogFragment_PlayerInfo.InfoInputListener;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity implements InfoInputListener{

	private Button btnNewGameButton;
	private Button btnGoOnButton;
	private Button btnOldMemoryButton;
	//private int TextSizefactor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnNewGameButton = (Button)findViewById(R.id.button_NewGame);
		btnGoOnButton = (Button)findViewById(R.id.button_GoOn);
		btnOldMemoryButton=(Button)findViewById(R.id.button_OldMemory);
		
		Display display = getWindowManager().getDefaultDisplay();
		Point point = new Point();
		display.getSize(point);
		
		//TextSizefactor = 20*(point.x/480);	//屏幕实际宽度point.x
		setTitle("屏幕分辨率为："+point.x+'*'+point.y);
		
		btnNewGameButton.setTextSize(Utility.GetTextSizeFactor(this));
		btnGoOnButton.setTextSize(Utility.GetTextSizeFactor(this));
		btnOldMemoryButton.setTextSize(Utility.GetTextSizeFactor(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu4main_activity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_exit) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void NewGameOnClick(View v)
	{
		Toast.makeText(getApplicationContext(), "新的故事", Toast.LENGTH_SHORT).show();


		DialogFragment_PlayerInfo dialogFragment_info = new DialogFragment_PlayerInfo();
		dialogFragment_info.show(getFragmentManager(), "新的故事");

		//return true;		
	}

	
	public void OldMemoryOnClick(View v)
	{
		Toast.makeText(getApplicationContext(), "旧的回忆", Toast.LENGTH_SHORT).show();
	}
	
	public void GoOnClick(View v)
	{
		String riqi = Utility.LoadGameRiqi();
		Log.d("roger", riqi);		
		
		ArrayList<HashMap<String, String>> mylist;
		
		Toast.makeText(getApplicationContext(), "好的继续", Toast.LENGTH_SHORT).show();
		//从数据库中读取记录，包装好后，启动Activity_NewGame
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(this, "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		String strSQL = "select p1name, p1jds, p2name, p2jds, p3name, p3jds, p4name, p4jds, jds from GameRec_LastInfo";
		Cursor cursor =  db.rawQuery(strSQL, null);
		if(0==cursor.getCount())
		{
			return;
		}
		
		cursor.moveToFirst();
		
		Bundle bundle = new Bundle();
		bundle.putString("gameflag", "goon");
		
		bundle.putString("playeronename", cursor.getString(cursor.getColumnIndex("p1name")));
		bundle.putInt("playeronejds", cursor.getInt(cursor.getColumnIndex("p1jds")));		
		bundle.putString("playertwoname", cursor.getString(cursor.getColumnIndex("p2name")));
		bundle.putInt("playertwojds", cursor.getInt(cursor.getColumnIndex("p2jds")));		
		bundle.putString("playerthreename", cursor.getString(cursor.getColumnIndex("p3name")));
		bundle.putInt("playerthreejds", cursor.getInt(cursor.getColumnIndex("p3jds")));		
		bundle.putString("playerfourname", cursor.getString(cursor.getColumnIndex("p4name")));
		bundle.putInt("playerfourjds", cursor.getInt(cursor.getColumnIndex("p4jds")));		
		bundle.putInt("jds", cursor.getInt(cursor.getColumnIndex("jds")));
		cursor.close();
		
		//每局的详细记录也传到bundle中
		strSQL = "select _id, p1data, p2data, p3data, p4data, shijian from GameRec_LastDetail";
		cursor =  db.rawQuery(strSQL, null);
		if(0==cursor.getCount())
		{
			return;
		}
		
		mylist = new ArrayList<HashMap<String,String>>();
		while(cursor.moveToNext())
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("SN", Utility.FormatData1To9(String.valueOf(cursor.getInt(cursor.getColumnIndex("_id")))));
			map.put("playerOneData", String.valueOf(cursor.getInt(cursor.getColumnIndex("p1data"))));
			map.put("playerTwoData", String.valueOf(cursor.getInt(cursor.getColumnIndex("p2data"))));
			map.put("playerThreeData", String.valueOf(cursor.getInt(cursor.getColumnIndex("p3data"))));
			map.put("playerFourData", String.valueOf(cursor.getInt(cursor.getColumnIndex("p4data"))));
			map.put("timeData", cursor.getString(cursor.getColumnIndex("shijian")));
			mylist.add(map);
		}
		bundle.putSerializable("xlist", mylist);
		cursor.close();
		
		//取玩家累计输赢值
		strSQL = "select sum(p1data) p1sum, sum(p2data) p2sum, sum(p3data) p3sum, sum(p4data) p4sum from GameRec_LastDetail;";
		Log.i("roger", strSQL);
		cursor =  db.rawQuery(strSQL, null);
		if(0==cursor.getCount())
		{
			return;
		}
		cursor.moveToFirst();		
		bundle.putInt("p1sum", cursor.getInt(cursor.getColumnIndex("p1sum")));
		bundle.putInt("p2sum", cursor.getInt(cursor.getColumnIndex("p2sum")));
		bundle.putInt("p3sum", cursor.getInt(cursor.getColumnIndex("p3sum")));
		bundle.putInt("p4sum", cursor.getInt(cursor.getColumnIndex("p4sum")));
		cursor.close();
		
		db.close();
		
		Intent it=new Intent(MainActivity.this, Activity_NewGame.class);
		it.putExtras(bundle);		
		startActivity(it);
		finish();		
	}
	
	public void onInfoInputComplete(String p1Name, String p2Name, String p3Name, String p4Name, String JDS)
	{	
		String str = p1Name + "-" + p2Name + "-" + p3Name + "-" + p4Name + "-" + JDS;
		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
		Bundle bundle=new Bundle();
		bundle.putString("gameflag", "newgame");	//选择开始一局新的游戏
		
		bundle.putString("playeronename", p1Name.substring(0,1));
		bundle.putString("playertwoname", p2Name.substring(0,1));
		bundle.putString("playerthreename", p3Name.substring(0,1));
		bundle.putString("playerfourname", p4Name.substring(0,1));
		bundle.putInt("jds", Integer.valueOf(JDS));
		Intent it=new Intent(MainActivity.this, Activity_NewGame.class);
		it.putExtras(bundle);		
		startActivity(it);
		finish();
		
		//将游戏初始信息存入数据库中
		initDataBase(p1Name, p2Name, p3Name, p4Name, Integer.valueOf(JDS));
	}
	
	private void initDataBase(String p1name, String p2name, String p3name, String p4name, int jds)
	{
		final String CREATE_TABLE_GameRec_LastInfo = "create table GameRec_LastInfo(" +
				"_id integer primary key autoincrement," +
				"p1name varchar(8)," +
				"p1jds integer," +
				"p2name varchar(8)," +
				"p2jds integer," +
				"p3name varchar(8)," +
				"p3jds integer," +
				"p4name varchar(8)," +
				"p4jds integer," +
				"jds integer" +
				")";
		
		final String CREATE_TABLE_GameRec_LastDetail = "create table GameRec_LastDetail(" +
				"_id integer primary key autoincrement," +
				"p1data integer," +
				"p2data integer," +
				"p3data integer," +
				"p4data integer," +
				"shijian varchar(8)" +
				")";		
		
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(this, "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();

		db.execSQL("drop table GameRec_LastInfo");
		db.execSQL("drop table GameRec_LastDetail");
		
		db.execSQL(CREATE_TABLE_GameRec_LastInfo);
		db.execSQL(CREATE_TABLE_GameRec_LastDetail);
		
		db.execSQL("insert into GameRec_LastInfo(p1name, p1jds, p2name, p2jds, p3name, p3jds, p4name, p4jds, jds) values(?,?,?,?,?,?,?,?,?)", new Object[]{p1name,0,p2name,0,p3name,0,p4name,0,jds});
		db.close();		
	}
}

