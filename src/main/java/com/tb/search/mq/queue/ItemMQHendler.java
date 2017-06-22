package com.tb.search.mq.queue;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.search.pojo.Item;
import com.tb.search.service.ItemService;
import com.tb.search.sys.MQConstant.MQ_MESSAGE_DATA;
import com.tb.search.sys.MQConstant.MQ_SEND_TYPE;

/**
 * @author acer11
 *  作者：
* 创建时间：2017年6月22日 下午3:56:16  
* 项目名称：tb-search  
* 文件名称：ItemMQHendler.java  
* 类说明：搜索系统item消费者
 */
public class ItemMQHendler {
	
	
	private static final ObjectMapper oMapper = new ObjectMapper();
	
	@Autowired
	private HttpSolrServer solr;
	
	@Resource(name="itemService")
	private ItemService itemServic;
	
	/**
	 * 处理消息队列
	 * @param MSG
	 */
	public void execute(String MSG){
		try {
			JsonNode jsonNode = oMapper.readTree(MSG);
			
			Long itemId = jsonNode.get("itemId").asLong();
			String messageType = jsonNode.get(MQ_MESSAGE_DATA.MQ_MESSAGE_TYPE).asText(); 
			if(StringUtils.equals(messageType, MQ_SEND_TYPE.MQ_SEND_ADD) || StringUtils.equals(messageType, MQ_SEND_TYPE.MQ_SEND_UPDATE)){
				Item item = itemServic.queryItemById(itemId);
				this.solr.addBean(item);
				this.solr.commit();
			}else if(StringUtils.equals(messageType, MQ_SEND_TYPE.MQ_SEND_DEL)){
				this.solr.deleteById(String.valueOf(itemId));//删除solr当中的数据
				this.solr.commit();//提交
			}
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
