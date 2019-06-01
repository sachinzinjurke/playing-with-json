package com.bny.json.runner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bny.json.beans.Student;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class Runner {

	public static void main(String[] args) throws JsonProcessingException {
		
		String[]filterProperty1= {"stdName","stdCity"};
		String[]filterProperty2= {"stdName"};
		SimpleFilterProvider filterProvider1 = new SimpleFilterProvider();
		filterProvider1.addFilter("studentFilter",SimpleBeanPropertyFilter.serializeAllExcept(filterProperty1));
		
		SimpleFilterProvider filterProvider2 = new SimpleFilterProvider();
		filterProvider2.addFilter("studentFilter",SimpleBeanPropertyFilter.serializeAllExcept(filterProperty2));
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
		
		
		ObjectMapper mapper1 = new ObjectMapper();
		mapper1.setSerializationInclusion(Include.NON_NULL);
		mapper1.setFilterProvider(filterProvider1);
		mapper1.setDateFormat(df);
		
		ObjectMapper mapper2 = new ObjectMapper();
		mapper2.setSerializationInclusion(Include.NON_NULL);
		mapper2.setFilterProvider(filterProvider2);
		
		Student student = new Student("Mohit", 30, "ABCD", "Varanasi","B",new Date() );   
		System.out.println("jsonData1"); 
		String jsonData1 = mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(student);
		System.out.println(jsonData1);
		System.out.println("jsonData2"); 
		String jsonData2 = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(student);
		System.out.println(jsonData2);
	}
	
}
