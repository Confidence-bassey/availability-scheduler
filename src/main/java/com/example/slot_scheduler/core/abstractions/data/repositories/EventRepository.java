package com.example.slot_scheduler.core.abstractions.data.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.slot_scheduler.core.domain.entities.Schedule;

@Repository
public interface EventRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s from Schedule s where FUNCTION('DATE', s.starTime)=:date order by s.endTime")
    List<Schedule> findEventsByDate(@Param("date")LocalDate date);
    
}
