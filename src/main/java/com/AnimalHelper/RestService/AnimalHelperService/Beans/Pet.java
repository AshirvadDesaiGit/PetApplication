package com.AnimalHelper.RestService.AnimalHelperService.Beans;

import java.net.MalformedURLException;
//import java.net.MalformedURLException;
import java.net.URL;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("Pet")// swagger description
//@JsonFilter("PetListFilter") //register filter for dynamic filtering
@Entity
public class Pet {
	
	
	@JsonIgnore //dont include this in the response
	@Id  //identifying this as an id
	@GeneratedValue // making this as an auto generated value
	@Column(name = "petid")
	private int petId;

	@Size(min = 3)
	@ApiModelProperty("Minimum 3 characters") // swagger description
	@Column(name = "petname")
	private String petName;

	private String sex;

	@Column(name = "petaddress")
	private String petAddress;

	@Column(name = "petlocation")
	private String petLocation;

	private URL petImageurl;

	private String petType;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pet_owner_id", nullable=false)
	private AppUser petOwner;
	
	//private PetShop petShop;
	
	public AppUser getPetOwner() {
		return petOwner;
	}

	public void setPetOwner(AppUser petOwner) {
		this.petOwner = petOwner;
	}


	public static class Builder{
		//Must need parameter
		private int petId;
		private String petName;
		//optional parameter
		private String petType;
		private String petAddress;
		private String petLocation;
		private URL petImageurl;
		private String sex;
		private PetShop petShop;
		
		public Builder(int petId, String petName)
		{
			this.petId = petId;
			this.petName = petName;
		}
		
		public Builder petType(PetShop val) {
			petShop =val;
			 return this;
			 
		}
		
		public Builder petType(String val) {
			 petType =val;
			 return this;
			 
		}
		
		public Builder petSex(String val) {
			sex =val;
			 return this;
			 
		}
		
		public Builder petAddress(String val) {
			petAddress =val;
			 return this;
			 
		}
		
		public Builder petLocation(String val) {
			petLocation =val;
			 return this;
			 
		}
		public Builder petImageurl(URL val) {
			petImageurl =val;
			 return this;
			 
		}
		
		//call to build the instance
		public Pet build()
		{
			return new Pet(this);
		}
		
	}

	public Pet()
	{
		
	}
	
	private Pet(Builder builder)
	{
		  petType=builder.petType;
		  petId =builder.petId;
		  petName =builder.petName;
		  petAddress=builder.petAddress;
		  petLocation=builder.petLocation;
		  petImageurl=builder.petImageurl;
		  sex=builder.sex;
		
	}

	
	
	/*
	 * public PetShop getPetshop() { return petShop; }
	 * 
	 * public void setPetshop(PetShop petShop) { this.petShop = petShop; }
	 */

	public String getPetType() {
		return petType;
	}

	public int getPetId() {
		return petId;
	}

	public String getPetName() {
		return petName;
	}
	

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPetAddress() {
		return petAddress;
	}

	public String getPetLocation() {
		return petLocation;
	}

	public URL getPetImageurl() {
		return petImageurl;
	}
	
	
	public void setPetType(String petType) {
		this.petType = petType;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public void setPetAddress(String petAddress) {
		this.petAddress = petAddress;
	}

	public void setPetLocation(String petLocation) {
		this.petLocation = petLocation;
	}

	public void setPetImageurl(URL petImageurl) {
		this.petImageurl = petImageurl;
	}

	
	public static void main(String mig[]) {
		try {
			int petid = 1;
			String petname = "mypetname";
			String petType = "petType";
			String petAddress = "petAddress";
			String petLocation = "petLocation";
			URL petImageurl = new URL("");

			Pet mypet = new Pet.Builder(petid, petname).petType("").petAddress("").petLocation("")
					.petImageurl(petImageurl).build();

		} catch (MalformedURLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	 
	  
	  }
	 
}
