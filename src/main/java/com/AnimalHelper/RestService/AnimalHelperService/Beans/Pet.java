package com.AnimalHelper.RestService.AnimalHelperService.Beans;

import java.net.MalformedURLException;
//import java.net.MalformedURLException;
import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("Pet")// swagger description
@JsonFilter("PetListFilter") //register filter for dynamic filtering
@Entity
public class Pet {
	
	private String petType;
	@JsonIgnore //dont include this in the response
	@Id  //identifying this as an id
	@GeneratedValue // making this as an auto generated value
	private String petid;
	@Size(min = 3)
	@ApiModelProperty("Minimum 3 characters") // swagger description
	private String petname;
	private String sex;
	private String petAddress;
	private String petLocation;
	private URL petImageurl;
	
	public static class Builder{
		//Must need parameter
		private String petid;
		private String petname;
		//optional parameter
		private String petType;
		private String petAddress;
		private String petLocation;
		private URL petImageurl;
		private String sex;
		
		public Builder(String petid, String petname)
		{
			this.petid=petid;
			this.petname=petname;
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
		  petid=builder.petid;
		  petname=builder.petname;
		  petAddress=builder.petAddress;
		  petLocation=builder.petLocation;
		  petImageurl=builder.petImageurl;
		  sex=builder.sex;
		
	}

	
	public String getPetType() {
		return petType;
	}

	public String getPetid() {
		return petid;
	}

	public String getPetname() {
		return petname;
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

	public void setPetid(String petid) {
		this.petid = petid;
	}

	public void setPetname(String petname) {
		this.petname = petname;
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
			String petid = "001";
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
