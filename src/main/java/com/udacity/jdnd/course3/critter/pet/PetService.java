package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public Pet save(Pet pet){
        Pet petNew = petRepository.save(pet);
        return petNew;
    }

    public Pet get(Long id){
        Pet pet = petRepository.getOne(id);
        return pet;
    }

    public List<Pet> getAllPets(){
        List<Pet> petList = petRepository.findAll();
        return petList;
    }

    public List<Pet> getPetsByOwner(Long ownerId){
        List<Pet> petList = petRepository.getPetsByOwner(ownerId);
        return petList;
    }

}
