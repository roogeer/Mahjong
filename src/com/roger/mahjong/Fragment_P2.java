package com.roger.mahjong;

import android.R.integer;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_P2 extends Fragment {
	public void onCreate(Bundle savedInstanceState)
	{
		//System.out.println("EditFragment-->onCreate");
		Log.i("Fragment_P2", "-->onCreate");
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//System.out.println("EidtFragment--->onCreateView");
		Log.i("Fragment_P2", "-->onCreateView");
		//return inflater.inflate(R.layout.tab_p2, container, false);
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		//return new MySurfaceView(this.getActivity(), activity_NewGame.intP2Sum);
		//return new MySurfaceView(this.getActivity(), activity_NewGame.PlayerInfoP2.LostWin);
		return new MySurfaceView(this.getActivity(), activity_NewGame.PlayerInfoP2);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		//为了指示宿主activity，当前显示的是哪个fragment
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		activity_NewGame.FragmentFlag=2;		
	}

	public void onStop()
    {
		Log.i("Fragment_P2", "-->onStop");
        super.onStop();
    }
	
	public void updata()
	{
		Log.i("roger", "当前页面是[南]");		
	}	
}
