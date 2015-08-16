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

		/*
		Display display = getWindowManager().getDefaultDisplay();
		Point point = new Point();
		display.getSize(point);
		setTitle("��Ļ�ֱ���Ϊ��"+point.x+'*'+point.y);
		*/
		
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
		//int id = item.getItemId();
		//if (id == R.id.action_exit) {
		//	return true;
		//}
		return super.onOptionsItemSelected(item);
	}

	public void NewGameOnClick(View v)
	{
		//Toast.makeText(getApplicationContext(), "�µĹ���", Toast.LENGTH_SHORT).show();

		DialogFragment_PlayerInfo dialogFragment_info = new DialogFragment_PlayerInfo();
		dialogFragment_info.show(getFragmentManager(), "�µĹ���");
	}

	
	public void OldMemoryOnClick(View v)
	{
		//Toast.makeText(getApplicationContext(), "�ɵĻ���", Toast.LENGTH_SHORT).show();
		ArrayList<HashMap<String, String>> memlist;
		
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(this, "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		String strSQL = "SELECT rowid, riqi, p1name, p1sum, p2name, p2sum, p3name, p3sum, p4name, p4sum FROM ViewGameInfo ORDER BY rowid DESC";
		Cursor cursor =  db.rawQuery(strSQL, null);
		if(0==cursor.getCount())
		{
			return;
		}
		
		memlist = new ArrayList<HashMap<String,String>>();
		Bundle bundle = new Bundle();
		bundle.putString("gameflag", "oldmemory");
		
		if(cursor.moveToFirst())
		{
			do
			{
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("p1Name", cursor.getString(cursor.getColumnIndex("p1name")));				
				map.put("p1LoseWin", String.valueOf(cursor.getInt(cursor.getColumnIndex("p1sum"))));
				map.put("p2Name", cursor.getString(cursor.getColumnIndex("p2name")));				
				map.put("p2LoseWin", String.valueOf(cursor.getInt(cursor.getColumnIndex("p2sum"))));
				map.put("p3Name", cursor.getString(cursor.getColumnIndex("p3name")));				
				map.put("p3LoseWin", String.valueOf(cursor.getInt(cursor.getColumnIndex("p3sum"))));
				map.put("p4Name", cursor.getString(cursor.getColumnIndex("p4name")));				
				map.put("p4LoseWin", String.valueOf(cursor.getInt(cursor.getColumnIndex("p4sum"))));
				map.put("riqi", cursor.getString(cursor.getColumnIndex("riqi")));			
				memlist.add(map);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();

		bundle.putSerializable("memlist", memlist);
				
		Intent it=new Intent(MainActivity.this, MemoryActivity.class);
		it.putExtras(bundle);
		startActivity(it);
		//finish();
	}
	
	public void GoOnClick(View v)
	{
		String riqi = Utility.LoadGameRiqi();
		
		ArrayList<HashMap<String, String>> mylist;
		
		//Toast.makeText(getApplicationContext(), "�õļ���", Toast.LENGTH_SHORT).show();
		//�����ݿ��ж�ȡ��¼����װ�ú�����Activity_NewGame
		
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
		bundle.putInt("p1jds", cursor.getInt(cursor.getColumnIndex("p1jds")));

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
		
		//ÿ�ֵ���ϸ��¼Ҳ����bundle��
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
		db.close();
		bundle.putSerializable("xlist", mylist);
		
		Intent it=new Intent(MainActivity.this, Activity_NewGame.class);
		it.putExtras(bundle);		
		startActivity(it);
		finish();		
	}
	
	public void onInfoInputComplete(String p1Name, String p2Name, String p3Name, String p4Name, String jdvalue)
	{
		//ȡ���һ�δ����Ϸ��Ϣ���ڣ������Ӧ��TableGameRec��¼Ϊ0����������¼��TableGameInfo����ɾ��
		clearGameInfo();
		
		//����Ϸ��ʼ��Ϣ�������ݿ���
		Utility.SaveGameRiqi();
		save2DB(p1Name, p2Name, p3Name, p4Name, Integer.valueOf(jdvalue), Utility.LoadGameRiqi());
		
		String str = p1Name + "-" + p2Name + "-" + p3Name + "-" + p4Name + "-" + jdvalue;
		//Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
		Bundle bundle=new Bundle();
		bundle.putString("gameflag", "newgame");	//ѡ��ʼһ���µ���Ϸ
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
	
	private void clearGameInfo()
	{
		//���ֻ�Ǵ�������Ϸ��Ϣ����û��һ����Ϸ�����ļ�¼����ɾ��������Ϸ��Ϣ��¼
		int count = 0;
		
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(this, "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();		
	
		Cursor cursor = db.rawQuery("SELECT COUNT(riqi) as count FROM ViewGameInfo WHERE riqi='" + Utility.LoadGameRiqi() + "'", null);
		cursor.moveToFirst();
		count = cursor.getInt(cursor.getColumnIndex("count"));
		Log.d("roger", "��¼������Ϊ��" + count);
		cursor.close();		
		
		if(0==count)
		{
			//û����ϸ����Ϸ������¼������ɾ����һ����Ϸ��Ϣ��¼
			db=dbHelper.getWritableDatabase();
			Log.i("roger", "TableGameInfo��ɾ��ǰ�ļ�¼����Ϊ��" + checkGameInfoCount());
			db.execSQL("DELETE FROM TableGameInfo WHERE riqi='" + Utility.LoadGameRiqi() + "'");
			Log.i("roger", "TableGameInfo��ɾ����ļ�¼����Ϊ��" + checkGameInfoCount());
		}
		db.close();
	}
	
	private void save2DB(String p1name, String p2name, String p3name, String p4name, int jds, String riqi)
	{
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(this, "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		db.execSQL("insert into TableGameInfo(p1name, p2name, p3name, p4name, jdvalue, riqi) values(?, ?, ?, ?, ?, ?)", new Object[]{p1name, p2name, p3name, p4name, jds, riqi});
		db.close();
		//Toast.makeText(MyApplication.getContext(), "��Ϸ��Ϣ�Ѵ������ݿ���", Toast.LENGTH_SHORT).show();	
		
		//�����ݿ��ж�ȡ��Ϸ��Ϣ�Լ���Ƿ���ȷд��
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
				Log.d("roger", "����" + p1name);
				Log.d("roger", "�ϣ�" + p2name);
				Log.d("roger", "����" + p3name);
				Log.d("roger", "����" + p4name);
				Log.d("roger", "������" + jds);
				Log.d("roger", "���ڣ�" + riqi);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
	}
	
	private int checkGameInfoCount()
	{
		int result = 0;
		
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(this, "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		
		Cursor cursor = db.rawQuery("SELECT COUNT(riqi) as count FROM TableGameInfo", null);
		cursor.moveToFirst();
		result = cursor.getInt(cursor.getColumnIndex("count"));
		cursor.close();
		db.close();
		return result;
	}
}

