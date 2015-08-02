package com.roger.mahjong;

import java.util.ArrayList;
import java.util.HashMap;
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
			
			int textsize = Utility.GetTextSizeFactor();
			
			holder.tvSN = (TextView)convertView.findViewById(R.id.SN);
			holder.tvSN.setTextSize(textsize);
			
			holder.tvP1value = (TextView)convertView.findViewById(R.id.p1value);
			holder.tvP1value.setTextSize(textsize);
			
			holder.tvP2value = (TextView)convertView.findViewById(R.id.p2value);
			holder.tvP2value.setTextSize(textsize);
			
			holder.tvP3value = (TextView)convertView.findViewById(R.id.p3value);
			holder.tvP3value.setTextSize(textsize);
			
			holder.tvP4value = (TextView)convertView.findViewById(R.id.p4value);
			holder.tvP4value.setTextSize(textsize);
			
			holder.tvShijian = (TextView)convertView.findViewById(R.id.shijian);
			holder.tvShijian.setTextSize(textsize);
			
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
		
		
		HashMap<String, String> map =myList.get(position);
	
		holder.tvSN.setText(map.get("SN"));
		
		tempNum =Integer.parseInt(map.get("p1value"));
		if(tempNum < 0)
		{
			tempNum *=(-1);
			holder.tvP1value.setTextColor(Color.rgb(0, 231, 0));
		}
		else if(tempNum > 0)
		{
			holder.tvP1value.setTextColor(Color.rgb(255, 60, 57));			
		}
		else
		{
			holder.tvP1value.setTextColor(Color.rgb(231, 231, 231));
		}
		holder.tvP1value.setText(String.valueOf(tempNum));
		
		tempNum =Integer.parseInt(map.get("p2value"));		
		if(tempNum < 0)
		{
			tempNum *=(-1);
			holder.tvP2value.setTextColor(Color.rgb(0, 231, 0));
		}
		else if(tempNum > 0)
		{
			holder.tvP2value.setTextColor(Color.rgb(255, 60, 57));			
		}
		else
		{
			holder.tvP2value.setTextColor(Color.rgb(231, 231, 231));
		}
		holder.tvP2value.setText(String.valueOf(tempNum));
		
		tempNum =Integer.parseInt(map.get("p3value"));		
		if(tempNum < 0)
		{
			tempNum *=(-1);
			holder.tvP3value.setTextColor(Color.rgb(0, 231, 0));
		}
		else if(tempNum > 0)
		{
			holder.tvP3value.setTextColor(Color.rgb(255, 60, 57));			
		}
		else
		{
			holder.tvP3value.setTextColor(Color.rgb(231, 231, 231));
		}
		holder.tvP3value.setText(String.valueOf(tempNum));
		
		tempNum =Integer.parseInt(map.get("p4value"));		
		if(tempNum < 0)
		{
			tempNum *=(-1);
			holder.tvP4value.setTextColor(Color.rgb(0, 231, 0));
		}
		else if(tempNum > 0)
		{
			holder.tvP4value.setTextColor(Color.rgb(255, 60, 57));			
		}
		else
		{
			holder.tvP4value.setTextColor(Color.rgb(231, 231, 231));
		}
		holder.tvP4value.setText(String.valueOf(tempNum));
		
		holder.tvShijian.setText(map.get("shijian"));
		
		return convertView;
	}

	private class ViewHolder
	{
		TextView tvSN;
		TextView tvP1value;
		TextView tvP2value;
		TextView tvP3value;
		TextView tvP4value;
		TextView tvShijian;
	}
}
