package com.AnimalHelper.RestService.AnimalHelperService.Dao;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AnimalHelper.RestService.AnimalHelperService.Beans.Pet;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{

    @Override
    Optional<Pet> findById(Integer id);

    @Override
    Page<Pet> findAll(Pageable pageable);

    Page<Pet> findAllByPetName(String petname, Pageable pageable);

    long countByPetName(String petname);

}
