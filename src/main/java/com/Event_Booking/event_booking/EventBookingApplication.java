package com.Event_Booking.event_booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

import java.util.TimeZone;

@SpringBootApplication()
public class EventBookingApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
		SpringApplication.run(EventBookingApplication.class, args);
		System.out.println("Timezone = " + TimeZone.getDefault().getID());
	}

}
