package com.hotelBookingSystem.dto;

import com.hotelBookingSystem.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelMinPriceDto {
    private Hotel hotel;
    private BigDecimal price;
}
