package com.zaver.hotel.repository;

import com.zaver.hotel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

//    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId " +
//            "AND b.cancelled = false " +
//            "AND ((b.checkInDate <= :checkOutDate AND b.checkOutDate >= :checkInDate))")
    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId " +
            "AND b.cancelled = false " +
            "AND ((:checkInDate <= b.checkOutDate OR :checkOutDate >= b.checkInDate)) ")
    List<Booking> findOverlappingBookings(
            @Param("roomId") Integer roomId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate);

    @Query("SELECT b FROM Booking b WHERE b.cancelled = false " +
            "AND ((:checkInDate <= b.checkOutDate OR :checkOutDate >= b.checkInDate)) ")
    List<Booking> findBookings(@Param("checkInDate") LocalDate checkInDate,
                               @Param("checkOutDate") LocalDate checkOutDate);

    Booking findByBookingReference(String bookingReference);
}
