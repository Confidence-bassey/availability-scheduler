package com.example.slot_scheduler.core.domain.models.requests;

import java.time.LocalDateTime;

import com.example.slot_scheduler.core.domain.entities.Schedule;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class NewEvent {

    @NotBlank
    private String name;

    @NotNull
    private Schedule.ScheduleType type;

    @NotNull
    private LocalDateTime starTime;

    @NotNull
    @Future
    private LocalDateTime endTime;


    public Schedule toEvent() {
        Schedule event = new Schedule();
            event.setName(this.name);
            event.setType(this.type);
            event.setStarTime(this.starTime);
            event.setEndTime(this.endTime);

            return event;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Schedule.ScheduleType getType() {
        return type;
    }

    public void setType(Schedule.ScheduleType type) {
        this.type = type;
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
}
