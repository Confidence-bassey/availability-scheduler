package com.example.slot_scheduler.api.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.slot_scheduler.core.abstractions.services.AvailabilityService;
import com.example.slot_scheduler.core.abstractions.services.SchedulerService;
import com.example.slot_scheduler.core.domain.entities.Schedule;
import com.example.slot_scheduler.core.domain.models.TimeSlot;
import com.example.slot_scheduler.core.domain.models.requests.NewEvent;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")

public class SchedulesController {

    private final SchedulerService schedulerService;
    private final AvailabilityService availabilityService;

    public SchedulesController(SchedulerService schedulerService, AvailabilityService availabilityService) {
        this.schedulerService = schedulerService;
        this.availabilityService = availabilityService;
        
    }


    @GetMapping("")
    public ResponseEntity<?> getAllEvents() {
        return ResponseEntity.ok().body(
            schedulerService.getSchedules()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSchedules(@PathVariable("id") long id) {
        Optional<Schedule> event = schedulerService.getScheduleById(id);
        if(event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> addSchedule(@RequestBody @Valid NewEvent request) {
        Schedule createdEvent = schedulerService.addSchedule(request);
        return ResponseEntity.ok(createdEvent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeSchedule(@PathVariable long id) {
        Optional<Schedule> schedule = schedulerService.getScheduleById(id);
        if(schedule.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        schedulerService.removeSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("availability/{date}")
    public ResponseEntity<?> getAvailability(@PathVariable("date") LocalDateTime date) {
        List<TimeSlot> availabilitySlots = availabilityService.searchAvailability(date);
        return ResponseEntity.ok(availabilitySlots);
    }
    
}
