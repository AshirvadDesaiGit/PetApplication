package com.AnimalHelper.RestService.AnimalHelperService.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.AnimalHelper.RestService.AnimalHelperService.Beans.AppUser;
import com.AnimalHelper.RestService.AnimalHelperService.Beans.Pet;
import com.AnimalHelper.RestService.AnimalHelperService.Dao.PetDao;
import com.AnimalHelper.RestService.AnimalHelperService.Dao.UserDao;
import com.AnimalHelper.RestService.AnimalHelperService.RestExceptions.ResourceNotFoundException;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PetDao petDao;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping(path="/user/all")
	public List<AppUser> getUsers() {
		
		try {
				return  userDao.listUsers();
				
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
	
	
	
	
	@DeleteMapping(path="/user/{userId}")
	public ResponseEntity<Pet> removeUser(@PathVariable int userId)
	{
			userDao.deleteUser(userId); //have to delete all the pets first
			return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	@PostMapping(path="/user/{userId}/pet")
	public ResponseEntity<Pet> addPet(@Valid @RequestBody Pet pet,@PathVariable int userId)
	{
		
			AppUser appuser=userDao.findUser(userId).getContent();
			pet.setPetOwner(appuser);
			Resource<Pet> savedPet=petDao.savePet(pet);
			//this is one so that the uri of the saved object is part of the response
			URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPet.getContent().getPetId()).toUri();
			return ResponseEntity.created(location).build();
		
		
	}
	
}
