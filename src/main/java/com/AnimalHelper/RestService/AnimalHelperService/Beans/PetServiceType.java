package com.AnimalHelper.RestService.AnimalHelperService.Beans;

public enum PetServiceType {
	
	PET_CARE_PRODUCTS_SALES("Sales Shop for PetCare"),
	PET_GROOMING("Pet Grooming Serivces"),
	PET_SHELTER("Shelter for pet"),
	PET_SHOP("Shop for selling pets"),
	PET_SERVICE("Commercial center offering various services for Pets ");
	
	private String Description;
	
	PetServiceType(String petService){
		Description=petService;
	}

}
