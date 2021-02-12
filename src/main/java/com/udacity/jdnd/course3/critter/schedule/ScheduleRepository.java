package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select s from Schedule s left join Pet p on s.id = p.id and p.id = :petId")
    public List<Schedule> getScheduleForPet(long petId);

    @Query("select s from Schedule s left join Employee e on s.id = e.id and e.id = :employeeId")
    public List<Schedule> getScheduleForEmployee(long employeeId);

    @Query("select s from Schedule s left join Customer c on s.id = c.id and c.id = :customerId")
    public List<Schedule> getScheduleForCustomer(long customerId);

}
