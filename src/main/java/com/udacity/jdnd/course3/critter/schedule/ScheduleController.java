package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertScheduleDTOToEntity(scheduleDTO);
        for(Employee employee:schedule.getEmployees()){
            if(employee.getSchedule()==null){
                employee.setSchedule(new ArrayList<>());
            }
            employee.getSchedule().add(schedule);
        }

        for(Pet pet:schedule.getPets()){
            if(pet.getSchedule()==null){
                pet.setSchedule(new ArrayList<>());
            }
            pet.getSchedule().add(schedule);
        }

        Schedule newSchedule = scheduleService.save(schedule);
        return convertEntityToScheduleDTO(newSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> scheduleList = scheduleService.getAllSchedules();
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for(Schedule schedule:scheduleList){
            scheduleDTOList.add(convertEntityToScheduleDTO(schedule));
        }
        return scheduleDTOList;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> scheduleList = scheduleService.getScheduleForPet(petId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for(Schedule schedule:scheduleList){
            scheduleDTOList.add(convertEntityToScheduleDTO(schedule));
        }
        return scheduleDTOList;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleList = scheduleService.getScheduleForEmployee(employeeId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for(Schedule schedule:scheduleList){
            if(schedule!=null){
                scheduleDTOList.add(convertEntityToScheduleDTO(schedule));
            }
        }
        return scheduleDTOList;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleList = scheduleService.getScheduleForCustomer(customerId);
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for(Schedule schedule:scheduleList){
            scheduleDTOList.add(convertEntityToScheduleDTO(schedule));
        }
        return scheduleDTOList;
    }








    /*
    * converter for DTOs
    * */

    private ScheduleDTO convertEntityToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setEmployeeIds(new ArrayList<>());
        scheduleDTO.setPetIds(new ArrayList<>());
        scheduleDTO.setActivities(new HashSet<>());
        if(schedule.getEmployees()!=null){
            for(Employee employee:schedule.getEmployees()){
                scheduleDTO.getEmployeeIds().add(employee.getId());
            }
        }
        if(schedule.getPets()!=null){
            for (Pet pet:schedule.getPets()){
                scheduleDTO.getPetIds().add(pet.getId());
            }
        }
        if(schedule.getActivities()!=null){
            for(EmployeeSkill skill:schedule.getActivities()){
                scheduleDTO.getActivities().add(skill);
            }
        }



        BeanUtils.copyProperties(schedule,scheduleDTO);
        return scheduleDTO;
    }

    private Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        List<Employee> employeeList = new ArrayList<>();
        for(long employeeId : scheduleDTO.getEmployeeIds()){
            employeeList.add(employeeService.get(employeeId));
        }
        schedule.setEmployees(employeeList);

        List<Pet> petList = new ArrayList<>();
        for(long petId : scheduleDTO.getPetIds()){
            petList.add(petService.get(petId));
        }
        schedule.setPets(petList);

        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }

}
