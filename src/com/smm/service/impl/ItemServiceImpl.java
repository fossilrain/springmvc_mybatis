package com.smm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.smm.mapper.ItemMapper;
import com.smm.mapper.ItemMapperCustom;
import com.smm.po.Item;
import com.smm.po.ItemCustom;
import com.smm.po.ItemQueryVo;
import com.smm.service.ItemService;

public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapperCustom itemMapperCustom;
	@Autowired
	private ItemMapper itemMapper;
	
	@Override
	public List<ItemCustom> findItemList(ItemQueryVo itemQueryVo) throws Exception {
		
		return itemMapperCustom.findItemList(itemQueryVo);
	}

	@Override
	public ItemCustom findItemById(Integer id) throws Exception {
		Item item = itemMapper.selectByPrimaryKey(id);
		if(item == null){
			return null;
		}
		//中间对商品信息进行处理
		//...业务代码
		//最终返回ItemCustom
		ItemCustom itemCustom = new ItemCustom();
		//将item属性值拷贝到itemCustom
		BeanUtils.copyProperties(item, itemCustom);
		return itemCustom;
	}

	@Override
	public void updateItemById(Integer id, ItemCustom itemCustom) throws Exception {
		//添加业务校验，通常在service接口中对关键参数进行校验
		//校验id是否为空，为空就抛出异常
		
		//更新商品信息
		itemCustom.setId(id);
		itemMapper.updateByPrimaryKeySelective(itemCustom);
	}

}
