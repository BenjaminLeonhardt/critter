package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer save(Customer customer){
        Customer customerNew = customerRepository.save(customer);
        return customerNew;
    }

    public Customer get(Long id){
        Customer customer = customerRepository.getOne(id);
        return customer;
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        return customerList;
    }

    public Customer getOwnerByPet(Long petId){
        Customer customerList = customerRepository.getOwnerByPet(petId);
        return customerList;
    }



}
