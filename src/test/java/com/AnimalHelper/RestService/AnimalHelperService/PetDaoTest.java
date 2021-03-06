package com.AnimalHelper.RestService.AnimalHelperService;

import com.AnimalHelper.RestService.AnimalHelperService.Beans.Pet;
import com.AnimalHelper.RestService.AnimalHelperService.Dao.PetDao;
import com.AnimalHelper.RestService.AnimalHelperService.Dao.PetRepository;
import com.AnimalHelper.RestService.AnimalHelperService.RestExceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.hateoas.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PetDaoTest {

    //private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetDao petDao;

    @BeforeClass
    public static void beforeClass(){

    }

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testRetriveAllPets (){
        List<Pet> petList = new ArrayList<Pet>();

        Pet pet1=new Pet.Builder(1, "pet1").petAddress("arlem").petLocation("goa").build();
        Pet pet2=new Pet.Builder(2, "pet2").petAddress("barebhat").petLocation("india").build();
        Pet pet3=new Pet.Builder(3, "pet3").petAddress("raia").petLocation("ponda").build();
        petList.add(pet1);
        petList.add(pet2);
        petList.add(pet3);
        when(petRepository.findAll()).thenReturn(petList);
        List<Pet> pets = petDao.listpets();
        verify(petRepository).findAll();
        //when (petRepository.findAll()).thenReturn(petList);
        //Assert.assertEquals(petRepository.findAll().size(),3);
    }

    @Test
    public void  testFindPet(){
        Pet pet1=new Pet.Builder(1, "pet1").petAddress("arlem").petLocation("goa").build();
        Optional<Pet> p = Optional.ofNullable(pet1);
        when(petRepository.findById(1)).thenReturn(p);
        Resource<Pet> pet = petDao.findPet(1);
        verify(petRepository, times(1)).findById(1);
        Assert.assertEquals(pet.getContent().getPetName(), pet1.getPetName());

        when(petRepository.findById(999)).thenThrow(new ResourceNotFoundException("Pet id 999 not found"));
        Resource<Pet> petNotFound = petDao.findPet(999);
        verify(petRepository, times(1)).findById(999);
        Assert.assertEquals(null, petNotFound);
    }

    @Test
    public void testSavePet(){
        Pet pet1=new Pet.Builder(1, "pet1").petAddress("arlem").petLocation("goa").build();
        when(petRepository.save(pet1)).thenReturn(pet1);

        Resource<Pet> pet = petDao.savePet(pet1);

        verify(petRepository).save(any());
        Assert.assertEquals(pet.getContent().getPetName(), pet1.getPetName());

        Pet pet2=new Pet.Builder(1, "pet1").petAddress("arlem").petLocation("goa").build();
        when(petRepository.save(pet2)).thenThrow(new RuntimeException());

        Resource<Pet> petex = petDao.savePet(pet2);

        verify(petRepository, times(1)).save(pet2);
        verify(petRepository, times(2)).save(any());
        Assert.assertEquals(null, petex);
    }

    @Test
    public void testUpdatePet(){
        Pet pet1=new Pet.Builder(1, "pet1").petAddress("arlem").petLocation("goa").build();
        when(petRepository.save(pet1)).thenReturn(pet1);

        Resource<Pet> pet = petDao.updatePet(pet1);

        verify(petRepository).save(any());
        Assert.assertEquals(pet.getContent().getPetName(), pet1.getPetName());

        Pet pet2=new Pet.Builder(1, "pet1").petAddress("arlem").petLocation("goa").build();
        when(petRepository.save(pet2)).thenThrow(new RuntimeException());

        Resource<Pet> petex = petDao.updatePet(pet2);

        verify(petRepository, times(1)).save(pet2);
        verify(petRepository, times(2)).save(any());
        Assert.assertEquals(null, petex);
    }

    @Test
    public void testDeletePet(){
        doNothing().when(petRepository).deleteById(1);
        petDao.deletePet(1);

        verify(petRepository,times(1)).deleteById(1);
    }
}
