package com.hotelBookingSystem.service;

import com.hotelBookingSystem.dto.ProfileUpdateRequestDto;
import com.hotelBookingSystem.dto.UserDto;
import com.hotelBookingSystem.entity.User;

public interface UserService {

    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
