package com.roger.mahjong;

import java.util.ArrayList;
import java.util.HashMap;
import com.roger.mahjong.DialogFragment_PlayerInfo.InfoInputListener;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements InfoInputListener{

	private Button btnNewGameButton;
	private Button btnGoOnButton;
	private Button btnOldMemoryButton;
	
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
		
		setTitle("屏幕分辨率为："+point.x+'*'+point.y);
		
		btnNewGameButton.setTextSize(Utility.GetTextSizeFactor());
		btnGoOnButton.setTextSize(Utility.GetTextSizeFactor());
		btnOldMemoryButton.setTextSize(Utility.GetTextSizeFactor());
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
	}

	
	public void OldMemoryOnClick(View v)
	{
		Toast.makeText(getApplicationContext(), "旧的回忆", Toast.LENGTH_SHORT).show();
	}
	
	public void GoOnClick(View v)
	{
		String riqi = Utility.LoadGameRiqi();
		
		ArrayList<HashMap<String, String>> mylist;
		
		Toast.makeText(getApplicationContext(), "好的继续", Toast.LENGTH_SHORT).show();
		//从数据库中读取记录，包装好后，启动Activity_NewGame
		
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(this, "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		String strSQL = "SELECT * FROM ViewGameInfo where riqi='" + riqi + "'";
		Cursor cursor =  db.rawQuery(strSQL, null);
		if(0==cursor.getCount())
		{
			return;
		}
		
		cursor.moveToFirst();
		Bundle bundle = new Bundle();
		bundle.putString("gameflag", "goon");
		
		bundle.putString("riqi", cursor.getString(cursor.getColumnIndex("riqi")));
		bundle.putInt("jdvalue", cursor.getInt(cursor.getColumnIndex("jdvalue")));
		
		bundle.putString("p1name", cursor.getString(cursor.getColumnIndex("p1name")));
		bundle.putInt("p1sum", cursor.getInt(cursor.getColumnIndex("p1sum")));
		bundle.putInt("p1win", cursor.getInt(cursor.getColumnIndex("p1win")));
		bundle.putInt("p1lose", cursor.getInt(cursor.getColumnIndex("p1lose")));
		bundle.putInt("pljds", cursor.getInt(cursor.getColumnIndex("p1jds")));

		bundle.putString("p2name", cursor.getString(cursor.getColumnIndex("p2name")));
		bundle.putInt("p2sum", cursor.getInt(cursor.getColumnIndex("p2sum")));
		bundle.putInt("p2win", cursor.getInt(cursor.getColumnIndex("p2win")));
		bundle.putInt("p2lose", cursor.getInt(cursor.getColumnIndex("p2lose")));
		bundle.putInt("p2jds", cursor.getInt(cursor.getColumnIndex("p2jds")));
		
		bundle.putString("p3name", cursor.getString(cursor.getColumnIndex("p3name")));
		bundle.putInt("p3sum", cursor.getInt(cursor.getColumnIndex("p3sum")));
		bundle.putInt("p3win", cursor.getInt(cursor.getColumnIndex("p3win")));
		bundle.putInt("p3lose", cursor.getInt(cursor.getColumnIndex("p3lose")));
		bundle.putInt("p3jds", cursor.getInt(cursor.getColumnIndex("p3jds")));
		
		bundle.putString("p4name", cursor.getString(cursor.getColumnIndex("p4name")));
		bundle.putInt("p4sum", cursor.getInt(cursor.getColumnIndex("p4sum")));
		bundle.putInt("p4win", cursor.getInt(cursor.getColumnIndex("p4win")));
		bundle.putInt("p4lose", cursor.getInt(cursor.getColumnIndex("p4lose")));
		bundle.putInt("p4jds", cursor.getInt(cursor.getColumnIndex("p4jds")));
		
		cursor.close();
		
		//每局的详细记录也传到bundle中
		strSQL = "SELECT rowid, * FROM TableGameRec where riqi='" + riqi + "'";
		cursor =  db.rawQuery(strSQL, null);
		if(0==cursor.getCount())
		{
			return;
		}
		
		mylist = new ArrayList<HashMap<String,String>>();
		int sn = 0;
		
		if(cursor.moveToFirst())
		{
			do{
				sn++;
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("SN", Utility.FormatData1To9(String.valueOf(sn)));
				map.put("p1value", String.valueOf(cursor.getInt(cursor.getColumnIndex("p1value"))));
				map.put("p2value", String.valueOf(cursor.getInt(cursor.getColumnIndex("p2value"))));
				map.put("p3value", String.valueOf(cursor.getInt(cursor.getColumnIndex("p3value"))));
				map.put("p4value", String.valueOf(cursor.getInt(cursor.getColumnIndex("p4value"))));
				map.put("shijian", cursor.getString(cursor.getColumnIndex("shijian")));			
				mylist.add(map);
			}while(cursor.moveToNext());
		}
		cursor.close();
		bundle.putSerializable("xlist", mylist);
		
		Intent it=new Intent(MainActivity.this, Activity_NewGame.class);
		it.putExtras(bundle);		
		startActivity(it);
		finish();		
	}
	
	public void onInfoInputComplete(String p1Name, String p2Name, String p3Name, String p4Name, String jdvalue)
	{
		//将游戏初始信息存入数据库中
		Utility.SaveGameRiqi();
		save2DB(p1Name, p2Name, p3Name, p4Name, Integer.valueOf(jdvalue), Utility.LoadGameRiqi());
		
		String str = p1Name + "-" + p2Name + "-" + p3Name + "-" + p4Name + "-" + jdvalue;
		Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
		Bundle bundle=new Bundle();
		bundle.putString("gameflag", "newgame");	//选择开始一局新的游戏
		bundle.putString("p1name", p1Name);
		bundle.putString("p2name", p2Name);
		bundle.putString("p3name", p3Name);
		bundle.putString("p4name", p4Name);
		bundle.putInt("jdvalue", Integer.valueOf(jdvalue));
		bundle.putString("riqi", Utility.LoadGameRiqi());
		Intent it=new Intent(MainActivity.this, Activity_NewGame.class);
		it.putExtras(bundle);		
		startActivity(it);
		finish();
	}
	
	private void save2DB(String p1name, String p2name, String p3name, String p4name, int jds, String riqi)
	{
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(this, "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		db.execSQL("insert into TableGameInfo(p1name, p2name, p3name, p4name, jdvalue, riqi) values(?, ?, ?, ?, ?, ?)", new Object[]{p1name, p2name, p3name, p4name, jds, riqi});
		db.close();
		Toast.makeText(MyApplication.getContext(), "游戏信息已存入数据库中", Toast.LENGTH_SHORT).show();	
		
		//从数据库中读取游戏信息以检查是否正确写入
		checkGameinfo();
	}
	
	private void checkGameinfo()
	{
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(this, "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		
		Cursor cursor = db.rawQuery("select * from TableGameInfo", null);
		if(cursor.moveToFirst())
		{
			do
			{
				String p1name = cursor.getString(cursor.getColumnIndex("p1name"));
				String p2name = cursor.getString(cursor.getColumnIndex("p2name"));
				String p3name = cursor.getString(cursor.getColumnIndex("p3name"));
				String p4name = cursor.getString(cursor.getColumnIndex("p4name"));
				int jds = cursor.getInt(cursor.getColumnIndex("jdvalue"));
				String riqi = cursor.getString(cursor.getColumnIndex("riqi"));
				Log.d("roger", "东：" + p1name);
				Log.d("roger", "南：" + p2name);
				Log.d("roger", "西：" + p3name);
				Log.d("roger", "北：" + p4name);
				Log.d("roger", "金顶数：" + jds);
				Log.d("roger", "日期：" + riqi);
			}while(cursor.moveToNext());
		}
		cursor.close();
	}
}

