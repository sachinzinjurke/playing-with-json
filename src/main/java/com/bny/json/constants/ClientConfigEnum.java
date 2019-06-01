package com.bny.json.constants;

import java.util.Random;

public enum ClientConfigEnum {

	AIS("AIS"),SACHIN("SACHIN"),MUKIL("MUKIL"),SHRINI("SHRINI");
	
	private  String agentCode;
	
	ClientConfigEnum(String agentCode) {
		this.agentCode=agentCode;
	}
	
	 public String getAgentCode() {
	        return this.agentCode;
	    }
	 
	 public static ClientConfigEnum getRandomAgentCode() {
         Random random = new Random();
         return values()[random.nextInt(values().length)];
     }
	    
	
}
