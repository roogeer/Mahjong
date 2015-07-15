package com.roger.mahjong;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_P1 extends Fragment {
	// TODO Auto-generated constructor stub
	private TextView tvP1;
	
	public void onCreate(Bundle savedInstanceState)
	{
		//System.out.println("Fragmen_A-->onCreate");
		Log.i("Fragment_P1", "onCreate");
		super.onCreate(savedInstanceState);
		
	}
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//System.out.println("Fragment_A-->onCreateView");
		Log.i("Fragment_P1", "onCreateView");
		//return inflater.inflate(R.layout.tab_p1,container,false);
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		//return new MySurfaceView(this.getActivity(), activity_NewGame.intP1Sum);		
		//return new MySurfaceView(this.getActivity(), activity_NewGame.PlayerInfoP1.LostWin);
		return new MySurfaceView(this.getActivity(), activity_NewGame.PlayerInfoP1);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		/*
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();
		tvP1 = (TextView)getView().findViewById(R.id.tvP1);
		tvP1.setText(String.valueOf(activity_NewGame.intP1Data));
		activity_NewGame.intP1Data++;
		//为了指示宿主activity，当前显示的是哪个fragment
		activity_NewGame.FragmentFlag=1;
		*/
	}

	public void onStop()
	{
		Log.i("Fragment_P1", "onStop");		
		super.onStop();
	}
	
	public void updata()
	{
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		tvP1 = (TextView)getView().findViewById(R.id.tvP1);
		tvP1.setText(activity_NewGame.p1Value);
	}
}
