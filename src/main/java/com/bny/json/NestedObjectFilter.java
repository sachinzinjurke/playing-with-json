package com.bny.json;

import com.bny.json.nested.Name;
import com.bny.json.nested.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class NestedObjectFilter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Person person=new Person();
		Name name=new Name();
		name.setFirstName("Sachin");
		name.setLastName("Zinjurke");
		person.setAge(10);
		person.setJob("SW");
		person.setName(name);
		
		ObjectMapper mapper = new ObjectMapper();

	    SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		/*filterProvider.addFilter("personFilter",SimpleBeanPropertyFilter.serializeAll())*/
	    filterProvider.addFilter("nameFilter",SimpleBeanPropertyFilter.serializeAllExcept("firstName"));
		mapper.setFilterProvider(filterProvider);
	    
		try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
