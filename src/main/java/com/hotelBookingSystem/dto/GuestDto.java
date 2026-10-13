package com.hotelBookingSystem.dto;

import com.hotelBookingSystem.entity.User;
import com.hotelBookingSystem.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GuestDto {
    private Long id;
    private String name;
    private Gender gender;
    private LocalDate dateOfBirth;
}
