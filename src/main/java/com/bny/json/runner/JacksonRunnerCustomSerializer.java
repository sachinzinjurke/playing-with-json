package com.bny.json.runner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.bny.json.beans.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.SerializerFactory;

public class JacksonRunnerCustomSerializer {

	static final List<String>PropertyName = Arrays.asList("field1","field2","name");
    static final String CustomValue = "SACHIN";
    static final String BaseValue = "baseValue";
    
	public static void main(String[] args) {

		Product product = new Product(1, "Playstation 4", new Date(), 499.99,"name1","name2","name3","name4");
		
		
		 ObjectMapper mapper = createMapper();

		 try {
			String json = mapper.writeValueAsString(product);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	public static ObjectMapper createMapper() {
		MyBeanSerializerModifier modifier = new MyBeanSerializerModifier();
        SerializerFactory sf = BeanSerializerFactory.instance.withSerializerModifier(modifier);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializerFactory(sf);

        return mapper;
    }

	public static class SensitivePropertyWriter extends BeanPropertyWriter {
		
	    private final BeanPropertyWriter writer;

	    public SensitivePropertyWriter(BeanPropertyWriter writer) {
	        super(writer);
	        this.writer = writer;
	    }

	    @Override
	    public void serializeAsField(Object bean,
	                                 JsonGenerator gen,
	                                 SerializerProvider prov) throws Exception {
	        Object value = writer.get(bean);
	        if (value != null) {
				/*
				 * String strValue = (String) value; gen.writeStringField(writer.getName(),
				 * "sachin");
				 */
	        	if(writer.getName()=="field1") {
	        		gen.writeStringField("MODIFIED-FIELD1",value.toString());
	        	}
	        	if(writer.getName()=="field2") {
	        		gen.writeStringField("MODIFIED-FIELD2","MODIFIED-FIELD2");
	        	}
	        }
	    }

		@Override
		public void assignSerializer(JsonSerializer<Object> ser) {
			super.assignSerializer(ser);
		}

	    
		/*
		 * @Override public BeanPropertyWriter withSerializer(JsonSerializer<Object>
		 * ser) { return this; }
		 */
	}
	 static class MyBeanSerializerModifier extends BeanSerializerModifier {

		@Override
		public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
				List<BeanPropertyWriter> beanProperties) {
			 for (int i = 0; i < beanProperties.size(); i++) {
	                BeanPropertyWriter beanPropertyWriter = beanProperties.get(i);
				/*
				 * if (PropertyName.equals(beanPropertyWriter.getName())) {
				 * beanProperties.set(i, new SensitivePropertyWriter(beanPropertyWriter)); }
				 */
	                if(PropertyName.contains(beanPropertyWriter.getName())) {	
	                	beanProperties.set(i, new SensitivePropertyWriter(beanPropertyWriter));
	                }
	                
	            }
	            return beanProperties;
		}
		 
		 
	    }
}
