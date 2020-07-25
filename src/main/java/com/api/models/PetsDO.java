package com.api.models;

import java.util.List;

public class PetsDO {

	private Long id;
	private Category category;
	private String name;
	private List<String> photoUrls = null;
	private List<Tag> tags = null;
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "[\nid: "+ id +", \ncategory: "+ category +", \nname: "+ name +", \nphotoUrls: "+ photoUrls.toString() +
				", \ntags"+ tags +", \nstatus: "+ status+"\n]";
	}

}