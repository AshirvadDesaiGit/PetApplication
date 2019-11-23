package com.AnimalHelper.RestService.AnimalHelperService.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import com.AnimalHelper.RestService.AnimalHelperService.Beans.AppUser;
import com.AnimalHelper.RestService.AnimalHelperService.Beans.Pet;
import com.AnimalHelper.RestService.AnimalHelperService.RestExceptions.ResourceNotFoundException;




@Component
public class UserDao {
	@Autowired
	UserRepository userRepository;
	
	
	
	public UserDao()
	{
		
	}
	
	//used to list pets
	public List<AppUser> listUsers() {
		return userRepository.findAll();
		
	}
	
	//used to retrive pet details
		public Resource<AppUser> findUser(int userid)
		{
			
				Optional<AppUser> appUser = userRepository.findById(userid);
				if (!appUser.isPresent())
					throw new ResourceNotFoundException("User " + userid);

				
				Resource<AppUser> resource = new Resource<AppUser>(appUser.get());
				List<Pet> petlist=resource.getContent().getPets();
				return resource;
				
			
			
		}
	//used to savepet to database
	public Resource<AppUser> saveUser(AppUser user) {
		try {
			AppUser savedpet = userRepository.save(user);
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
		
	
}
