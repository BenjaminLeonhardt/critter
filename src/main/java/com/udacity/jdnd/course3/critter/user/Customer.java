package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Customer extends User {



    private String phoneNumber;

    private String notes;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Pet> PetList;

    public Customer() {
    }

    public Customer(Long id, String name, String phoneNumber, String notes, List<Pet> petList) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        PetList = petList;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPetList() {
        return PetList;
    }

    public void setPetList(List<Pet> petList) {
        PetList = petList;
    }
}
