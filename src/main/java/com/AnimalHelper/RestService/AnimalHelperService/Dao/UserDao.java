package com.AnimalHelper.RestService.AnimalHelperService.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import com.AnimalHelper.RestService.AnimalHelperService.Beans.AppUser;
import com.AnimalHelper.RestService.AnimalHelperService.Beans.Pet;




@Component
public class UserDao {
	@Autowired
	UserRepository userRepository;
	
	
	
	public UserDao()
	{
		
	}
	
	//used to list pets
	public List<AppUser> listpets() {
		return userRepository.findAll();
		
	}
	//used to savepet to database
	public Resource<AppUser> saveUser(AppUser pet) {
		try {
			AppUser savedpet = userRepository.save(pet);
			Resource<AppUser> resource = new Resource<AppUser>(savedpet);
			return resource;
			
		} catch (Exception e) {
			return null;
		}
	}
			
	//used to retrive pet details
	public Resource<AppUser> updateUser(AppUser appUser)
	{
		try {
			AppUser savedpet = userRepository.save(appUser);
			Resource<AppUser> resource = new Resource<AppUser>(savedpet);
			return resource;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	//used to delete pet details
	public void deleteUser(int userId)
	{
		try {
			userRepository.deleteById(userId);
			
			
		} catch (Exception e) {
			
		}
		
	}
		
	//used to retrive pet details
	public Resource<AppUser> findUser(int userid)
	{
		try {
			Optional<AppUser> appUser = userRepository.findById(userid);
			if (!appUser.isPresent())
				throw new ResourceNotFoundException("id-" + userid);

			
			Resource<AppUser> resource = new Resource<AppUser>(appUser.get());
			List<Pet> petlist=resource.getContent().getPets();
			return resource;
			
		} catch (Exception e) {
			return null;
		}
		
	}
}
