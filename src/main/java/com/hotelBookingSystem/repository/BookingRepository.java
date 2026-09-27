package com.hotelBookingSystem.repository;

import com.hotelBookingSystem.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
