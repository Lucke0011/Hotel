package com.zaver.hotel;

import com.zaver.hotel.controllers.HotelRoomController;
import com.zaver.hotel.model.HotelRoom;
import com.zaver.hotel.service.HotelRoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HotelRoomController.class)
public class HotelRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HotelRoomService hotelRoomService;

    @Test
    public void testFindAvailableRooms() throws Exception {
        HotelRoom room1 = new HotelRoom();
        room1.setId(1);
        room1.setName("Room1");
        room1.setNumberOfBeds(1);

        HotelRoom room2 = new HotelRoom();
        room2.setId(2);
        room2.setName("Room2");
        room2.setNumberOfBeds(2);

        List<HotelRoom> rooms = Arrays.asList(room1, room2);

        when(hotelRoomService.findAvailableRooms(any(LocalDate.class), any(LocalDate.class), anyInt()))
                .thenReturn(rooms);

        mockMvc.perform(get("/api/rooms/available")
                        .param("checkInDate", "2025-04-03")
                        .param("checkOutDate", "2025-04-06")
                        .param("numberOfBeds", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Room1"))
                .andExpect(jsonPath("$[1].name").value("Room2"));
    }

}
