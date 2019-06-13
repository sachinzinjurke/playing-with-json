package com.bny.json.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bny.json.beans.Body;
import com.bny.json.beans.ClientConfig;
import com.bny.json.beans.Header;
import com.bny.json.beans.PayLoad;
import com.bny.json.beans.PostingItem;
import com.bny.json.constants.ClientConfigEnum;
import com.bny.json.constants.MapperInitializerUpdated;
import com.bny.json.helper.AgentCodeHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Consumer implements Runnable{

private static final Logger logger = LoggerFactory.getLogger(Consumer.class.getName());
	
	private BlockingQueue<ClientConfig>queue;
	
	private AgentCodeHelper helper=new AgentCodeHelper();
	
	public Consumer(BlockingQueue<ClientConfig>queue) {
	
		this.queue=queue;
	}

	@Override
	public void run() {
		MapperInitializerUpdated.initialize();
		while(true) {
			ClientConfig client=null;
			try {
				client = this.queue.poll(100,TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(client!=null) {
				logger.info("processing client {}  : ",client.getAgentCode());
				PostingItem randomPostingItem = helper.getRandomPostingItem();
				randomPostingItem.setAgentCode(client.getAgentCode());
				
				PayLoad payload=new PayLoad();
				Header header=new Header();
				header.setDeliminitor("comma");
				header.setMessageFormat("JSON");
				
				Body body=new Body();
				body.setFields(randomPostingItem);
				
				payload.setHeader(header);
				payload.setBody(body);
				
				ObjectMapper mapper=MapperInitializerUpdated.MAPPER_MAP.get(ClientConfigEnum.valueOf(randomPostingItem.getAgentCode()));
				if(mapper!=null) {
					logger.info("Got the mapper for client : {} " + randomPostingItem.getAgentCode());	
					try {
						String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
						logger.info(json);
					} catch (JsonProcessingException e) {
						logger.error("Error while parsing posting item for client : {} ",client.getAgentCode(),e);
						
					}
				}else {
					logger.info("No mapper is configured for client : {} ",client.getAgentCode());
				}
				
			}
		}
		
	}
}
