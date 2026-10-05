package com.hotelBookingSystem.service;

import com.hotelBookingSystem.dto.HotelDto;
import com.hotelBookingSystem.dto.HotelMinPriceDto;
import com.hotelBookingSystem.dto.HotelSearchRequest;
import com.hotelBookingSystem.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest);

    Page<HotelMinPriceDto> searchHotelsWithMinPrice(HotelSearchRequest hotelSearchRequest);
}
