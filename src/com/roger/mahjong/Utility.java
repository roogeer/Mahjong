package com.roger.mahjong;

import java.util.Date;

import android.content.SharedPreferences;
import android.util.Log;


public class Utility {

	public static void SaveGameRiqi()
	{
		String riqi = (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date());
		Log.d("roger", riqi);
		
		SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("GameInfo", android.content.Context.MODE_PRIVATE).edit();
		editor.putString("LastGameRiqi", riqi);
		editor.commit();
	}
	
	public static String LoadGameRiqi()
	{
		SharedPreferences pref = MyApplication.getContext().getSharedPreferences("GameInfo", android.content.Context.MODE_PRIVATE);
		return pref.getString("LastGameRiqi", "");
	}
	
	public static int GetTextSizeFactor()
	{
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
