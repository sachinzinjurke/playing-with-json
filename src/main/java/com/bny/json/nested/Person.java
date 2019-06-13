package com.bny.json.nested;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonFilter("personFilter")
public class Person {
	
	private Integer age;
	private String job;
	private Name name;
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}

}
