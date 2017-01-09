package com.smm.po;

import java.util.List;

public class ItemQueryVo {
	
	private Item item;
	//对原始生成的po进行扩展
	private ItemCustom itemCustom;
	//存储批量商品信息
	private List<ItemCustom> itemList;
	//map的绑定页面使用 成员变量名xxxMap["key"]
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public ItemCustom getItemCustom() {
		return itemCustom;
	}
	public void setItemCustom(ItemCustom itemCustom) {
		this.itemCustom = itemCustom;
	}
	public List<ItemCustom> getItemList() {
		return itemList;
	}
	public void setItemList(List<ItemCustom> itemList) {
		this.itemList = itemList;
	}
	
}
