package com.bny.json.runner;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bny.json.beans.ClientConfig;
import com.bny.json.threads.Consumer;
import com.bny.json.threads.Producer;

public class PostingRunner {

	public static void main(String[] args) throws InterruptedException {
		
		
		ExecutorService producerService=Executors.newFixedThreadPool(1);
		ExecutorService consumerService=Executors.newFixedThreadPool(1);
		
		BlockingQueue<ClientConfig>queue=new ArrayBlockingQueue<ClientConfig>(100);
		
		Producer producer=new Producer(queue);
		Consumer consumer=new Consumer(queue);
		
		producerService.submit(producer);
		consumerService.submit(consumer);
		
		while(true) {
			System.out.println("Sleeping");
			Thread.sleep(2000);
		}
		
		
	}
}
