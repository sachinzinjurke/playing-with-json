package com.bny.json.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.bny.json.beans.PostingItem;
import com.bny.json.constants.ClientConfigEnum;

public class AgentCodeHelper {
	
	private List<PostingItem> list=new ArrayList<PostingItem>();
	public AgentCodeHelper(){
		
		for (int i = 0; i < 10; i++) {
			PostingItem item=new PostingItem();
			item.setAgentCode(ClientConfigEnum.getRandomAgentCode().toString());
			item.setAmount1(i+1);
			item.setAmount2(i+1);
			item.setAmount3(i+1);
			item.setAmount4(i+1);
			item.setCreated(new Date());
			item.setLastUpdated(new Date());
			item.setLobId(i);
			item.setPrice1(i+10D);
			item.setPrice2(i+10D);
			item.setPrice3(i+10D);
			item.setPrice4(i+10D);
			item.setReference("reference-" + i);
			item.setStatus("status-" + i);
			list.add(item);
		}
		
	}

	public PostingItem getRandomPostingItem() {
		 Random random = new Random();
         return list.get(random.nextInt(list.size()));
	}
}
