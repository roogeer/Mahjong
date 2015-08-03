package com.roger.mahjong;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_Detail extends Fragment {
	// TODO Auto-generated constructor stub
	//private TextView tvDetail;
	private int dataCount;
	private DataAdapter adapter = null;	
	
	public void onCreate(Bundle savedInstanceState)
	{
		//System.out.println("Fragmen_Detail-->onCreate");
		Log.i("roger", "Fragment_Detail-->onCreate");
		super.onCreate(savedInstanceState);
	}
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//System.out.println("Fragment_Detail-->onCreateView");
		Log.i("roger", "Fragment_Detail-->onCreateView");
		return inflater.inflate(R.layout.tab_detail,container,false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("roger", "Fragment_Detail-->onActivityCreated");
		
		//为了指示宿主activity，当前显示的是哪个fragment		
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		activity_NewGame.FragmentFlag=5;
		
		dataCount = activity_NewGame.mylist.size();
		
		ListView listView = (ListView)getView().findViewById(R.id.MyListView);

		adapter = new DataAdapter(getActivity(), activity_NewGame.mylist);
		listView.setAdapter(adapter);
	}
	
	
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("roger", "onPause() in Fragment_Detail");
	}


	public void onStop()
	{
		Log.i("roger", "Fragment_Detail-->onStop");		
		super.onStop();
	}
	
	public void updata()
	{
		Log.i("roger", "当前页面是[详]");
	}	
}
