package com.roger.mahjong;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MahjongDatabaseHelper extends SQLiteOpenHelper {

/*
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
*/
	
	final String CREATE_TableGameInfo = "CREATE TABLE TableGameInfo(" +
			 "p1name TEXT," +
			 "p2name TEXT," +
			 "p3name TEXT," +
			 "p4name TEXT," +
			 "jdvalue INTEGER," +
			 "riqi TEXT," +
			 "PRIMARY KEY(riqi)" +
			 ")";
				
	final String CREATE_TableGameRec = "CREATE  TABLE TableGameRec(" +
			"p1value INTEGER," +
			"p2value INTEGER," +
			"p3value INTEGER," +
			"p4value INTEGER," +
			"shijian TEXT," +
			"riqi TEXT," +
			"FOREIGN KEY(riqi) REFERENCES TableGameInfo(riqi)" +
			")";
			
	public MahjongDatabaseHelper(Context context, String databaseName, int version)
	{
		super(context, databaseName, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//第一次使用数据库时，自动创建表
		Log.i("roger", CREATE_TableGameInfo);
		db.execSQL(CREATE_TableGameInfo);
		Log.i("roger", "TableGameInfo ok");
		Log.i("roger", CREATE_TableGameRec);		
		db.execSQL(CREATE_TableGameRec);
		Log.i("roger", "TableGameRec ok");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		//数据库版本升级时，调用此函数
		Log.d("roger", "-----onUpgrade Called-----");
		Log.d("roger", oldversion+"---->"+newversion);
	}
}
