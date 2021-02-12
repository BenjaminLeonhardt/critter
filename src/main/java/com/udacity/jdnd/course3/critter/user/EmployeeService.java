package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Employee> findEmployeesForService(Employee employee) {
        Set<EmployeeSkill> skills = employee.getSkills();
        List<Employee> employeeList = new ArrayList<>();
        for(EmployeeSkill skill: skills){
            employeeList = employeeRepository.findEmployeesForService(employee.getId());
        }
        return employeeList;
    }
}
