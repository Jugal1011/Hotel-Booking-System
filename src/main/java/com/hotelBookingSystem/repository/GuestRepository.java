package com.hotelBookingSystem.repository;

import com.hotelBookingSystem.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}