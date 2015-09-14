package com.roger.mahjong;

import com.google.gson.Gson;

public class PostInfoWithRiqi {
	public String GsonStr;
	private TableGameInfo_Item tableGameInfo_Item;
	private TableGameRec_Items tablegGameRec_Items;
	
	public PostInfoWithRiqi(String aRiqi)
	{
		this.tableGameInfo_Item = new TableGameInfo_Item(aRiqi);
		this.tablegGameRec_Items = new TableGameRec_Items(aRiqi);
		
		Gson gson = new Gson();
		String GameInfoGSON = gson.toJson(this.tableGameInfo_Item);
		String GameRecGSON = gson.toJson(this.tablegGameRec_Items);
		this.GsonStr = "Gameinfo="+GameInfoGSON+"GameRecs="+GameRecGSON;
		//this.GsonStr = "Gameinfo="+this.tableGameInfo_Item.GsonStr+"GameRecs="+this.tablegGameRec_Items.GsonStr;
	}
}
