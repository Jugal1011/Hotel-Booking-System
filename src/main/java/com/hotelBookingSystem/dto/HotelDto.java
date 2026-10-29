package com.hotelBookingSystem.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.hotelBookingSystem.entity.HotelContactInfo;
import lombok.Data;

@Data
public class HotelDto {
    private Long id;

    @NotBlank
    @Size(max = 150)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String city;

    private String[] photos;
    private String[] amenities;

    @Valid
    private HotelContactInfo contactInfo;

    private Boolean active;
}
