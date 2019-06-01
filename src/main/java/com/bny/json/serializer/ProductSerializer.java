package com.bny.json.serializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.bny.json.beans.Product;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ProductSerializer implements JsonSerializer<Product>{

	@Override
	public JsonElement serialize(Product product, Type type, JsonSerializationContext context) {
		JsonObject jObj = (JsonObject)new GsonBuilder().create().toJsonTree(product);
		/*
		 * if (product.getAmount() > 100){ jObj.remove("amount"); }
		 */
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
		String [] list= {"field1","field4"};
		for (String fname : list) {
			if(jObj.has(fname)) {
	        	 jObj.remove(fname);
	        }	
		}
        return jObj;
	}

}
