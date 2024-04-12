package com.example.springboot.repository;

import com.example.springboot.model.Buy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BuyRepo extends JpaRepository<Buy, Integer> {

    @Query("SELECT b FROM Buy b WHERE b.customerName = :customerName ORDER BY b.bookingDate")
    List<Buy> findByCustomerNameSortedByBookingDate(@Param("customerName") String customerName);
}


