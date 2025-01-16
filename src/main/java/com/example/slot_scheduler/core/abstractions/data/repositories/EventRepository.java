package com.example.slot_scheduler.core.abstractions.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slot_scheduler.core.domain.entities.Schedule;

@Repository
public interface EventRepository extends JpaRepository<Schedule, Long> {
    
}
