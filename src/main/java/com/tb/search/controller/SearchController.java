package com.tb.search.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tb.search.model.SearchResult;
import com.tb.search.pojo.Item;
import com.tb.search.service.ItemSearchService;

/**
 * @author acer11
 *  作者：
* 创建时间：2017年6月20日 下午4:41:22  
* 项目名称：tb-search  
* 文件名称：SearchController.java  
* 类说明：搜索
 */
@Controller
@RequestMapping("/S")
public class SearchController {
	
	public static final Integer ROWS=32;//每页数量
	
	@Resource(name="itemSearchService")
	private ItemSearchService itemSearchService;
	
	@RequestMapping(value="/Search",method=RequestMethod.GET)
	public ModelAndView Search(@RequestParam("keyword")String keyword,
			@RequestParam(value="page",defaultValue="1") Integer page){
		
		ModelAndView modelAndView = new ModelAndView("search");
		
		SearchResult searchResult = null;
		try {
			keyword = new String(keyword.getBytes("ISO-8859-1"),"utf-8");
			searchResult = this.itemSearchService.search(keyword,page);
			
		} catch (Exception e) {
			searchResult = new SearchResult(0L,null);
			e.printStackTrace();
		}
		//搜索关键字
		modelAndView.addObject("keyWords", keyword);
		//商品集合
		modelAndView.addObject("itemList", searchResult.getList());
		//页数
		modelAndView.addObject("page", page);
		
		int total = searchResult.getTotal().intValue();
		int pages = total % ROWS ==0 ?total/ROWS :total/ROWS+1;
		//总页数
		modelAndView.addObject("pages", pages);
		
	
		return modelAndView;
		
	}

}
