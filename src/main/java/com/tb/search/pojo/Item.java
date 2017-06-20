package com.tb.search.pojo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.beans.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author acer11 作者：haoxd 创建时间：2017年1月23日 上午9:47:09 项目名称：tb-manager-pojo
 * @version 1.0 文件名称：Item.java 类说明：商品
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Item {
	
	@Field("id")
	private Long id;

	@Field("title")
	private String title;
	
	@Field("sell_point")
	private String sellPoint;
	
	@Field("price")
	private Long price;


	@Field("image")
	private String image;

	@Field("cid")
	private Long cid;

	@Field("status")
	private Integer status;
	@Field("create_time")
	private Long createTime;
	@Field("update_time")
	private Long updateTime;
	
	
	



	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}


	public void setImage(String image) {
		this.image = image;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}
	public String[] getimages(){
		return StringUtils.split(this.getImage(), ",");
	} 
	
	

	
}
