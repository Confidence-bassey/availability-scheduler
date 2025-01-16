package com.example.slot_scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.slot_scheduler")
public class SlotSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlotSchedulerApplication.class, args);
	}

}
