package com.bny.json.runner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bny.json.beans.Product;
import com.bny.json.jackson.serializer.CustomSerializer;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class JacksonRunnerCustomSerializer {

	public static void main(String[] args) {

		Product product = new Product(1, "Playstation 4", new Date(), 499.99,"name1","name2","name3","name4");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(new CustomSerializer());
		
		String[]filterProperty1= {"name"};
		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.addFilter("productFilter",SimpleBeanPropertyFilter.serializeAllExcept(filterProperty1));
		
		ObjectMapper mapper1 = new ObjectMapper();
		mapper1.setSerializationInclusion(Include.NON_NULL);
		mapper1.setDateFormat(df);
		mapper1.registerModule(module); 
		mapper1.setFilterProvider(filterProvider);
		
		try {
			String jsonWriter = mapper1.writerWithDefaultPrettyPrinter()
					 .writeValueAsString(product);
			System.out.println(jsonWriter);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
