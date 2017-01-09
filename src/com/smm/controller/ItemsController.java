package com.smm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.smm.exception.CustomException;
import com.smm.po.ItemCustom;
import com.smm.po.ItemQueryVo;
import com.smm.service.ItemService;
import com.smm.validation.ValidGroup1;


/**
 * 
 * <p>Title: ItemsController</p>
 * <p>Description: 商品的controller</p>
 * <p>Company: </p>
 * @author RainFossil
 * @date 2017年1月4日 上午10:59:34
 * @version 1.0.0
 */
@Controller
//对url进行分类管理，此处定义url根路径，访问路径为根路径+子路径
@RequestMapping("/item")
public class ItemsController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request,ItemQueryVo itemQueryVo) throws Exception{
		//测试forward request域是否共享
		System.out.println(request.getParameter("id"));
		//调用service查询商品列表
		List<ItemCustom> li=itemService.findItemList(itemQueryVo);
		ModelAndView mav=new ModelAndView();
		//相当于request.setAttribute();
		mav.addObject("itemList", li);
		//指定视图
		mav.setViewName("items/itemlist.jsp");
		
		return mav;
	}
	/*//商品信息修改页面显示
	//@RequestMapping("/editItem")
	@RequestMapping(value="/queryItems",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView editItem() throws Exception{
		//调用service根据id查询商品详情
		ItemCustom itemCustom=itemService.findItemById(1);
		ModelAndView mav=new ModelAndView();
		//相当于request.setAttribute();
		mav.addObject("itemCustom", itemCustom);
		//指定视图
		mav.setViewName("items/editItem.jsp");
		
		return mav;
	}*/
	// 商品信息修改页面显示[返回string]
	// @RequestMapping("/editItem")
	//@RequestParam里边指定传入参数名称和形参进行绑定
	//通过required属性指定参数是否必输
	//通过defaultValue可以设置默认值，如果id没有传入，将默认值和形参进行绑定
	@RequestMapping(value = "/editItem", method = { RequestMethod.POST, RequestMethod.GET })
	public String editItem(Model model,@RequestParam(value="id",required=true,defaultValue="1")Integer id) throws Exception {
		// 调用service根据id查询商品详情
		ItemCustom itemCustom = itemService.findItemById(id);
		//异常测试，如果itemCustom为null，抛出自定义异常
		if(itemCustom == null){
			throw new CustomException("修改的商品不存在！");
		}
		//相当于mav.addObject("itemCustom", itemCustom) 通过形参model将数据传到页面
		model.addAttribute("itemCustom", itemCustom);

		return "items/editItem.jsp";
	}
	// 商品信息修改页面显示
	//返回void的话可以再加一个参数response，和servlet一样
	//校验 在需要校验的pojo前添加@Validated，在要校验的pojo后添加,BindingResult bindingResult接收错误信息
	//注意：@Validated和BindingResult是配对出现，并且顺序是固定的（一前一后）
	//value={ValidGroup1.class指定使用ValidGroup1分组的校验
	//@ModelAttribute可以指定pojo回显到页面在request中的key
	@RequestMapping("/editItemSubmit")
	public String editItemSubmit(Model model,HttpServletRequest request,Integer id,
			@ModelAttribute("itemCustom")@Validated(value={ValidGroup1.class}) ItemCustom itemCustom,BindingResult bindingResult,
			MultipartFile items_pic//接收商品图片
			) throws Exception {
		//获取错误校验信息
		if(bindingResult.hasErrors()){
			List<ObjectError> li=bindingResult.getAllErrors();
			for(ObjectError oe:li){
				System.out.println(oe.getDefaultMessage());//输出错误信息
			}
			model.addAttribute("allErrors", li);
			return "items/editItem.jsp";
		}
		//上传图片
		if(items_pic != null){
			//存储图片的物理路径
			String  pic_path = "D:\\other\\ide\\upload\\tmp\\";
			//原始名称
			String originName = items_pic.getOriginalFilename();
			//新的图片名称
			String newName = UUID.randomUUID() + originName.substring(originName.lastIndexOf("."));
			//新图片
			File file = new File(pic_path+newName);
			//将内存中的数据写入磁盘
			items_pic.transferTo(file);
		}
		
		
		// 调用service更新商品信息，页面需要传递商品信息到此方法
		itemService.updateItemById(id, itemCustom);
		
		
		//重定向无需添加根路径，它们在同一个controller中
//		return "redirect:queryItems.action";
		//转发forward 地址栏不变，request共享
		return "forward:queryItems.action";
	}
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[]item_id) throws Exception {
		//调用service批量删除商品信息
		
		return "success.jsp";
	}
	//批量修改商品页面
	@RequestMapping("/editItemsLi")
	public String editItemsLi(Model model) throws Exception {
		//调用service查询商品列表
		List<ItemCustom> li=itemService.findItemList(null);
		model.addAttribute("itemList", li);
		return "items/edititemli.jsp";
	}

	// 批量修改商品提交页面
	//通过itemQueryVo接收批量提交的商品信息，将商品信息存储到itemQueryVo的itemList中
	@RequestMapping("/editItemsLiSubmit")
	public String editItemsLiSubmit(ItemQueryVo itemQueryVo) throws Exception {
		// 调用service批量删除商品信息

		return "success.jsp";
	}
	//itemTypes表示最终将方法的返回值放在request中的key
	@ModelAttribute("itemTypes")
	public Map<String,String> getItemTypes(){
		Map<String,String> itemTypes = new HashMap<String,String>();
		itemTypes.put("101", "电子");
		itemTypes.put("102", "母婴");
		return itemTypes;
	}
	//RESTfull测试
	//@RequestMapping("/itemView/{id}")中的id标示将这个位置的参数传递到(@PathVariable指定名称中
	@RequestMapping("/itemView/{id}")
	public @ResponseBody ItemCustom itemView(@PathVariable("id")Integer id) throws Exception{
		return itemService.findItemById(id);
	}
}
