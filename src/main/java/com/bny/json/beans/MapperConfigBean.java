package com.bny.json.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MapperConfigBean {
	
	
	private String clientName;
	private String dateFormat;
	private List<String> excludedFields=new ArrayList<String>();
	private Map<String,String> renameFields=new HashMap<String,String>();
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public List<String> getExcludedFields() {
		return excludedFields;
	}
	public void setExcludedFields(List<String> excludedFields) {
		this.excludedFields = excludedFields;
	}
	public Map<String, String> getRenameFields() {
		return renameFields;
	}
	public void setRenameFields(Map<String, String> renameFields) {
		this.renameFields = renameFields;
	}
	@Override
	public String toString() {
		return "MapperConfigBean [clientName=" + clientName + ", dateFormat=" + dateFormat + ", excludedFields="
				+ excludedFields + ", renameFields=" + renameFields + "]";
	}
	
	
	
	

}
