package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s left join s.pets sp where sp.id = :petId")
    public List<Schedule> getScheduleForPet(long petId);

    @Query("select s from Schedule s left join s.employees se where se.id = :employeeId")
    public List<Schedule> getScheduleForEmployee(long employeeId);

    //customer_id
    @Query("select s from Schedule s inner join s.pets sp where sp.owner.id = :customerId")
    public List<Schedule> getScheduleForCustomer(long customerId);

}
