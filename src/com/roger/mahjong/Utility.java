package com.roger.mahjong;

import java.util.Date;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;


public class Utility {

	public static void SaveGameRiqi(Activity activity)
	{
		String riqi = (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date());
		Log.d("roger", riqi);
		
		SharedPreferences.Editor editor = activity.getSharedPreferences("GameInfo", android.content.Context.MODE_PRIVATE).edit();
		editor.putString("LastGameRiqi", riqi);
		editor.commit();
	}
	
	public static String LoadGameRiqi(Activity activity)
	{
		SharedPreferences pref =activity.getSharedPreferences("GameInfo", android.content.Context.MODE_PRIVATE);
		return pref.getString("LastGameRiqi", "");
	}
	
	public static int GetTextSizeFactor(Activity activity)
	{
		//Display display = activity.getWindowManager().getDefaultDisplay();
		//Point point = new Point();
		//display.getSize(point);
		//return 20*(point.x/480);	//ÆÁÄ»Êµ¼Ê¿í¶Èpoint.x
		
		return 20;
	}

	public static String FormatData1To9(String numStr)
	{
		String result="";
		int num =Math.abs(Integer.parseInt(numStr));

		if(num < 10)
		{
			result+="0";
		}
		
		result+=String.valueOf(num);
		
		return result;
	}		
}
