package com.zaver.hotel.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(mappedBy="hotel")
    private List<HotelRoom> rooms;

    public Hotel(Integer id, List<HotelRoom> rooms) {
        this.id = id;
        this.rooms = rooms;
    }

    public Hotel() {}

    public Integer getId() {
        return id;
    }

    public List<HotelRoom> getRooms() {
        return rooms;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRooms(List<HotelRoom> rooms) {
        this.rooms = rooms;
    }
}
