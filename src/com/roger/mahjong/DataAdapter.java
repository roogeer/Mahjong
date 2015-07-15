package com.roger.mahjong;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DataAdapter extends BaseAdapter {
	private Context c = null;
	private LayoutInflater myInflater = null;
	private ArrayList<HashMap<String, String>> myList = null;
	private int tempNum;	//玩家的数据值，用于临时判断｛正，负，零｝
		
	
	public DataAdapter(Context myContext, ArrayList<HashMap<String, String>> iList) {
		// TODO Auto-generated constructor stub
		c = myContext;
		myList = iList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return myList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder =new ViewHolder();
		if(convertView == null)
		{
			myInflater = LayoutInflater.from(c);
			convertView = myInflater.inflate(R.layout.my_listitem, null);
			
			int textsize = Utility.GetTextSizeFactor((Activity)c);
			Log.i("roger", String.valueOf(textsize));
			
			holder.tvSN = (TextView)convertView.findViewById(R.id.SN);
			holder.tvSN.setTextSize(textsize);
			
			holder.tvPlayerOneData = (TextView)convertView.findViewById(R.id.playerOneData);
			holder.tvPlayerOneData.setTextSize(textsize);
			
			holder.tvPlayerTwoData = (TextView)convertView.findViewById(R.id.playerTwoData);
			holder.tvPlayerTwoData.setTextSize(textsize);
			
			holder.tvPlayerThreeData = (TextView)convertView.findViewById(R.id.playerThreeData);
			holder.tvPlayerThreeData.setTextSize(textsize);
			
			holder.tvPlayerFourData = (TextView)convertView.findViewById(R.id.playerFourData);
			holder.tvPlayerFourData.setTextSize(textsize);
			
			holder.tvTimeData = (TextView)convertView.findViewById(R.id.timeData);
			holder.tvTimeData.setTextSize(textsize);
			
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
		
		
		HashMap<String, String> map =myList.get(position);
	
		holder.tvSN.setText(map.get("SN"));
		
		tempNum =Integer.parseInt(map.get("playerOneData"));
		if(tempNum < 0)
		{
			tempNum *=(-1);
			holder.tvPlayerOneData.setTextColor(Color.rgb(0, 231, 0));
		}
		else if(tempNum > 0)
		{
			holder.tvPlayerOneData.setTextColor(Color.rgb(255, 60, 57));			
		}
		else
		{
			holder.tvPlayerOneData.setTextColor(Color.rgb(231, 231, 231));
		}
		holder.tvPlayerOneData.setText(String.valueOf(tempNum));
		
		tempNum =Integer.parseInt(map.get("playerTwoData"));		
		if(tempNum < 0)
		{
			tempNum *=(-1);
			holder.tvPlayerTwoData.setTextColor(Color.rgb(0, 231, 0));
		}
		else if(tempNum > 0)
		{
			holder.tvPlayerTwoData.setTextColor(Color.rgb(255, 60, 57));			
		}
		else
		{
			holder.tvPlayerTwoData.setTextColor(Color.rgb(231, 231, 231));
		}
		holder.tvPlayerTwoData.setText(String.valueOf(tempNum));
		
		tempNum =Integer.parseInt(map.get("playerThreeData"));		
		if(tempNum < 0)
		{
			tempNum *=(-1);
			holder.tvPlayerThreeData.setTextColor(Color.rgb(0, 231, 0));
		}
		else if(tempNum > 0)
		{
			holder.tvPlayerThreeData.setTextColor(Color.rgb(255, 60, 57));			
		}
		else
		{
			holder.tvPlayerThreeData.setTextColor(Color.rgb(231, 231, 231));
		}
		holder.tvPlayerThreeData.setText(String.valueOf(tempNum));
		
		tempNum =Integer.parseInt(map.get("playerFourData"));		
		if(tempNum < 0)
		{
			tempNum *=(-1);
			holder.tvPlayerFourData.setTextColor(Color.rgb(0, 231, 0));
		}
		else if(tempNum > 0)
		{
			holder.tvPlayerFourData.setTextColor(Color.rgb(255, 60, 57));			
		}
		else
		{
			holder.tvPlayerFourData.setTextColor(Color.rgb(231, 231, 231));
		}
		holder.tvPlayerFourData.setText(String.valueOf(tempNum));
		
		holder.tvTimeData.setText(map.get("timeData"));
		
		return convertView;
	}

	private class ViewHolder
	{
		TextView tvSN;
		TextView tvPlayerOneData;
		TextView tvPlayerTwoData;
		TextView tvPlayerThreeData;
		TextView tvPlayerFourData;
		TextView tvTimeData;
	}
}
