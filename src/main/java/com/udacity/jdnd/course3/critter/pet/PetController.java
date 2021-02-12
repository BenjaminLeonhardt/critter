package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;


    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertPetDTOToEntity(petDTO);
        Customer customer = customerService.get(petDTO.getOwnerId());
        if(customer.getPetList()==null){
            customer.setPetList(new ArrayList<Pet>());
        }
        customer.getPetList().add(pet);
        pet.setOwner(customer);
        Pet petNew = petService.save(pet);
        return convertEntityToPetDTO(petNew);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.get(petId);
        return convertEntityToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> petList = petService.getAllPets();
        List<PetDTO> petDTOList = new ArrayList<>();
        for(Pet pet:petList){
            petDTOList.add(convertEntityToPetDTO(pet));
        }
        return petDTOList;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> petList = petService.getPetsByOwner(ownerId);
        List<PetDTO> petDTOList = new ArrayList<>();
        for(Pet pet:petList){
            petDTOList.add(convertEntityToPetDTO(pet));
        }
        return petDTOList;
    }








    /*
     * converter for DTOs
     * */


    private static PetDTO convertEntityToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet,petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    private static Pet convertPetDTOToEntity(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }
}
