package com.roger.mahjong;

public class PostInfoWithRiqi {
	public String GsonStr;
	private TableGameInfo_Item tableGameInfo_Item;
	private TableGameRec_Items tablegGameRec_Items;
	
	public PostInfoWithRiqi(String aRiqi)
	{
		this.tableGameInfo_Item = new TableGameInfo_Item(aRiqi);
		this.tablegGameRec_Items = new TableGameRec_Items(aRiqi);
		this.GsonStr = "Gameinfo="+this.tableGameInfo_Item.GsonStr+"GameRecs="+this.tablegGameRec_Items.GsonStr;
	}
}
