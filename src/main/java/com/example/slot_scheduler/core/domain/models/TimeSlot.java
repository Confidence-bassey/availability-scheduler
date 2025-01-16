package com.example.slot_scheduler.core.domain.models;

import java.time.LocalDateTime;


public record TimeSlot(LocalDateTime starTime, LocalDateTime endTime) implements Comparable<TimeSlot> {

    @Override
    public int compareTo(TimeSlot o) {
        return starTime.compareTo(o.starTime());
    }

    public static TimeSlot of(LocalDateTime startTime, LocalDateTime endTime) {
        return new TimeSlot(startTime, endTime);
    }

    @Override
    public String toString() {
        return String.format("{} {}-{}", starTime.toLocalDate(), starTime.toLocalTime(), endTime.toLocalTime());
    }
} 
