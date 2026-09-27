package com.hotelBookingSystem.repository;

import com.hotelBookingSystem.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
