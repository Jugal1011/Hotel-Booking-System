package com.hotelBookingSystem.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomDto {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String type;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal basePrice;

    private String[] photos;
    private String[] amenities;

    @NotNull
    @Min(1)
    private Integer totalCount;

    @NotNull
    @Min(1)
    private Integer capacity;
}
