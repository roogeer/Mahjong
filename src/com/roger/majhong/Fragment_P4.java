package com.roger.majhong;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_P4 extends Fragment {
	// TODO Auto-generated constructor stub
	
	public void onCreate(Bundle savedInstanceState)
	{
		//System.out.println("Fragmen_D-->onCreate");
		Log.i("Fragment_P4", "-->onCreate");
		super.onCreate(savedInstanceState);
	}
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//System.out.println("Fragment_D-->onCreateView");
		Log.i("Fragment_P4", "-->onCreateView");
		//return inflater.inflate(R.layout.tab_p4,container,false);
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		//return new MySurfaceView(this.getActivity(), activity_NewGame.intP4Sum);
		//return new MySurfaceView(this.getActivity(), activity_NewGame.PlayerInfoP4.LostWin);
		return new MySurfaceView(this.getActivity(), activity_NewGame.PlayerInfoP4);		
	}

	public void onStop()
	{
		Log.i("Fragment_P4", "-->onStop");		
		super.onStop();
	}
}
