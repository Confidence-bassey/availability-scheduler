package com.example.slot_scheduler.core.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.stereotype.Service;

import com.example.slot_scheduler.core.abstractions.data.repositories.EventRepository;
import com.example.slot_scheduler.core.abstractions.services.SchedulerService;
import com.example.slot_scheduler.core.domain.entities.Schedule;
import com.example.slot_scheduler.core.domain.models.requests.NewEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SchdulerServiceImpl implements SchedulerService {

    final EventRepository eventRepository;

    @Override
    public Schedule addSchedule(NewEvent newEventRequEvent) {
        Schedule schedule = newEventRequEvent.toEvent();
        eventRepository.save(schedule);
        return schedule;
    }

    @Override
    public Optional<Schedule> getScheduleById(long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Schedule> getSchedules() {
        return eventRepository.findAll();
    }

    @Override
    public void updateSchedule(long id, Schedule updatedSchedule) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSchedule'");
    }

    @Override
    public boolean removeSchedule(long id) {
        Optional<Schedule> event = eventRepository.findById(id);
        event.ifPresent(e -> eventRepository.delete(e));
        return true;
    }
    
}
