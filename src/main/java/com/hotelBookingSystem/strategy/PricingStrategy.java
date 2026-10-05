package com.hotelBookingSystem.strategy;

import com.hotelBookingSystem.entity.Inventory;

import java.math.BigDecimal;
public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}
