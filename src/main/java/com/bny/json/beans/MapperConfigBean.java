package com.bny.json.beans;

import java.util.ArrayList;
import java.util.List;

public class MapperConfigBean {
	
	
	private String clientName;
	private List<String> excludedFileds=new ArrayList<String>();
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public List<String> getExcludedFileds() {
		return excludedFileds;
	}
	public void setExcludedFileds(List<String> excludedFileds) {
		this.excludedFileds = excludedFileds;
	}
	@Override
	public String toString() {
		return "MapperConfigBean [clientName=" + clientName + ", excludedFileds=" + excludedFileds + "]";
	}
	
	

}