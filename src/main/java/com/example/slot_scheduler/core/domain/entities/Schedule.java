package com.example.slot_scheduler.core.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "schedule")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private ScheduleType type;

    private LocalDateTime starTime;
    private LocalDateTime endTime;

    public ScheduleType getType() {
        return type;
    }

    public void setType(ScheduleType type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStarTime() {
        return starTime;
    }

    public void setStarTime(LocalDateTime starTime) {
        this.starTime = starTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("%s - %s from %s - %s", this.getStarTime().toLocalDate(), this.getType(), this.getStarTime().toLocalTime(), this.getEndTime().toLocalTime());
    }

    public enum ScheduleType {
        TASK, EVENT, APPOINTMENT, OPENING
    }


    public static Schedule ofDefaultDayAvailability(LocalDate date) {
        LocalDateTime day = date.atStartOfDay();
        return Schedule.builder()
                .starTime(day.withHour(9))
                .endTime(day.withHour(17))
                .build();
    }
    
}
