package com.zaver.hotel.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "hotelroom")
public class HotelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer numberOfBeds;

    @Column
    private Integer price;

    @Column
    @ElementCollection
    private List<LocalDate> availability; // days

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable=false) // Foreign key in hotelroom to know which hotel its part of
    private Hotel hotel;

    public HotelRoom(Integer id, String name, String description,
                     Integer numberOfBeds, Integer price,
                     List<LocalDate> availability, Hotel hotel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.availability = availability;
        this.hotel = hotel;
    }

    public HotelRoom() {

    }

    public boolean isRoomAvailable(LocalDate dateRange) {
        return availability.stream()
                .filter(availability -> availability.isBefore(dateRange))
                .filter(availability -> availability.isAfter(dateRange))
                .findFirst().isEmpty();
    }
    
    public boolean enoughRooms(Integer numberOfRooms) {
        return numberOfRooms <= numberOfBeds;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public Integer getPrice() {
        return price;
    }

    public List<LocalDate> getAvailability() {
        return availability;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setAvailability(List<LocalDate> availability) {
        this.availability = availability;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
