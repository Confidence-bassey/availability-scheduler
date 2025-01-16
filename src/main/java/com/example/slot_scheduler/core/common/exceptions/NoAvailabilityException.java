package com.example.slot_scheduler.core.common.exceptions;

public class NoAvailabilityException extends Exception {
    public static String message = "No available time slot found"; 
    public NoAvailabilityException() {
        this(message);
    }

    public NoAvailabilityException(String message) {
        super(message);
    }

    public NoAvailabilityException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
