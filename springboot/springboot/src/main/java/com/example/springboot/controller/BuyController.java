package com.example.springboot.controller;

import com.example.springboot.model.Buy;
import com.example.springboot.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BuyController {

    @Autowired
    private BuyService buyService;

    @PostMapping
    public ResponseEntity<Buy> addBooking(@RequestBody Buy booking) {
        Buy newBooking = buyService.create(booking);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Buy>> getAllBookings() {
        List<Buy> bookings = buyService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buy> getBookingById(@PathVariable("id") int id) {
        Optional<Buy> booking = buyService.getBookingById(id);
        return booking.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buy> updateBooking(@PathVariable("id") int id, @RequestBody Buy booking) {
        boolean updated = buyService.updateBooking(id, booking);
        return updated ? new ResponseEntity<>(booking, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") int id) {
        boolean deleted = buyService.deleteBooking(id);
        return deleted ? new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Buy>> getAllBookingsSorted(@RequestParam String sortBy) {
        List<Buy> bookings = buyService.getAllBookingsSortedBy(sortBy);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Buy>> getAllBookingsPaginated(@RequestParam int pageNo, @RequestParam int pageSize) {
        Page<Buy> bookingsPage = buyService.getAllBookingsPaginated(pageNo, pageSize);
        return new ResponseEntity<>(bookingsPage, HttpStatus.OK);
    }
}
