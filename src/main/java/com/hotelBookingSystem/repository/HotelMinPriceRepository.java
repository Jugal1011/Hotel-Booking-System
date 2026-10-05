package com.hotelBookingSystem.repository;

import com.hotelBookingSystem.dto.HotelMinPriceDto;
import com.hotelBookingSystem.entity.Hotel;
import com.hotelBookingSystem.entity.HotelMinPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface HotelMinPriceRepository extends JpaRepository<HotelMinPrice, Long> {
    @Query("""
            SELECT new com.hotelBookingSystem.dto.HotelMinPriceDto(hmp.hotel, MIN(hmp.price))
            FROM HotelMinPrice hmp
            WHERE hmp.hotel.city = :city
            AND hmp.date BETWEEN :startDate AND :endDate
            AND hmp.hotel.active = true
            GROUP BY hmp.hotel
           """)
    Page<HotelMinPriceDto> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );

    Optional<HotelMinPrice> findByHotelAndDate(Hotel hotel, LocalDate date);
}
