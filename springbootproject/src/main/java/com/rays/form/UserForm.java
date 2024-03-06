package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class UserForm {

	@NotEmpty(message = "firstName is required...!!!")
	private String firstName;

	@NotEmpty(message = "lastName is required...!!!")
	private String lastName;

	@NotEmpty(message = "loginId is required...!!!")
	private String loginId;

	@NotEmpty(message = "password is required...!!!")
	private String password;

	@NotEmpty(message = "dob is required...!!!")
	private String dob;

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
