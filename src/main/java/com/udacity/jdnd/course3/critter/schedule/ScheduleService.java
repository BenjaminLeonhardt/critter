package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule){
        Schedule scheduleNew = scheduleRepository.save(schedule);
        return scheduleNew;
    }

    public Schedule get(Long id){
        Schedule schedule = scheduleRepository.getOne(id);
        return schedule;
    }

    public List<Schedule> getAllSchedules(){
        List<Schedule> scheduleList = scheduleRepository.findAll();
        return scheduleList;
    }

    public List<Schedule> getScheduleForPet(long petId) {
        List<Schedule> scheduleList = scheduleRepository.getScheduleForPet(petId);
        return scheduleList;
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        List<Schedule> scheduleList = scheduleRepository.getScheduleForEmployee(employeeId);
        return scheduleList;
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        List<Schedule> scheduleList = scheduleRepository.getScheduleForCustomer(customerId);
        return scheduleList;
    }

}
