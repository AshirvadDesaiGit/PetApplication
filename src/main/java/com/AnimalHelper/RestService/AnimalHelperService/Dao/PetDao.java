package com.AnimalHelper.RestService.AnimalHelperService.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import com.AnimalHelper.RestService.AnimalHelperService.Beans.Pet;
import com.AnimalHelper.RestService.AnimalHelperService.RestExceptions.ResourceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
public class PetDao {
	@Autowired
	PetRepository petRepository;

	// save method is used for save/update
	// use entity manager if you choose a composite key of date being passed,
	// where if duplicate is passed then it should throw exception
	@PersistenceContext
	EntityManager entityManager;
	
	Pet pet1=new Pet.Builder(1, "pet1").petAddress("arlem").petLocation("goa").build();
	Pet pet2=new Pet.Builder(2, "pet2").petAddress("barebhat").petLocation("india").build();
	Pet pet3=new Pet.Builder(3, "pet3").petAddress("raia").petLocation("ponda").build();
	
	List<Pet> pets = new ArrayList<Pet>();
	
	public PetDao()
	{
		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
	}
	
	//used to list pets
	public List<Pet> listpets() {
		return petRepository.findAll();
		
	}
	//used to savepet to database
	public Resource<Pet> savePet(Pet pet) {
		try {
			Pet savedpet = petRepository.save(pet);
			Resource<Pet> resource = new Resource<Pet>(savedpet);
			return resource;
			
		} catch (Exception e) {
			return null;
		}
	}
			
	//used to retrive pet details
	public Resource<Pet> updatePet(Pet pet)
	{
		try {
			Pet savedpet = petRepository.save(pet);
			Resource<Pet> resource = new Resource<Pet>(savedpet);
			return resource;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	//used to delete pet details
	public void deletePet(int petId)
	{
		try {
			petRepository.deleteById(petId);
			
			
		} catch (Exception e) {
			
		}
		
	}
		
	//used to retrive pet details
	public Resource<Pet> findPet(int petId)
	{
		try {
			Optional<Pet> pet = petRepository.findById(petId);
			if (!pet.isPresent())
				throw new ResourceNotFoundException("id-" + petId);

			
			Resource<Pet> resource = new Resource<Pet>(pet.get());
			return resource;
			
		} catch (Exception e) {
			return null;
		}
		
	}

	public long getPetCountByName(String petname) {
		return petRepository.countByPetName(petname);
	}

	public Page<Pet> getAllByPage(Integer pageNo, Integer pageSize, String sortBy, String order) {

		/*
		Either return page object as is. or filter out the content and create a list.
		Page contains most of the details related to pagination, so could use the page object as is.
		 */
		Pageable page = PageRequest.of(pageNo, pageSize,
				Sort.by(("ASC".equalsIgnoreCase(order))? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
		return petRepository.findAll(page);
	}

	public Page<Pet> getAllByPetName(String petname, Integer pageNo, Integer pageSize, String sortBy, String order) {

		Pageable page = PageRequest.of(pageNo, pageSize,
				Sort.by(("ASC".equalsIgnoreCase(order))? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
		return petRepository.findAllByPetName(petname, page);
	}
}
