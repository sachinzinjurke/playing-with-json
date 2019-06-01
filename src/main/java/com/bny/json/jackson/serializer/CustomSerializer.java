package com.bny.json.jackson.serializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.bny.json.beans.Product;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomSerializer extends StdSerializer<Product>{
	public CustomSerializer() {
	      this(Product.class);
	   }
	   protected CustomSerializer(Class<Product> t) {
	      super(t);
	   }

	/**
	 * 
	 */
	private static final long serialVersionUID = -7429778924045254936L;

	@Override
	public void serialize(Product value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
		 gen.writeStartObject();
		 gen.writeStringField("name", value.getName().toUpperCase());
		 gen.writeStringField("field1",value.getField1());
		// gen.writeStringField("created", df.format(value.getCreated()));
		 gen.writeObjectField("created", value.getCreated());
		 gen.writeEndObject();
	}

	/*
	 * @Override public void serialize(Product value, JsonGenerator gen,
	 * SerializerProvider serializers) throws IOException {
	 * 
	 * gen.writeStringField("name", value.getName().toUpperCase());
	 * 
	 * }
	 */

}
