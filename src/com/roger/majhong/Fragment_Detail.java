package com.roger.majhong;

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
		Log.i("Fragment_Detail", "-->onCreate");
		super.onCreate(savedInstanceState);
	}
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//System.out.println("Fragment_Detail-->onCreateView");
		Log.i("Fragment_Detail", "-->onCreateView");
		return inflater.inflate(R.layout.tab_detail,container,false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("Fragment_Detail", "-->onActivityCreated");		
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		//tvDetail = (TextView)getView().findViewById(R.id.tvDetail);
		//tvDetail.setText(activity_NewGame.strDetail);
		//为了指示宿主activity，当前显示的是哪个fragment		
		activity_NewGame.FragmentFlag=5;
		
		dataCount = activity_NewGame.mylist.size();
		
		ListView listView = (ListView)getView().findViewById(R.id.MyListView);

		adapter = new DataAdapter(getActivity(), activity_NewGame.mylist);
		listView.setAdapter(adapter);
	}

	public void onStop()
	{
		Log.i("Fragment_Detail", "-->onStop");		
		super.onStop();
	}
	
	public void updata()
	{
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();
		//tvDetail = (TextView)getView().findViewById(R.id.tvDetail);
		//String tempString = tvDetail.getText().toString().trim()+"\n"+activity_NewGame.strDetail;
		//Log.i("roger", "before setText");
		//Log.i("roger", activity_NewGame.strDetail);
		//tvDetail.setText(activity_NewGame.strDetail);
		Log.i("roger", "end of setText");
	}	
}
