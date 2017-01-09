package com.smm.mapper;

import java.util.List;

import com.smm.po.ItemCustom;
import com.smm.po.ItemQueryVo;

public interface ItemMapperCustom {
	//商品查询列表
	public List<ItemCustom> findItemList(ItemQueryVo itemQueryVo) throws Exception;
}