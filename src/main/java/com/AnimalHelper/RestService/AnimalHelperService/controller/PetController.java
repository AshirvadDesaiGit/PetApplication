package com.AnimalHelper.RestService.AnimalHelperService.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
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

	@GetMapping(path = "/pet/{name}/count", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Get the count of Pets by name" ,response = Long.class)
	public ResponseEntity<Long> countPetsByName(@ApiParam("Name Of Pet") @PathVariable(value="name") String name){

		System.out.println("name"+name);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(petDao.getPetCountByName(name));
	}

	@GetMapping(path = "/pets/all/page/{pageno}", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Get All Pets by Page number" ,response = Page.class)
	public ResponseEntity<Page<Pet>> getAllPets(
			@ApiParam(value = "page number") @PathVariable(name = "pageno") Integer page,
			@ApiParam(value = "page size") @RequestParam(defaultValue = "5") Integer pageSize,
			@ApiParam(value = "Field by which the results will be sorted") @RequestParam(defaultValue = "petId") String sortBy,
			@ApiParam(value = "Sorting order. Use values asc or desc") @RequestParam(defaultValue = "DESC") String order ){

		System.out.println("pageno"+page);
		System.out.println("pageSize"+pageSize);
		System.out.println("sortby"+sortBy);
		System.out.println("order"+order);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(petDao.getAllByPage(page, pageSize, sortBy, order));
	}

	@GetMapping(path = "/pets/{petname}/page/{pageno}", produces = {"application/json", "application/xml"})
	@ApiOperation(value = "Get All Pets by Page number" ,response = Page.class)
	public ResponseEntity<Page<Pet>> getAllPetsByName(
			@ApiParam(value = "pet name") @PathVariable(name = "petname") String petname,
			@ApiParam(value = "page number") @PathVariable(name = "pageno") Integer page,
			@ApiParam(value = "page size") @RequestParam(defaultValue = "5") Integer pageSize,
			@ApiParam(value = "Field by which the results will be sorted") @RequestParam(defaultValue = "petId") String sortBy,
			@ApiParam(value = "Sorting order. Use values asc or desc") @RequestParam(defaultValue = "DESC") String order ){

		System.out.println("petname"+petname);
		System.out.println("pageno"+page);
		System.out.println("pageSize"+pageSize);
		System.out.println("sortby"+sortBy);
		System.out.println("order"+order);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(petDao.getAllByPetName(petname, page, pageSize, sortBy, order));
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
