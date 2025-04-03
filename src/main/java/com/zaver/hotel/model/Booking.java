package com.zaver.hotel.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
//@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bookingReference;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private HotelRoom room;

    private boolean cancelled = false;

    public Booking() {}

    public Integer getId() {
        return id;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public HotelRoom getRoom() {
        return room;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setRoom(HotelRoom room) {
        this.room = room;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
