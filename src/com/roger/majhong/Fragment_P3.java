package com.roger.majhong;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_P3 extends Fragment {
	public void onCreate(Bundle savedInstanceState)
	{
		//System.out.println("Fragment_C-->onCreate");
		Log.i("Fragment_P3", "-->onCreate");
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//System.out.println("Fragment_C--->onCreateView");
		Log.i("Fragment_P3", "-->onCreateView");
		//return inflater.inflate(R.layout.tab_p3, container, false);
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		//return new MySurfaceView(this.getActivity(), activity_NewGame.intP3Sum);
		//return new MySurfaceView(this.getActivity(), activity_NewGame.PlayerInfoP3.LostWin);
		return new MySurfaceView(this.getActivity(), activity_NewGame.PlayerInfoP3);		
	}
	
	public void onStop()
    {
		Log.i("Fragment_P3", "-->onStop");
        super.onStop();
    }	
}
