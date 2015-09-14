package com.roger.mahjong;

import com.google.gson.Gson;

public class PostInfoWithRiqi {
	public String GsonStr;
	public String GsonInfo;
	public String GsonRec;
	private TableGameInfo_Item tableGameInfo_Item;
	private TableGameRec_Items tablegGameRec_Items;
	
	public PostInfoWithRiqi(String aRiqi)
	{
		this.tableGameInfo_Item = new TableGameInfo_Item(aRiqi);
		this.tablegGameRec_Items = new TableGameRec_Items(aRiqi);
		
		Gson gson = new Gson();
		GsonInfo = gson.toJson(this.tableGameInfo_Item);
		GsonRec = gson.toJson(this.tablegGameRec_Items);
		this.GsonStr = "Gameinfo="+GsonInfo+"GameRecs="+GsonRec;
		//this.GsonStr = "Gameinfo="+this.tableGameInfo_Item.GsonStr+"GameRecs="+this.tablegGameRec_Items.GsonStr;
	}
}
