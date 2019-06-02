package com.bny.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        initialize();
    }
    public static void initialize() {
		
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			 InputStream is = App.class.getResourceAsStream("/test.json");
			 List<Product> mapperBean=mapper.readValue(is, new TypeReference<List<Product>>(){} );
			 System.out.println(mapperBean);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Product {
    	 
    	private String clientName;
    	private List<String> excludedFields=new ArrayList<String>();
    	private Map<String,String> renameFields=new HashMap<String,String>();
    	
    	public String getClientName() {
    		return clientName;
    	}
    	public void setClientName(String clientName) {
    		this.clientName = clientName;
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
			return "Product [clientName=" + clientName + ", excludedFields=" + excludedFields + ", renameFields="
					+ renameFields + "]";
		}
		
    
    	
     
    }
}
