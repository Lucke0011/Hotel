package com.zaver.hotel.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
//@Data
public class HotelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private int numberOfBeds;
    private int price;

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;

    public HotelRoom() {}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public int getPrice() {
        return price;
    }

    public List<Booking> getBookings() {
        return bookings;
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

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
