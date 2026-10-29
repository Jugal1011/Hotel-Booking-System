package com.hotelBookingSystem.dto;

import com.hotelBookingSystem.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GuestDto {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    private Gender gender;

    @Past
    private LocalDate dateOfBirth;
}
