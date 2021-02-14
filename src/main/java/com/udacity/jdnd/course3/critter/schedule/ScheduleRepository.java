package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s inner join s.pets sp where sp.id = :petId")
    public List<Schedule> getScheduleForPet(long petId);

    @Query("select distinct s from Schedule s inner join s.employees se with se.id = :employeeId")
    public List<Schedule> getScheduleForEmployee(long employeeId);

    //customer_id
    @Query("select s from Schedule s inner join s.pets sp where sp.owner.id = :customerId")
    public List<Schedule> getScheduleForCustomer(long customerId);

}
