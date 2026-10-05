package com.hotelBookingSystem.dto;

import com.hotelBookingSystem.entity.User;
import com.hotelBookingSystem.enums.Gender;
import lombok.Data;

@Data
public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
