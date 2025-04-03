package com.zaver.hotel.service;

import com.zaver.hotel.model.Booking;
import com.zaver.hotel.model.HotelRoom;
import com.zaver.hotel.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(HotelRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        if (!isRoomAvailable(room.getId(), checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("Room is not available for the selected dates");
        }

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setBookingReference(generateBookingReference());

        return bookingRepository.save(booking);
    }

    public Optional<List<Booking>> findBookingsByRange(LocalDate checkInDate, LocalDate checkOutDate) {
        return Optional.ofNullable(bookingRepository.findBookings(checkInDate, checkOutDate));
    }

    public Booking cancelBooking(String bookingReference) {
        Booking booking = bookingRepository.findByBookingReference(bookingReference);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }
        booking.setCancelled(true);
        return bookingRepository.save(booking);
    }

    public boolean isRoomAvailable(Integer roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(roomId, checkInDate, checkOutDate);
        return overlappingBookings.isEmpty();
    }

    private String generateBookingReference() {
        return UUID.randomUUID().toString();
    }
}
