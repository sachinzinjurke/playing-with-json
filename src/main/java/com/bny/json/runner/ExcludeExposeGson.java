package com.bny.json.runner;

import java.util.Date;

import com.bny.json.beans.Product;
import com.bny.json.serializer.ProductSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExcludeExposeGson {

	public static void main(String[] args) {
		Product product = new Product(1, "Playstation 4", new Date(), 499.99,"name1","name2","name3","name4");

        // normal serialization
        Gson gson = new GsonBuilder().create();
        String result = gson.toJson(product);
        System.out.println(result);

        // dynamic serialization on product
        gson = new GsonBuilder()
                .registerTypeAdapter(Product.class, new ProductSerializer())
                .create();
        result = gson.toJson(product);
        System.out.println(result);
        
	}
}
