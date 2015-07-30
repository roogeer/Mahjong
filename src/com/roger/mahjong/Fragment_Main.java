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
		//System.out.println("Fragment_Main-->onCreateView");
		Log.i("Fragment_main", "-->onCreateView");
		//return inflater.inflate(R.layout.tab_main,container,false);
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();
		//int p1SumValue = activity_NewGame.intP1Sum;
		//int p2SumValue = activity_NewGame.intP2Sum;
		//int p3SumValue = activity_NewGame.intP3Sum;
		//int p4SumValue = activity_NewGame.intP4Sum;
		int p1SumValue = activity_NewGame.PlayerInfoP1.LoseWin;
		int p2SumValue = activity_NewGame.PlayerInfoP2.LoseWin;
		int p3SumValue = activity_NewGame.PlayerInfoP3.LoseWin;
		int p4SumValue = activity_NewGame.PlayerInfoP4.LoseWin;		

		return new MySurfaceView4Main(this.getActivity(), activity_NewGame.PlayerInfoP1, activity_NewGame.PlayerInfoP2, activity_NewGame.PlayerInfoP3, activity_NewGame.PlayerInfoP4);		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		//Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		//updata();
		//activity_NewGame.FragmentFlag=0;		
	}
	
	public void onStop()
	{
		Log.i("Fragment_main", "-->onStop");		
		super.onStop();
	}
	
	public void updata()
	{
		/*
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();
		tvMain = (TextView)getView().findViewById(R.id.tvMain);
		String str = activity_NewGame.playerOneName + ": " + String.valueOf(activity_NewGame.intP1Sum) + "\n";
		str +=activity_NewGame.playerTwoName + ": " + String.valueOf(activity_NewGame.intP2Sum) + "\n";
		str +=activity_NewGame.playerThreeName + ": " + String.valueOf(activity_NewGame.intP3Sum) + "\n";		
		str +=activity_NewGame.playerFourName + ": " + String.valueOf(activity_NewGame.intP4Sum);		
		tvMain.setText(str);
		*/		
	}
}
