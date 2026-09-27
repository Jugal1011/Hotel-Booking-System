package com.hotelBookingSystem.service;

import com.hotelBookingSystem.dto.HotelDto;
import com.hotelBookingSystem.dto.HotelSearchRequest;
import com.hotelBookingSystem.entities.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
