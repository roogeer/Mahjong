package com.roger.mahjong;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MemoryActivity extends Activity {
	
	private MemoryDataAdapter memoryAdapter = null;
	private ArrayList<HashMap<String, String>> memlist = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory);
		
		ListView listView = (ListView)findViewById(R.id.MemListView);
		Intent it = this.getIntent();
		Bundle bundle = it.getExtras();
		memlist = (ArrayList<HashMap<String,String>>)bundle.getSerializable("memlist");
		
		memoryAdapter = new MemoryDataAdapter(getApplicationContext(), memlist); 
		listView.setAdapter(memoryAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,long id)
			{
				// TODO Auto-generated method stub
				HashMap<String, String> maptemp = memlist.get(position);
				String riqi = maptemp.get("riqi");
				ArrayList<HashMap<String, String>> mylist;
				
				MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(MyApplication.getContext(), "mahjong.db", 1);
				SQLiteDatabase db=dbHelper.getWritableDatabase();
				String strSQL = "SELECT * FROM ViewGameInfo WHERE riqi='" + riqi + "'";
				Cursor cursor =  db.rawQuery(strSQL, null);
				if(0==cursor.getCount())
				{
					return;
				}
				
				cursor.moveToFirst();
				Bundle bundle = new Bundle();
				bundle.putString("gameflag", "oldmemory");				
				
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
				db.close();
				bundle.putSerializable("xlist", mylist);
				
				Intent it=new Intent(MemoryActivity.this, Activity_NewGame.class);
				it.putExtras(bundle);
				startActivity(it);
			}
		});
	}
}
