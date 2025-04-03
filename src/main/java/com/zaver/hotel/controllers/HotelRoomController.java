package com.zaver.hotel.controllers;

import com.zaver.hotel.model.HotelRoom;
import com.zaver.hotel.service.HotelRoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class HotelRoomController {

    private final HotelRoomService hotelRoomService;

    public HotelRoomController(HotelRoomService hotelRoomService) {
        this.hotelRoomService = hotelRoomService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<HotelRoom>> findAvailableRooms(
            @RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam("beds") Integer numberOfBeds) {

        try {
            List<HotelRoom> availableRooms = hotelRoomService.findAvailableRooms(checkInDate, checkOutDate, numberOfBeds);
            return ResponseEntity.ok(availableRooms);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<HotelRoom> createRoom(@RequestBody HotelRoom room) {
        try {
            HotelRoom hotelRoom = hotelRoomService.saveRoom(room);
            return ResponseEntity.ok(hotelRoom);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
