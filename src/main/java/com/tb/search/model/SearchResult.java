package com.tb.search.model;

import java.util.List;

/**
 * @author acer11
 *  作者：
* 创建时间：2017年6月20日 下午5:09:55  
* 项目名称：tb-search  
* 文件名称：SearchResult.java  
* 类说明：搜索模型数据
 */
public class SearchResult {
	
	private Long total;
	private List<?> list;
	
	public SearchResult(){}
	
	public SearchResult(Long total, List<?> list) {
		this.total = total;
		this.list = list;
	}
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}

}
