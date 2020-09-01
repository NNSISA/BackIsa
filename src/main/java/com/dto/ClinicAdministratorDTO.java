package com.dto;

import com.model.ClinicAdministrator;

public class ClinicAdministratorDTO {

	private String password;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String city;
	private String country;
	private int mobileNumber;
	private int jmbg;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public int getJmbg() {
		return jmbg;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}

	public ClinicAdministratorDTO() {
		super();
	}

	public ClinicAdministratorDTO(ClinicAdministrator ca) {
		super();
		this.password = ca.getPassword();
		this.username = ca.getUsername();
	}

	public ClinicAdministratorDTO(String password, String username, String firstName, String lastName, String email,
			String address, String city) {
		this.password = password;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
	}

}
