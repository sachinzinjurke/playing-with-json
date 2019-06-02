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
import com.bny.json.threads.ObjectMapperWrapper;
import com.bny.json.threads.ObjectMapperWrapper.ObjectMapperWrapperBuilder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class MapperInitializerUpdated {

	private static final Logger logger = LoggerFactory.getLogger(MapperInitializerUpdated.class.getName());
	public static HashMap<ClientConfigEnum, ObjectMapper>MAPPER_MAP=new HashMap<ClientConfigEnum, ObjectMapper>();

	public static void initialize() {
		
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			 InputStream is = MapperInitializer.class.getResourceAsStream("/test.json");
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
		
		ObjectMapperWrapperBuilder builder = new ObjectMapperWrapper.ObjectMapperWrapperBuilder().withDefaultMapper();;
		if(mapperConfigBean.getExcludedFields()!=null && mapperConfigBean.getExcludedFields().size() > 0) {
			builder.withExcludedFields(mapperConfigBean.getExcludedFields());
		}
		if(mapperConfigBean.getRenameFields()!=null && mapperConfigBean.getRenameFields().size() > 0) {
			builder.withRenamedFields(mapperConfigBean.getRenameFields());
		}
		if(mapperConfigBean.getDateFormat()!=null ) {
			builder.withDateFormat(mapperConfigBean.getDateFormat());
		}
	//	ObjectMapperWrapper build = builder.build();
		logger.info("Putting clinet mapper : {}  with excluded values : {} , renamed fields : {}",ClientConfigEnum.valueOf(mapperConfigBean.getClientName()), mapperConfigBean.getExcludedFields(),mapperConfigBean.getRenameFields());
		MAPPER_MAP.put(ClientConfigEnum.valueOf(mapperConfigBean.getClientName()), builder.build().getMapper());
	}
}
