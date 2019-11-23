package com.AnimalHelper.RestService.AnimalHelperService.Beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
@ApiModel("Pet")
@Entity
public class PetShop {

	@Id  //identifying this as an id
	@GeneratedValue // making this as an auto generated value
	private int id;
	private String name;
	private String franchise;
	private String address;
	 //List<Pet> pets;
	
	 public PetShop()
	{
		
	}
	public PetShop(String name, String franchise, String address) {
		super();
		this.name = name;
		this.franchise = franchise;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFranchise() {
		return franchise;
	}
	public void setFranchise(String franchise) {
		this.franchise = franchise;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	 
	 
	 
	 
}
