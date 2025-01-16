package com.example.slot_scheduler.core.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.slot_scheduler.core.abstractions.data.repositories.EventRepository;
import com.example.slot_scheduler.core.abstractions.services.AvailabilityService;
import com.example.slot_scheduler.core.domain.entities.Schedule;
import com.example.slot_scheduler.core.domain.entities.Schedule.ScheduleType;
import com.example.slot_scheduler.core.domain.models.SlotKind;
import com.example.slot_scheduler.core.domain.models.TimeSlot;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AvailabilityServiceImpl implements AvailabilityService {

    final EventRepository eventRepository;

    @Override
    public List<TimeSlot> searchAvailability(LocalDate date) {
        List<Schedule> schdules = eventRepository.findEventsByDate(date);
        //Assumes a default full availablity  if none is defined
        Schedule availabilityEvent = schdules.stream()
                                        .filter(s -> s.getType() == ScheduleType.OPENING)
                                        .findFirst().orElse(Schedule.ofDefaultDayAvailability(date));

        List<Schedule> otherSchedules = schdules.stream().filter(
            s -> s.getType() != ScheduleType.OPENING
        ).collect(Collectors.toList());

        List<TimeSlot> slots = new ArrayList<>();

        LocalDateTime startTime = availabilityEvent.getStarTime();
        for (Schedule schedule : otherSchedules) {
            if(schedule.getStarTime().isAfter(startTime)) {
                slots.add(TimeSlot.of(startTime, schedule.getStarTime(), SlotKind.AVAILABILITY));
            }
            startTime = schedule.getEndTime();
        }
        if(startTime.isBefore(availabilityEvent.getEndTime())) {
            slots.add(TimeSlot.of(startTime, availabilityEvent.getEndTime(), SlotKind.AVAILABILITY));
        }

        return slots.stream().filter(s -> Duration.between(s.starTime(), s.endTime()).toMinutes() > 0)
                .toList();
    }
    
}
