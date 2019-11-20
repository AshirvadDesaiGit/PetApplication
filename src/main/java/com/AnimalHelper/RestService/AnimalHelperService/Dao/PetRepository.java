package com.AnimalHelper.RestService.AnimalHelperService.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AnimalHelper.RestService.AnimalHelperService.Beans.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{

}
