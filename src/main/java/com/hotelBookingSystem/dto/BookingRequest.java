package com.hotelBookingSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {
    @NotNull
    @Min(1)
    private Long hotelId;

    @NotNull
    @Min(1)
    private Long roomId;

    @NotNull
    @FutureOrPresent
    private LocalDate checkInDate;

    @NotNull
    @FutureOrPresent
    private LocalDate checkOutDate;

    @NotNull
    @Min(1)
    private Integer roomsCount;

    @AssertTrue(message = "Check-out date must be on or after check-in date")
    @JsonIgnore
    public boolean isDateRangeValid() {
        return checkInDate == null || checkOutDate == null || !checkOutDate.isBefore(checkInDate);
    }
}
