package com.roger.mahjong;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;


public class Utility {

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
