package com.example.slot_scheduler.core.abstractions.services;

import java.util.List;
import java.util.Optional;

import com.example.slot_scheduler.core.common.exceptions.NoAvailabilityException;
import com.example.slot_scheduler.core.domain.entities.Schedule;
import com.example.slot_scheduler.core.domain.models.requests.NewEvent;

public interface SchedulerService {
    Schedule addSchedule(NewEvent event) throws NoAvailabilityException;
    Optional<Schedule> getScheduleById(long id);
    List<Schedule> getSchedules();
    void updateSchedule(long id, Schedule updatedSchedule);
    boolean removeSchedule(long id);
    
}
