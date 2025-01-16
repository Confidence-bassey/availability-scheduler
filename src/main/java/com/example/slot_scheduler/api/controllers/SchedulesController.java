package com.example.slot_scheduler.api.controllers;

import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.slot_scheduler.core.abstractions.data.repositories.EventRepository;
import com.example.slot_scheduler.core.domain.entities.Schedule;
import com.example.slot_scheduler.core.domain.models.requests.NewEvent;

@RestController
@RequestMapping("/api/schedules")

public class SchedulesController {

    private final EventRepository eventRepository;

    public SchedulesController(EventRepository eventRepo) {
        this.eventRepository = eventRepo;
    }


    @GetMapping("")
    public ResponseEntity<?> getAllEvents() {
        return ResponseEntity.ok().body(eventRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSchedules(@PathVariable("id") long id) {
        Optional<Schedule> event = eventRepository.findById(id);
        if(event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> addSchedule(@RequestBody @Valid NewEvent request) {

        Schedule eventToCreate = request.toEvent();
        eventRepository.save(eventToCreate);
        return ResponseEntity.ok(eventToCreate);
    }
    
}
