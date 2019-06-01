package com.bny.json.constants;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bny.json.beans.MapperConfigBean;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class MapperInitializer {

	private static final Logger logger = LoggerFactory.getLogger(MapperInitializer.class.getName());
	public static HashMap<ClientConfigEnum, ObjectMapper>MAPPER_MAP=new HashMap<ClientConfigEnum, ObjectMapper>();

	public static void initialize() {
		
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			 InputStream is = MapperInitializer.class.getResourceAsStream("/mapper.json");
			 List<MapperConfigBean> mapperBean=mapper.readValue(is, new TypeReference<List<MapperConfigBean>>(){} );
			 logger.info("Mapper Bean :: {}",mapperBean);
			
			  for (MapperConfigBean mapperConfigBean : mapperBean) {
				  try {
					  createMapper(mapperConfigBean); 
				  }catch(Exception ex) {
					  logger.error("erroe while cerating mapper",ex);
				  }
				  
			  }
			 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void createMapper(MapperConfigBean mapperConfigBean) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
		String[]excludedFileds= mapperConfigBean.getExcludedFileds().toArray(new String[mapperConfigBean.getExcludedFileds().size()]);  
		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.addFilter("postingFilter",SimpleBeanPropertyFilter.serializeAllExcept(excludedFileds));
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setDateFormat(df);
		mapper.setFilterProvider(filterProvider);
		logger.info("Putting clinet mapper : {}  with excluded values : {} ",ClientConfigEnum.valueOf(mapperConfigBean.getClientName()), excludedFileds);
		MAPPER_MAP.put(ClientConfigEnum.valueOf(mapperConfigBean.getClientName()), mapper);
	}
}
