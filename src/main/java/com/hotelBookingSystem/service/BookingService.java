package com.hotelBookingSystem.service;

import com.hotelBookingSystem.dto.BookingDto;
import com.hotelBookingSystem.dto.BookingRequest;
import com.hotelBookingSystem.dto.GuestDto;

import java.util.List;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
