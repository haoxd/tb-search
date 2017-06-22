package com.tb.search.service;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.common.bean.api.RespInfo;
import com.tb.search.pojo.Item;
import com.tb.search.sys.HttpConstants;
import com.tb.search.sys.http.ws.server.intf.HttpClientApiServerTools;

@Service("itemService")
public class ItemService {
	
	@Resource(name="http")
	private HttpClientApiServerTools http;
	
	@Value("${GET_ITEM_INFO_URL}")
	private String GET_ITEM_INFO_URL;
	
	private static final ObjectMapper oMapper = new ObjectMapper();
	
	public Item queryItemById(Long itemId){
		
		try {
		RespInfo resp=	this.http.sendGetNoReadXML(GET_ITEM_INFO_URL+itemId);
		
		if(HttpConstants.HttpStatusCode.OK.equals(resp.getRespCode())){
			String jsonData = (String)resp.getData();
			if(StringUtils.isNotEmpty(jsonData)){
				return oMapper.readValue(jsonData, Item.class);
			}
		}
		} catch (HttpClientErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
}
