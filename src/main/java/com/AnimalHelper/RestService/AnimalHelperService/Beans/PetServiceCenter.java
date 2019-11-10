package com.AnimalHelper.RestService.AnimalHelperService.Beans;

public class PetServiceCenter {

	private String name;
	private String id;
	private String headquaters; //address of headquaters
	private String phoneNumber;
	private String email;
	private String country;
	private String state;
	private String district;
	private String zipcode;
	private String franchiseId;
	
	public PetServiceCenter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PetServiceCenter(String name, String id, String phoneNumber, String email) {
		super();
		this.name = name;
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHeadquaters() {
		return headquaters;
	}

	public void setHeadquaters(String headquaters) {
		this.headquaters = headquaters;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getFranchise() {
		return franchiseId;
	}

	public void setFranchise(String franchise) {
		this.franchiseId = franchise;
	}
	
	
	
}
