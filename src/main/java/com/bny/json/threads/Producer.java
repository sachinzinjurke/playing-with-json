package com.bny.json.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bny.json.beans.ClientConfig;
import com.bny.json.constants.ClientConfigEnum;

public class Producer implements Runnable{
	
	private static final Logger logger = LoggerFactory.getLogger(Producer.class.getName());
	
	private BlockingQueue<ClientConfig>queue;
	
	public Producer(BlockingQueue<ClientConfig>queue) {
	
		this.queue=queue;
	}

	@Override
	public void run() {
		while(true) {
			
			ClientConfig config=getClientConfig();
			try {
				queue.offer(config,100,TimeUnit.MILLISECONDS);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
				logger.info("producing config for agent code : {} ",config.getAgentCode());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private ClientConfig getClientConfig() {
		ClientConfig config=new ClientConfig();
		config.setAgentCode(ClientConfigEnum.getRandomAgentCode().toString());
		config.setChannel("KAFKA");
		return config;
	}

}
