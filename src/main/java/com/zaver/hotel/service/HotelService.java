package com.zaver.hotel.service;

import com.zaver.hotel.model.HotelRoom;
import com.zaver.hotel.repository.HotelRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private HotelRoomRepository hotelRoomRepository;

    public List<HotelRoom> getAvailableRooms(LocalDate dateRange, Integer numberOfRooms) {
        List<HotelRoom> hotelRooms = (List<HotelRoom>) hotelRoomRepository.findAll();
        return hotelRooms.stream()
                .filter(hotelRoom -> hotelRoom.isRoomAvailable(dateRange))
                .filter(hotelRoom -> hotelRoom.enoughRooms(numberOfRooms))
                .collect(Collectors.toList());
    }

    public Integer bookHotelRoom(HotelRoom hotelRoom, LocalDate dateRange) {
        hotelRoom.getAvailability().remove(dateRange);
        hotelRoomRepository.save(hotelRoom);
        return hotelRoom.getId();
    }


}
