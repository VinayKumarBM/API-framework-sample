package com.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InventoryDO {

	@SerializedName("sold")
	@Expose
	private Integer sold;	
	@SerializedName("pending")
	@Expose
	private Integer pending;
	@SerializedName("available")
	@Expose
	private Integer available;

	public Integer getSold() {
		return sold;
	}

	public void setSold(Integer sold) {
		this.sold = sold;
	}

	public Integer getPending() {
		return pending;
	}

	public void setPending(Integer pending) {
		this.pending = pending;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	@Override 
	public String toString() {
		return "[\nsold: "+sold+", \npending: "+pending+", \navailable: "+available+"\n]";
	}
}
