package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.model.Buy;
import com.example.springboot.service.BuyService;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookController {

    @Autowired
    private BuyService bookingService;

    @PostMapping
    public ResponseEntity<Buy> addBooking(@RequestBody Buy booking) {
        Buy newBooking = bookingService.create(booking);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Buy>> getAllBookings() {
        List<Buy> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buy> getBookingById(@PathVariable("id") int id) {
        Buy booking = bookingService.getBookingById(id).orElse(null);
        if (booking != null) {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buy> updateBooking(@PathVariable("id") int id, @RequestBody Buy booking) {
        if (bookingService.updateBooking(id, booking)) {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBooking(@PathVariable("id") int id) {
        if (bookingService.deleteBooking(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Buy>> getAllBookingsSorted(@RequestParam String sortBy) {
        List<Buy> bookings = bookingService.getAllBookingsSortedBy(sortBy);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Buy>> getAllBookingsPaginated(@RequestParam int pageNo, @RequestParam int pageSize) {
        Page<Buy> bookingsPage = bookingService.getAllBookingsPaginated(pageNo, pageSize);
        return new ResponseEntity<>(bookingsPage, HttpStatus.OK);
    }
}
