package com.zaver.hotel;

import com.zaver.hotel.model.HotelRoom;
import com.zaver.hotel.service.BookingService;
import com.zaver.hotel.service.HotelRoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initData(HotelRoomService hotelRoomService, BookingService bookingService) {
        return args -> {
            HotelRoom room1 = new HotelRoom();
            room1.setName("Room1");
            room1.setDescription("Room1");
            room1.setNumberOfBeds(1);
            room1.setPrice(100);
            hotelRoomService.saveRoom(room1);

            HotelRoom room2 = new HotelRoom();
            room2.setName("Room2");
            room2.setDescription("Room2");
            room2.setNumberOfBeds(2);
            room2.setPrice(200);
            hotelRoomService.saveRoom(room2);

            HotelRoom room3 = new HotelRoom();
            room3.setName("Room3");
            room3.setDescription("Room3");
            room3.setNumberOfBeds(3);
            room3.setPrice(300);
            hotelRoomService.saveRoom(room3);

            bookingService.createBooking(room1, LocalDate.now(), LocalDate.now().plusDays(3));
            bookingService.createBooking(room2, LocalDate.now(), LocalDate.now().plusDays(7));
        };
    }
}
