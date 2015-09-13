package com.roger.mahjong;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TableGameRec_Items {

	class TableGameRec_Item
	{
		int p1value;
		int p2value;
		int p3value;
		int p4value;
		String shijian;
		String riqi;
		
		public TableGameRec_Item(int aP1value, int aP2value, int aP3value, int aP4value, String aShijian, String aRiqi)
		{
			this.p1value = aP1value;
			this.p2value = aP2value;
			this.p3value = aP3value;
			this.p4value = aP4value;
			this.shijian = aShijian;
			this.riqi = aRiqi;
		}
	}
	
	public ArrayList<TableGameRec_Item> listTableGameRecs;
	public String GsonStr;
	
	public TableGameRec_Items(String aRiqi)
	{
		this.listTableGameRecs = new ArrayList<TableGameRec_Items.TableGameRec_Item>();
		
		//从TableGameRec表中提示符合日期的记录
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(MyApplication.getContext(), "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		
		Cursor cursor = db.rawQuery("select * from TableGameRec where riqi='"+ aRiqi +"' order by rowid", null);
		if(cursor.moveToFirst())
		{
			do
			{
				int p1value = cursor.getInt(cursor.getColumnIndex("p1value"));
				int p2value = cursor.getInt(cursor.getColumnIndex("p2value"));
				int p3value = cursor.getInt(cursor.getColumnIndex("p3value"));
				int p4value = cursor.getInt(cursor.getColumnIndex("p4value"));
				String shijian = cursor.getString(cursor.getColumnIndex("shijian"));
				String riqi = cursor.getString(cursor.getColumnIndex("riqi"));
				TableGameRec_Item tableGameRec_Item = new TableGameRec_Item(p1value, p2value, p3value, p4value, shijian, riqi);
				listTableGameRecs.add(tableGameRec_Item);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
		
		Gson gson = new Gson();
		this.GsonStr = gson.toJson(listTableGameRecs);
	}
}