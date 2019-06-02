package com.bny.json.threads;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class ObjectMapperWrapper {

	private ObjectMapper mapper;
	private List<String>excludedFields;
	private Map<String,String>renamedFileds;
	private boolean isExclusionRequired;
	private boolean isRenameRequired;
	
	public ObjectMapperWrapper(ObjectMapperWrapperBuilder objectMapperWrapperBuilder) {
		
		this.mapper=objectMapperWrapperBuilder.mapper;
		this.excludedFields=objectMapperWrapperBuilder.excludedFields;
		this.isExclusionRequired=objectMapperWrapperBuilder.isExclusionRequired;
		this.renamedFileds=objectMapperWrapperBuilder.renamedFields;
		this.isRenameRequired=objectMapperWrapperBuilder.isRenameRequired;
	
	}
	public ObjectMapper getMapper() {
		return mapper;
	}
	public List<String> getExcludedFields() {
		return excludedFields;
	}
	public Map<String, String> getRenamedFileds() {
		return renamedFileds;
	}
	public boolean isExclusionRequired() {
		return isExclusionRequired;
	}
	public boolean isRenameRequired() {
		return isRenameRequired;
	}
	
	static class CustomRenameBeanSerializerModifier extends BeanSerializerModifier {

		 	private Map<String,String>renamedFileds;
		 	
		 	public CustomRenameBeanSerializerModifier(Map<String,String>renamedFileds) {
		 		this.renamedFileds=renamedFileds;
		 	}
		 	
			@Override
			public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
					List<BeanPropertyWriter> beanProperties) {
				 for (int i = 0; i < beanProperties.size(); i++) {
		                BeanPropertyWriter beanPropertyWriter = beanProperties.get(i);
		                if(renamedFileds.keySet().contains(beanPropertyWriter.getName())) {	
		                	beanProperties.set(i, new RenamePropertyWriter(beanPropertyWriter,this.renamedFileds));
		                }
		                
		            }
		            return beanProperties;
			}
			 
			 
		    }

public static class RenamePropertyWriter extends BeanPropertyWriter {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 3184264246825024900L;
		private Map<String,String>renamedFileds;
	    private final BeanPropertyWriter writer;

	    public RenamePropertyWriter(BeanPropertyWriter writer,Map<String,String>renamedFileds) {
	        super(writer);
	        this.writer = writer;
	        this.renamedFileds=renamedFileds;
	    }

	    @Override
	    public void serializeAsField(Object bean,
	                                 JsonGenerator gen,
	                                 SerializerProvider prov) throws Exception {
	        Object value = writer.get(bean);
	        if (value != null) {
				
	        	String newFieldName=this.renamedFileds.get(writer.getName());
	        		gen.writeStringField(newFieldName,value.toString());
	        }
	    }

		@Override
		public void assignSerializer(JsonSerializer<Object> ser) {
			super.assignSerializer(ser);
		}

	}

	
	
	public static class ObjectMapperWrapperBuilder{
	
		private ObjectMapper mapper;
		private List<String>excludedFields;
		private Map<String,String>renamedFields;
		private boolean isExclusionRequired;
		private boolean isRenameRequired;
		private String dateFormat;
		
		public ObjectMapperWrapperBuilder() {
			
		}
		
		public ObjectMapperWrapperBuilder withDefaultMapper() {
			this.mapper = new ObjectMapper();
			this.mapper.setSerializationInclusion(Include.NON_NULL);
			return this;
		}
		public ObjectMapperWrapperBuilder withExcludedFields(List<String> excludedFields) {
			this.excludedFields = excludedFields;
			if(this.excludedFields.size() > 0) {
				this.isExclusionRequired=true;
			}
			if(this.isExclusionRequired) {
				registerMapperForExclusionHook();	
			}
			
			return this;
		}
		
		public ObjectMapperWrapperBuilder withRenamedFields(Map<String, String> renamedFileds) {
			this.renamedFields = renamedFileds;
			if(this.renamedFields.size() > 0) {
				this.isRenameRequired=true;
			}
			if(this.isRenameRequired) {
				registerMapperForRenameHook();
			}
			return this;
		}
		public ObjectMapperWrapperBuilder withDateFormat(String dateFormat) {
			this.dateFormat=dateFormat;
			DateFormat df = new SimpleDateFormat(this.dateFormat);
			this.mapper.setDateFormat(df);
			return this;
		}
		private void registerMapperForRenameHook() {
			CustomRenameBeanSerializerModifier modifier = new CustomRenameBeanSerializerModifier(this.renamedFields);
	        SerializerFactory sf = BeanSerializerFactory.instance.withSerializerModifier(modifier);
	        this.mapper.setSerializerFactory(sf);
		}
		private void registerMapperForExclusionHook() {
			String[]excludedFileds= this.excludedFields.toArray(new String[this.excludedFields.size()]);  
			SimpleFilterProvider filterProvider = new SimpleFilterProvider();
			filterProvider.addFilter("postingFilter",SimpleBeanPropertyFilter.serializeAllExcept(excludedFileds));
			this.mapper.setFilterProvider(filterProvider);
		}
		
		public ObjectMapperWrapper build(){
			return new ObjectMapperWrapper(this);
		}
		
	}
}
