package com.udacity.jdnd.course3.critter.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //@Query("select es from employee_skills es where e.skill = :skill")
    //@Query("select e from Employee e left join com.udacity.jdnd.course3.critter.user.employee_skill es on e.id = es.id and e.id = :employeeId")
    //@Query("select e from Employee e where e.id = :employeeId")
    //public List<Employee> findEmployeesForService(Long employeeId);

    @Query("select e from Employee e where :day member of e.daysAvailable")
    List<Employee> findByDayAvailability(DayOfWeek day);

    @Query("SELECT distinct e FROM Employee e inner join e.skills es inner join e.daysAvailable ed WHERE es IN :employeeSkills AND ed IN :dayOfWeek")
    List<Employee> findByDaysAndEmployeeSkills(DayOfWeek dayOfWeek, Set<EmployeeSkill> employeeSkills);

}


