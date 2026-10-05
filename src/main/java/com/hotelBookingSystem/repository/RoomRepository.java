package com.hotelBookingSystem.repository;

import com.hotelBookingSystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
