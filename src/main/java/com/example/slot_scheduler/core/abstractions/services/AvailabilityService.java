package com.example.slot_scheduler.core.abstractions.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.slot_scheduler.core.domain.models.TimeSlot;

public interface AvailabilityService {
    List<TimeSlot> searchAvailability(LocalDate date);
}
