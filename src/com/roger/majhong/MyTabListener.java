package com.roger.majhong;

//import com.roger.actionbar.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;

public class MyTabListener implements TabListener {
	private Fragment fragment;
	
	public MyTabListener(Fragment fragment) {
		// TODO Auto-generated constructor stub
		this.fragment=fragment;
	}
	
	public void onTabReselected(Tab tab, FragmentTransaction ft)
	{
		
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft)
	{
		ft.replace(R.id.context, fragment,null);
	}
	
	public void onTabUnselected(Tab tab, FragmentTransaction ft)
	{
		ft.remove(fragment);
	}
}
