package com.zaver.hotel.controllers;

import com.zaver.hotel.model.Booking;
import com.zaver.hotel.model.HotelRoom;
import com.zaver.hotel.service.BookingService;
import com.zaver.hotel.service.HotelRoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final HotelRoomService hotelRoomService;

    public BookingController(BookingService bookingService, HotelRoomService hotelRoomService) {
        this.bookingService = bookingService;
        this.hotelRoomService = hotelRoomService;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(
            @RequestParam("roomId") Integer roomId,
            @RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate) {


        try {
            HotelRoom room = hotelRoomService.getRoomById(roomId)
                    .orElseThrow(() -> new IllegalArgumentException("Room not found"));
            String bookingReference = bookingService.createBooking(room, checkInDate, checkOutDate).getBookingReference();
            return ResponseEntity.ok(bookingReference);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findBookings(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        try {
            List<Booking> bookings = bookingService.findBookingsByRange(fromDate, toDate)
                    .orElseThrow(() -> new IllegalArgumentException("No bookings found"));
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{bookingReference}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable String bookingReference) {
        try {
            Booking booking = bookingService.cancelBooking(bookingReference);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
