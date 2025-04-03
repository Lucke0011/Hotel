package com.zaver.hotel.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class HotelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int numberOfBeds;

    @Column
    private int price;

    @Column
    @ElementCollection
    private List<LocalDate> availability; // days

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;

    public boolean isRoomAvailable(LocalDate dateRange) {
        return availability.stream()
                .filter(availability -> availability.isBefore(dateRange))
                .filter(availability -> availability.isAfter(dateRange))
                .findFirst().isEmpty();
    }
    
    public boolean enoughRooms(int bedsNeeded) {
        return bedsNeeded <= numberOfBeds;
    }

}
