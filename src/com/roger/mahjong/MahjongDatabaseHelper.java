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
			
	final String CREATE_ViewGameInfo = "CREATE VIEW 'ViewGameInfo' AS select b.riqi," +
			"a.p1name,b.p1sum,b.p1win,b.p1lose,b.p1jds," +
			"a.p2name,b.p2sum,b.p2win,b.p2lose,b.p2jds," +
			"a.p3name,b.p3sum,b.p3win,b.p3lose,b.p3jds," +
			"a.p4name,b.p4sum,b.p4win,b.p4lose,b.p4jds from " +
			"(select TableGameRec.riqi," +
			"sum(p1value) as p1sum," +
			"sum(case when p1value>0 then 1 else 0 end) as p1win," +
			"sum(case when p1value<0 then 1 else 0 end) as p1lose," +
			"sum(case when p1value>=TableGameInfo.jdvalue then 1 else 0 end) as p1jds," +
			"sum(p2value) as p2sum," +
			"sum(case when p2value>0 then 1 else 0 end) as p2win," +
			"sum(case when p2value<0 then 1 else 0 end) as p2lose," +
			"sum(case when p2value>=TableGameInfo.jdvalue then 1 else 0 end) as p2jds," +
			"sum(p3value) as p3sum," +
			"sum(case when p3value>0 then 1 else 0 end) as p3win," +
			"sum(case when p3value<0 then 1 else 0 end) as p3lose," +
			"sum(case when p3value>=TableGameInfo.jdvalue then 1 else 0 end) as p3jds," +
			"sum(p4value) as p4sum," +
			"sum(case when p4value>0 then 1 else 0 end) as p4win," +
			"sum(case when p4value<0 then 1 else 0 end) as p4lose," +
			"sum(case when p4value>=TableGameInfo.jdvalue then 1 else 0 end) as p4jds " +
			"from TableGameInfo inner join TableGameRec on TableGameInfo.riqi=TableGameRec.riqi " +
			"group by TableGameRec.riqi) as b " +
			"inner join " +
			"(select riqi,p1name,p2name,p3name,p4name from TableGameInfo) as a " +
			"on a.riqi=b.riqi";
	
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
		
		Log.i("roger", "CREATE_ViewGameInfo ing...");
		Log.i("roger", CREATE_ViewGameInfo);		
		db.execSQL(CREATE_ViewGameInfo);
		Log.i("roger", "CREATE_ViewGameInfo ok");		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		//数据库版本升级时，调用此函数
		Log.d("roger", "-----onUpgrade Called-----");
		Log.d("roger", oldversion+"---->"+newversion);
	}
}
