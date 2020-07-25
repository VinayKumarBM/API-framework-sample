package com.api.models;

public class InventoryDO {

	private Integer sold;	
	private Integer pending;
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
