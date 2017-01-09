package com.smm.service;

import java.util.List;

import com.smm.po.ItemCustom;
import com.smm.po.ItemQueryVo;

/**
 * 
 * <p>Title: ItemService</p>
 * <p>Description: 商品管理service</p>
 * <p>Company: </p>
 * @author RainFossil
 * @date 2017年1月4日 上午9:23:21
 * @version 1.0.0
 */
public interface ItemService {
	//商品查询列表
	public List<ItemCustom> findItemList(ItemQueryVo itemQueryVo) throws Exception;
	/**
	 * 根据id查询商品详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ItemCustom findItemById(Integer id) throws Exception;
	/**
	 * 根据id修改商品信息
	 * @param id 修改商品的id
	 * @param itemCustom 修改的商品信息
	 * @throws Exception
	 */
	public void updateItemById(Integer id,ItemCustom itemCustom) throws Exception;
}
