package com.roger.majhong;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MajhongDatabaseHelper extends SQLiteOpenHelper {
	
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
			
	public MajhongDatabaseHelper(Context context, String databaseName, int version)
	{
		super(context, databaseName, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//第一次使用数据库时，自动创建表
		Log.i("roger", CREATE_TABLE_GameRec_LastInfo);
		Log.i("roger", CREATE_TABLE_GameRec_LastDetail);		
		db.execSQL(CREATE_TABLE_GameRec_LastInfo);
		db.execSQL(CREATE_TABLE_GameRec_LastDetail);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		//数据库版本升级时，调用些函数
		System.out.println("-----onUpgrade Called-----");
		System.out.println(oldversion+"---->"+newversion);
	}
}
