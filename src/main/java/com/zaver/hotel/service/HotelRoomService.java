package com.zaver.hotel.service;

import com.zaver.hotel.model.HotelRoom;
import com.zaver.hotel.repository.HotelRoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HotelRoomService {

    private final HotelRoomRepository hotelRoomRepository;
    private final BookingService bookingService;

    HotelRoomService(HotelRoomRepository hotelRoomRepository, BookingService bookingService) {
        this.hotelRoomRepository = hotelRoomRepository;
        this.bookingService = bookingService;
    }

    public Optional<HotelRoom> getRoomById(Integer id) {
        return hotelRoomRepository.findById(id);
    }

    public List<HotelRoom> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, Integer numberOfBeds) {
        List<HotelRoom> allRooms = hotelRoomRepository.findAll();

        return allRooms.stream()
                .filter(room -> room.getNumberOfBeds() >= numberOfBeds)
                .filter(room -> bookingService.isRoomAvailable(room.getId(), checkInDate, checkOutDate))
                .toList();
    }

    public HotelRoom saveRoom(HotelRoom room) {
        return hotelRoomRepository.save(room);
    }
}
