package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        Employee employeeNew = employeeRepository.save(employee);
        return employeeNew;
    }

    public Employee get(Long id){
        Employee employee = employeeRepository.getOne(id);
        return employee;
    }

    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    /*public List<Employee> findEmployeesForService(Employee employee) {
        Set<EmployeeSkill> skills = employee.getSkills();

        List<Employee> employeeList = employeeRepository.findAll();
        List<Employee> employeeListWithSkills = new ArrayList<>();
        for(Employee employeeActual:employeeList){
            if(employeeActual.getSkills().containsAll(skills)){
                employeeListWithSkills.add(employeeActual);
            }
        }
        return employeeListWithSkills;
    }*/

    public List<Employee> findEmployeesForService(LocalDate date, Set<EmployeeSkill> skillSet){//DayOfWeek day) {
        //return employeeRepository.findByDayAvailability(day);
        return employeeRepository.findByDaysAndEmployeeSkills(date.getDayOfWeek(),skillSet);
    }


}
