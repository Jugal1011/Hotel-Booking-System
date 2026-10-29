package com.hotelBookingSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotelBookingSystem.enums.Gender;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileUpdateRequestDto {
    @NotBlank
    @Size(max = 100)
    private String name;

    @Past
    private LocalDate dateOfBirth;

    private Gender gender;

    @AssertTrue(message = "At least one profile field must be provided")
    @JsonIgnore
    public boolean isAtLeastOneFieldPresent() {
        return name != null || dateOfBirth != null || gender != null;
    }
}
