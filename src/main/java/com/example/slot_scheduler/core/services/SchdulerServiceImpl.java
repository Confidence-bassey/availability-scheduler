package com.example.slot_scheduler.core.services;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.slot_scheduler.core.abstractions.data.repositories.EventRepository;
import com.example.slot_scheduler.core.abstractions.services.AvailabilityService;
import com.example.slot_scheduler.core.abstractions.services.SchedulerService;
import com.example.slot_scheduler.core.common.exceptions.NoAvailabilityException;
import com.example.slot_scheduler.core.domain.entities.Schedule;
import com.example.slot_scheduler.core.domain.entities.Schedule.ScheduleType;
import com.example.slot_scheduler.core.domain.models.TimeSlot;
import com.example.slot_scheduler.core.domain.models.requests.NewEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SchdulerServiceImpl implements SchedulerService {

    final EventRepository eventRepository;
    final AvailabilityService availabilityService;

    @Override
    public Schedule addSchedule(NewEvent newEventRequEvent) throws NoAvailabilityException {
        Schedule schedule = newEventRequEvent.toEvent();
        List<TimeSlot> availabilityListForDate = availabilityService.searchAvailability(newEventRequEvent.getStarTime().toLocalDate());
        if(availabilityListForDate.isEmpty()) {
            throw new NoAvailabilityException();
        }
        List<Schedule> otherEventsList = eventRepository.findEventsByDate(newEventRequEvent.getStarTime().toLocalDate())
                        .stream().filter(e -> e.getType() != ScheduleType.OPENING).toList();
        List<Schedule> overlapEvents = otherEventsList.stream().filter(
            slot -> Duration.between(slot.getStarTime(), newEventRequEvent.getStarTime()).toMinutes() < 1 &&
                    Duration.between(slot.getEndTime(), newEventRequEvent.getEndTime()).toMinutes() >= 0
        ).toList();
        if(overlapEvents.size() > 0) {
            throw new NoAvailabilityException(String.format("New event request conflicts with following existing schedules: %s", overlapEvents));
        }
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
