package com.roger.mahjong;

import java.util.Date;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;


public class Utility {

	public static void SaveGameRiqi()
	{
		String riqi = (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date());
		
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
	
	public static float GetTextSizeFactor(int ActivityWidth)
	{
		float result = 0;
		switch(ActivityWidth)
		{
		case 720:
			result = 1;
			break;
		case 1080:
			result = 1.5f;
			break;			
		}
		return result;
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
