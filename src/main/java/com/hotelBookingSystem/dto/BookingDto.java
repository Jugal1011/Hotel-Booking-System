package com.hotelBookingSystem.dto;

import com.hotelBookingSystem.entities.Hotel;
import com.hotelBookingSystem.entities.Room;
import com.hotelBookingSystem.entities.User;
import com.hotelBookingSystem.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
}
