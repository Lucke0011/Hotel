package com.zaver.hotel.controllers;

import com.zaver.hotel.model.HotelRoom;
import com.zaver.hotel.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping(value = "getAvailableRooms/{date}/{beds}")
    public ResponseEntity<?> getAvailableRooms(@PathVariable("date") LocalDate dateRange,
                                               @PathVariable("beds") Integer numberOfRooms) {
        try {
            List<HotelRoom> availableRooms = hotelService.getAvailableRooms(dateRange, numberOfRooms);
            return ResponseEntity.of(hotelRooms);
        }
    }
}
