package com.bny.json.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFilter("studentFilter")
public class Student {

	@JsonProperty("stdName")
	private String name;
	
	@JsonProperty("stdAge")	
	private Integer age;
	
	@JsonProperty("stdCollege")	
	private String college;
	
	@JsonProperty("stdCity")	
	private String city;
	
	@JsonProperty("div")	
	private String div;
	
	@JsonProperty("stdDate")
	private Date date;
	
	public Student() {}
	public Student(String name, Integer age, String college, String city,String div,Date date) {
		this.name = name;
		this.age = age;
		this.college = college;
		this.city = city;
		this.div=div;
		this.date=date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDiv() {
		return div;
	}
	public void setDiv(String div) {
		this.div = div;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
