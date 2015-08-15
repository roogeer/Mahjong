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

public class MemoryDataAdapter extends BaseAdapter {
	private Context c = null;
	private LayoutInflater myInflater = null;
	private ArrayList<HashMap<String, String>> memList = null;
	private int tempNum;	//玩家的数据值，用于临时判断｛正，负，零｝
	
	public MemoryDataAdapter(Context myContext, ArrayList<HashMap<String, String>> iList) {
		// TODO Auto-generated constructor stub
		c = myContext;
		memList = iList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return memList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return memList.get(position);
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
		
		HashMap<String, String> map =memList.get(position);
		
		if(convertView == null)
		{
			myInflater = LayoutInflater.from(c);
			convertView = myInflater.inflate(R.layout.oldmem_listitem, null);
			
			int textsize = Utility.GetTextSizeFactor();
			
			holder.tvP1Name = (TextView)convertView.findViewById(R.id.tvP1Name);
			holder.tvP1Name.setTextSize(textsize);
			holder.tvP1Name.setTextColor(Color.GRAY);
			holder.tvP1Name.setText(map.get("p1Name"));

			
			holder.tvP1LoseWin = (TextView)convertView.findViewById(R.id.tvP1LoseWin);
			holder.tvP1LoseWin.setTextSize(textsize);
			
			holder.tvP2Name = (TextView)convertView.findViewById(R.id.tvP2Name);
			holder.tvP2Name.setTextSize(textsize);
			holder.tvP2Name.setTextColor(Color.GRAY);
			holder.tvP2Name.setText(map.get("p2Name"));
			
			holder.tvP2LoseWin = (TextView)convertView.findViewById(R.id.tvP2LoseWin);
			holder.tvP2LoseWin.setTextSize(textsize);
			
			holder.tvP3Name = (TextView)convertView.findViewById(R.id.tvP3Name);
			holder.tvP3Name.setTextSize(textsize);
			holder.tvP3Name.setTextColor(Color.GRAY);
			holder.tvP3Name.setText(map.get("p3Name"));
			
			holder.tvP3LoseWin = (TextView)convertView.findViewById(R.id.tvP3LoseWin);
			holder.tvP3LoseWin.setTextSize(textsize);
			
			holder.tvP4Name = (TextView)convertView.findViewById(R.id.tvP4Name);
			holder.tvP4Name.setTextSize(textsize);
			holder.tvP4Name.setTextColor(Color.GRAY);
			holder.tvP4Name.setText(map.get("p4Name"));
			
			holder.tvP4LoseWin = (TextView)convertView.findViewById(R.id.tvP4LoseWin);
			holder.tvP4LoseWin.setTextSize(textsize);
			
			holder.tvRiqi = (TextView)convertView.findViewById(R.id.riqi);
			holder.tvRiqi.setTextSize(textsize);
			holder.tvRiqi.setTextColor(Color.GRAY);
			
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
		
		tempNum =Integer.parseInt(map.get("p1LoseWin"));
		if(tempNum < 0)
		{
			tempNum *=(-1);
			holder.tvP1LoseWin.setTextColor(Color.rgb(0, 231, 0));
		}
		else if(tempNum > 0)
		{
			holder.tvP1LoseWin.setTextColor(Color.rgb(255, 60, 57));			
		}
		else
		{
			holder.tvP1LoseWin.setTextColor(Color.rgb(231, 231, 231));
		}
		holder.tvP1LoseWin.setText(String.valueOf(tempNum));
		
		tempNum =Integer.parseInt(map.get("p2LoseWin"));		
		if(tempNum < 0)
		{
			tempNum *=(-1);
			holder.tvP2LoseWin.setTextColor(Color.rgb(0, 231, 0));
		}
		else if(tempNum > 0)
		{
			holder.tvP2LoseWin.setTextColor(Color.rgb(255, 60, 57));			
		}
		else
		{
			holder.tvP2LoseWin.setTextColor(Color.rgb(231, 231, 231));
		}
		holder.tvP2LoseWin.setText(String.valueOf(tempNum));
		
		tempNum =Integer.parseInt(map.get("p3LoseWin"));		
		if(tempNum < 0)
		{
			tempNum *=(-1);
			holder.tvP3LoseWin.setTextColor(Color.rgb(0, 231, 0));
		}
		else if(tempNum > 0)
		{
			holder.tvP3LoseWin.setTextColor(Color.rgb(255, 60, 57));			
		}
		else
		{
			holder.tvP3LoseWin.setTextColor(Color.rgb(231, 231, 231));
		}
		holder.tvP3LoseWin.setText(String.valueOf(tempNum));
		
		tempNum =Integer.parseInt(map.get("p4LoseWin"));		
		if(tempNum < 0)
		{
			tempNum *=(-1);
			holder.tvP4LoseWin.setTextColor(Color.rgb(0, 231, 0));
		}
		else if(tempNum > 0)
		{
			holder.tvP4LoseWin.setTextColor(Color.rgb(255, 60, 57));			
		}
		else
		{
			holder.tvP4LoseWin.setTextColor(Color.rgb(231, 231, 231));
		}
		holder.tvP4LoseWin.setText(String.valueOf(tempNum));
		
		holder.tvRiqi.setText(map.get("riqi"));
		
		return convertView;
	}

	private class ViewHolder
	{
		TextView tvP1Name;
		TextView tvP1LoseWin;
		TextView tvP2Name;
		TextView tvP2LoseWin;
		TextView tvP3Name;
		TextView tvP3LoseWin;
		TextView tvP4Name;
		TextView tvP4LoseWin;		
		TextView tvRiqi;		
	}
}
