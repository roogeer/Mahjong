package com.roger.mahjong;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MemoryActivity extends Activity {
	
	private MemoryDataAdapter memoryAdapter = null;
	private ArrayList<HashMap<String, String>> memlist = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory);
		
		ListView listView = (ListView)findViewById(R.id.MemListView);
		Intent it = this.getIntent();
		Bundle bundle = it.getExtras();
		memlist = (ArrayList<HashMap<String,String>>)bundle.getSerializable("memlist");
		
		memoryAdapter = new MemoryDataAdapter(getApplicationContext(), memlist); 
		listView.setAdapter(memoryAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,long id)
			{
				// TODO Auto-generated method stub
				HashMap<String, String> map = memlist.get(position);
				Toast.makeText(MemoryActivity.this ,"—°‘Ò¡À"+map.get("riqi") , Toast.LENGTH_SHORT).show();				
			}
		});
	}
}
