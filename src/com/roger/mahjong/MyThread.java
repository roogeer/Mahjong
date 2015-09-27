package com.roger.mahjong;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class MyThread extends Thread {
	public PostInfoWithRiqi postInfoWithRiqi;
	
	public MyThread(PostInfoWithRiqi aPostInfoWithRiqi)
	{
		this.postInfoWithRiqi = aPostInfoWithRiqi;
	}

	@Override
	
	public void run()
	{
		HttpURLConnection connection = null;
		try
		{
			URL url = new URL("http://192.168.108.99/Mahjong/Mahjong.aspx");
			connection = (HttpURLConnection)url.openConnection();
			connection.setConnectTimeout(8000);
			connection.setReadTimeout(8000);
			connection.setRequestMethod("GET");

			InputStream in = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder response = new StringBuilder();
			String line;
			while((line = reader.readLine())!=null)
			{
				response.append(line);
			}
			Log.i("roger", "GET:"+response.toString());
		}catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			if(connection != null)
				connection.disconnect();
		}
	}
	

	/*
	public void run() {
		// TODO Auto-generated method stub
		HttpURLConnection connection = null;
		try
		{
			String postString = "riqi="+postInfoWithRiqi.Riqi+"&gameinfo="+postInfoWithRiqi.GsonInfo+"&gamerec="+postInfoWithRiqi.GsonRec;			
			
			URL url = new URL("http://192.168.108.99/Mahjong/Mahjong.aspx");
			connection = (HttpURLConnection)url.openConnection();
			connection.setConnectTimeout(8000);
			connection.setReadTimeout(8000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length",String.valueOf(postString.getBytes().length));
			connection.setRequestMethod("POST");

			Log.i("roger", "POST:"+postString);
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(postString);
			out.flush();
			out.close();
			
			InputStream in = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder response = new StringBuilder();
			String line;
			while((line = reader.readLine())!=null)
			{
				response.append(line);
			}
			Log.i("roger", "POST result:"+response.toString());			
			
			int returnCode = connection.getResponseCode();
			Log.i("roger", "·µ»ØÂëÎª£º"+ String.valueOf(returnCode));
			
			//PrintWriter pw = new PrintWriter(connection.getOutputStream());
			//pw.print(postString);
			//pw.flush();
			//pw.close();
		}catch (Exception e)
		{
			e.printStackTrace();
		}finally
		{
			if(connection != null)
				connection.disconnect();
		}
	}
	*/
}
