package com.AnimalHelper.RestService.AnimalHelperService.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.AnimalHelper.RestService.AnimalHelperService.Beans.Pet;



@Component
public class PetDao {
	
	Pet pet1=new Pet.Builder("1", "pet1").petAddress("arlem").petLocation("goa").build();
	Pet pet2=new Pet.Builder("2", "pet2").petAddress("barebhat").petLocation("india").build();
	Pet pet3=new Pet.Builder("3", "pet3").petAddress("raia").petLocation("ponda").build();
	
	List<Pet> pets = new ArrayList<Pet>();
	
	public PetDao()
	{
		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
	}
	
	//used to list pets
	public List<Pet> listpets() {
		return pets;
		
	}
	//used to savepet to database
	public Pet savePet(Pet pet) {
		try {
			pets.add(pet);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pet;
	}
	
	//used to retrive pet details
	public Pet createPet(Pet pet)
	{
		return new Pet.Builder(pet.getPetid(), pet.getPetname()).build();
	}
	
	//used to retrive pet details
	public Pet updatePet(Pet pet)
	{
		return new Pet.Builder("", "tempname").build();
	}
	
	//used to delete pet details
	public Pet deletePet(String petId)
	{
		try {
			Pet pet=pets.get(Integer.parseInt(petId));
			pets.remove(petId);
			return pet;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
		
	//used to retrive pet details
	public Pet findPet(String petId)
	{
		try {
			return pets.get(Integer.parseInt(petId));
		} catch (Exception e) {
			return null;
		}
		
	}
}
