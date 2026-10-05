package com.hotelBookingSystem.controller;

import com.hotelBookingSystem.dto.HotelDto;
import com.hotelBookingSystem.dto.HotelInfoDto;
import com.hotelBookingSystem.dto.HotelMinPriceDto;
import com.hotelBookingSystem.dto.HotelSearchRequest;
import com.hotelBookingSystem.service.HotelService;
import com.hotelBookingSystem.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {

        Page<HotelDto> page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId) {
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }

    @GetMapping("/searchWithMinPrice")
    public ResponseEntity<Page<HotelMinPriceDto>> searchHotelsWithMinPrice(@RequestBody HotelSearchRequest hotelSearchRequest) {

        Page<HotelMinPriceDto> page = inventoryService.searchHotelsWithMinPrice(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

}
