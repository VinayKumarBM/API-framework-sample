package com.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDO {

	@SerializedName("id")
	@Expose
	private Long id;
	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("firstName")
	@Expose
	private String firstName;
	@SerializedName("lastName")
	@Expose
	private String lastName;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("password")
	@Expose
	private String password;
	@SerializedName("phone")
	@Expose
	private String phone;
	@SerializedName("userStatus")
	@Expose
	private Long userStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Long userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "[\nid: "+ id +", \nusername: "+ username +"\nfirstName: "+ firstName +", \nlastName: "+ lastName +
				", \nemail: "+ email +", \npassword: "+ password +", \nphone: "+ phone +", \nuserStatus: "+ userStatus +"\n]";
	}

}