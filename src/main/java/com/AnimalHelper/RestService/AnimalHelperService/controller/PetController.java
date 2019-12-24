package com.AnimalHelper.RestService.AnimalHelperService.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.AnimalHelper.RestService.AnimalHelperService.Beans.Pet;
import com.AnimalHelper.RestService.AnimalHelperService.Dao.PetDao;
import com.AnimalHelper.RestService.AnimalHelperService.RestExceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class PetController {

	@Autowired
	private PetDao petDao;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping(path="/pet/all")
	public MappingJacksonValue getPets() {
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("petname","petAddress");
		try {
				return petListDemoFilter(petDao.listpets(), filter);
				
		} catch (Exception e) {
			return null;
		}
		
		
	}
	//implementing HATEOAS :Hypermedia as the Engine of Application State
	//for returning a link to get pets
	@GetMapping(path="/pet/{petid}")
	public Resource<Pet> getPet(@PathVariable int petid) {//@RequestHeader(name ="Accept-Language",required=false) Locale locale
		
		Resource<Pet> resource= petDao.findPet(petid);
		if (resource==null)
			throw new ResourceNotFoundException("this resource does not exist");
		//Internationalization 
			//String petsex=messageSource.getMessage("dog.male",null,locale);
		String petsex=messageSource.getMessage("dog.male",null,LocaleContextHolder.getLocale()); //used in case we use a AcceptHeaderLocaleResolver
		resource.getContent().setSex(petsex);
		
		
		//HATEOS
		//hateoas resource
		//resource = new Resource<Pet>(pet);
		ControllerLinkBuilder linkAllPets= linkTo(methodOn(this.getClass()).getPets()); //method from ControllerLinkBuilder
		resource.add(linkAllPets.withRel("All Pets"));
		return resource;
	}
	
	@PostMapping(path="/pet")
	public ResponseEntity<Pet> addPet(@Valid @RequestBody Pet pet)
	{
		try {
			Resource<Pet> savedPet=petDao.savePet(pet);
			//this is one so that the uri of the saved object is part of the response
			URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPet.getContent().getPetId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	@PutMapping(path="/pet/{petid}")
	public ResponseEntity<Pet> updatePet(@RequestBody Pet pet)
	{
		try {
			petDao.updatePet(pet);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	@DeleteMapping(path="/pet/{petid}")
	public ResponseEntity<Pet> removePet(@PathVariable int petid)
	{
			petDao.deletePet(petid);
			return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	/**
	 * used to dynamically filter out elements of a bean 
	 * @param pet
	 * @return
	 */
	private MappingJacksonValue petListDemoFilter(List<Pet> pet,SimpleBeanPropertyFilter filter) {
		
		FilterProvider filters=new SimpleFilterProvider().addFilter("PetListFilter", filter);
		MappingJacksonValue mapping=new MappingJacksonValue(pet);
		mapping.setFilters(filters);
		return mapping;
	}
	
}
