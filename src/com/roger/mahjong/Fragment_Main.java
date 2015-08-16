package com.roger.mahjong;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class Fragment_Main extends Fragment {
	// TODO Auto-generated constructor stub
	private TextView tvMain;
	
	public void onCreate(Bundle savedInstanceState)
	{
		//System.out.println("Fragmen_Main-->onCreate");
		Log.i("Fragment_main", "-->onCreate");
		super.onCreate(savedInstanceState);
	}
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Log.i("Fragment_main", "-->onCreateView");
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();

		return new MySurfaceView4Main(this.getActivity(), activity_NewGame.PlayerInfoP1, activity_NewGame.PlayerInfoP2, activity_NewGame.PlayerInfoP3, activity_NewGame.PlayerInfoP4);		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		//为了指示宿主activity，当前显示的是哪个fragment
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		activity_NewGame.FragmentFlag=0;		
	}
	
	public void onStop()
	{
		Log.i("Fragment_main", "-->onStop");		
		super.onStop();
	}
	
	public void updata()
	{
		Log.i("roger", "当前页面是[主]");		
	}
}
