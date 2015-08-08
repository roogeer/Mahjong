package com.roger.mahjong;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_P1 extends Fragment {
	// TODO Auto-generated constructor stub

	public void onCreate(Bundle savedInstanceState)
	{
		//System.out.println("Fragmen_A-->onCreate");
		Log.i("roger", "Fragment_P1-->onCreate");
		super.onCreate(savedInstanceState);
	}
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		//System.out.println("Fragment_A-->onCreateView");
		Log.i("roger", "Fragment_P1-->onCreateView");


		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		return new MySurfaceView(this.getActivity(), activity_NewGame.PlayerInfoP1);

		/*
		View view = inflater.inflate(R.layout.tab_p1, null);
		return view;
		*/
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		//为了指示宿主activity，当前显示的是哪个fragment
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		activity_NewGame.FragmentFlag=1;
	}

	public void onStop()
	{
		Log.i("roger", "Fragment_P1-->onStop");		
		super.onStop();
	}

	public void updata()
	{
		Log.i("roger", "当前页面是[东]");
		/*
		//这里应该是调用P1页面的重绘方法
		if(fragment!=null)
		{
			Log.i("roger", "this fragment不为null");
		}
		else
		{
			Log.i("roger", "this fragment为null");
		}
		
		Activity_NewGame activity_NewGame = (Activity_NewGame)getActivity();		
		FragmentTransaction ft = activity_NewGame.getFragmentManager().beginTransaction();
		ft.remove(fragment);
		ft.replace(R.id.context, fragment, null);
		ft.commit();
		*/
	}
}
