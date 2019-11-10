package com.AnimalHelper.RestService.AnimalHelperService.Beans;

public enum PetType {

	DOG("Canis lupus familiaris","Mans Best Friend"),CAT("Felis catus","Furry animal"),TURTLE("Testudines",""),TORTOISE("Testudinidae","");
	
	String biological_name;
	String description;
	
	private PetType(String biological_name, String description) {
		
		this.biological_name = biological_name;
		this.description=description;
	}
	
	
	
	
}
