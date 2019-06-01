package com.bny.json.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class CustomSerializer extends SimpleBeanPropertyFilter{

	@Override
	public void serializeAsElement(Object elementValue, JsonGenerator jgen, SerializerProvider provider,
			PropertyWriter writer) throws Exception {
		
		 if (include(writer)) {
			 if(writer.getName().equals("amount")) {
				 System.out.println("###############");
				 writer.serializeAsField(50, jgen, provider);
			 }
	        
	      } else if (!jgen.canOmitFields()) { // since 2.3
	         writer.serializeAsOmittedField(elementValue, jgen, provider);
	      }
	}
	 @Override
	   protected boolean include(BeanPropertyWriter writer) {
	      return true;
	   }
	   @Override
	   protected boolean include(PropertyWriter writer) {
	      return true;
	   }
	
}
