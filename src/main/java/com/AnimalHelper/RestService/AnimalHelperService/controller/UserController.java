package com.AnimalHelper.RestService.AnimalHelperService.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.AnimalHelper.RestService.AnimalHelperService.Beans.AppUser;
import com.AnimalHelper.RestService.AnimalHelperService.Beans.Pet;
import com.AnimalHelper.RestService.AnimalHelperService.Dao.PetDao;
import com.AnimalHelper.RestService.AnimalHelperService.Dao.UserDao;
import com.AnimalHelper.RestService.AnimalHelperService.RestExceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping(path="/user/all")
	public List<AppUser> getPets() {
		
		try {
				return  userDao.listpets();
				
		} catch (Exception e) {
			return null;
		}
		
		
	}
	
	@GetMapping(path="/user/{userId}")
	public Resource<AppUser> getUser(@PathVariable int userId) {//@RequestHeader(name ="Accept-Language",required=false) Locale locale
		
		Resource<AppUser> resource= userDao.findUser(userId);
		if (resource==null)
			throw new ResourceNotFoundException("this resource does not exist");
		
		
		//HATEOS
		//hateoas resource
		//resource = new Resource<Pet>(pet);
		
		  ControllerLinkBuilder linkAllPets= linkTo(methodOn(this.getClass()).getUserPet(userId)); //method from
		   resource.add(linkAllPets.withRel("All Pets"));
		 
		return resource;
	}
	
	@GetMapping(path="/user/{userId}/pets")
	public List<Pet> getUserPet(@PathVariable int userId) {//@RequestHeader(name ="Accept-Language",required=false) Locale locale
		
		Resource<AppUser> resource= userDao.findUser(userId);
		if (resource==null)
			throw new ResourceNotFoundException("this resource does not exist");
		
		return resource.getContent().getPets();
	}
	
	
	@PostMapping(path="/user")
	public ResponseEntity<AppUser> addUser(@Valid @RequestBody AppUser user)
	{
		try {
			Resource<AppUser> savedUser=userDao.saveUser(user);
			//this is one so that the uri of the saved object is part of the response in the header
			URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getContent().getId()).toUri();
			//return ResponseEntity.created(location).build();
			return new ResponseEntity<AppUser>(savedUser.getContent(), HttpStatus.OK);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	@PutMapping(path="/user/{userId}")
	public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user,@PathVariable int userId)
	{
		try {
			user.setId(userId);
			userDao.updateUser(user);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	
	
	@DeleteMapping(path="/user/{userid}")
	public ResponseEntity<Pet> removeUser(@PathVariable int userid)
	{
			userDao.deleteUser(userid);
			return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	
	
}
