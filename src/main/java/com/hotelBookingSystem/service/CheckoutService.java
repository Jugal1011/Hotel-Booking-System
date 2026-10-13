package com.hotelBookingSystem.service;

import com.hotelBookingSystem.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);

}
