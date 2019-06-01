package com.bny.json.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFilter;
@JsonFilter("postingFilter")
public class PostingItem {
	
	private String agentCode;
	private int lobId;
	private String reference;
	private Double price1;
	private Double price2;
	private Double price3;
	private Double price4;
	private Date created;
	private Date lastUpdated;
	private String status;
	private float amount1;
	private float amount2;
	private float amount3;
	private float amount4;
	
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public int getLobId() {
		return lobId;
	}
	public void setLobId(int lobId) {
		this.lobId = lobId;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Double getPrice1() {
		return price1;
	}
	public void setPrice1(Double price1) {
		this.price1 = price1;
	}
	public Double getPrice2() {
		return price2;
	}
	public void setPrice2(Double price2) {
		this.price2 = price2;
	}
	public Double getPrice3() {
		return price3;
	}
	public void setPrice3(Double price3) {
		this.price3 = price3;
	}
	public Double getPrice4() {
		return price4;
	}
	public void setPrice4(Double price4) {
		this.price4 = price4;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getAmount1() {
		return amount1;
	}
	public void setAmount1(float amount1) {
		this.amount1 = amount1;
	}
	public float getAmount2() {
		return amount2;
	}
	public void setAmount2(float amount2) {
		this.amount2 = amount2;
	}
	public float getAmount3() {
		return amount3;
	}
	public void setAmount3(float amount3) {
		this.amount3 = amount3;
	}
	public float getAmount4() {
		return amount4;
	}
	public void setAmount4(float amount4) {
		this.amount4 = amount4;
	}
	
}
