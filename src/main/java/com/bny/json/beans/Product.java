package com.bny.json.beans;

import java.util.Date;
public class Product {

	private long id;
    private String name;
    private Date created;
    private double amount;
    private String field1;
    private String field2;
    private String field3;
    private String field4;

    public Product(long id, String name, Date created, double amount,String field1,String field2,String field3,String field4) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.amount = amount;
        this.field1=field1;
        this.field2=field2;
        this.field3=field3;
        this.field4=field4;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreated() {
        return created;
    }

    public double getAmount() {
        return amount;
    }

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
    
}
