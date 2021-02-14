package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

//    @Autowired
//    NamedParameterJdbcTemplate jdbcTemplate;

//    private static final RowMapper<Schedule> scheduleRowMapper =
//            new BeanPropertyRowMapper<>(Schedule.class);

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

        //List<Schedule> schedule = jdbcTemplate.query("select schedule.* from schedule left join schedule_pets on schedule.id = schedule_pets.schedule_id left join pet on schedule_pets.pets_id = pet.id left join customer on pet.customer_id = customer.id and customer.id = :customerId",
        //        new MapSqlParameterSource("customerId", customerId),
        //        scheduleRowMapper);

        return scheduleList;
    }

}
