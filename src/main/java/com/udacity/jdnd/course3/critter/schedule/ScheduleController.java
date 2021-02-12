package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertScheduleDTOToEntity(scheduleDTO);
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
            scheduleDTOList.add(convertEntityToScheduleDTO(schedule));
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

    private static ScheduleDTO convertEntityToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule,scheduleDTO);
        return scheduleDTO;
    }

    private static Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        return schedule;
    }

}
