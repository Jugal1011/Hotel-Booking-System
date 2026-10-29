package com.hotelBookingSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelSearchRequest {
    @NotBlank
    @Size(max = 100)
    private String city;

    @NotNull
    @FutureOrPresent
    private LocalDate startDate;

    @NotNull
    @FutureOrPresent
    private LocalDate endDate;

    @NotNull
    @Min(1)
    private Integer roomsCount;

    @NotNull
    @Min(0)
    private Integer page=0;

    @NotNull
    @Min(1)
    private Integer size=10;

    @AssertTrue(message = "End date must be on or after start date")
    @JsonIgnore
    public boolean isDateRangeValid() {
        return startDate == null || endDate == null || !endDate.isBefore(startDate);
    }
}
