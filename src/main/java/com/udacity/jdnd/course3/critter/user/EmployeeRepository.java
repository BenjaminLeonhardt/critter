package com.udacity.jdnd.course3.critter.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //@Query("select es from employee_skills es where e.skill = :skill")
    @Query("select e from Employee e left join com.udacity.jdnd.course3.critter.user.employee_skill es on e.id = es.id and e.id = :employeeId")
    public List<Employee> findEmployeesForService(Long employeeId);
}


