package com.roger.mahjong;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

public class TableGameInfo_Item {
	public String p1name;
	public String p2name;
	public String p3name;
	public String p4name;
	public int jdvalue;
	public String riqi;
	//public String GsonStr;

	public TableGameInfo_Item(String aRiqi)
	{
		MahjongDatabaseHelper dbHelper = new MahjongDatabaseHelper(MyApplication.getContext(), "mahjong.db", 1);
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		
		Cursor cursor = db.rawQuery("select * from TableGameInfo where riqi='"+ aRiqi +"'", null);		
		if(cursor.moveToFirst())
		{
			do
			{
				this.p1name = cursor.getString(cursor.getColumnIndex("p1name"));
				this.p2name = cursor.getString(cursor.getColumnIndex("p2name"));
				this.p3name = cursor.getString(cursor.getColumnIndex("p3name"));
				this.p4name = cursor.getString(cursor.getColumnIndex("p4name"));
				this.jdvalue = cursor.getInt(cursor.getColumnIndex("jdvalue"));
				this.riqi = cursor.getString(cursor.getColumnIndex("riqi"));
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();		
		
		/*
		Gson gson = new Gson();
		this.GsonStr = gson.toJson(this, TableGameInfo_Item.class);
		*/
	}
}
