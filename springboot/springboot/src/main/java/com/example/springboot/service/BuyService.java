package com.example.springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.springboot.model.Buy;
import com.example.springboot.repository.BuyRepo;

@Service
public class BuyService {

    @Autowired
    BuyRepo bookingRepository;

    public Buy create(Buy booking) {
        return bookingRepository.save(booking);
    }

    public List<Buy> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Buy> getBookingById(int id) {
        return bookingRepository.findById(id);
    }

    public boolean updateBooking(int id, Buy booking) {
        if (!bookingRepository.existsById(id)) {
            return false;
        }
        booking.setId(id);
        bookingRepository.save(booking);
        return true;
    }

    public boolean deleteBooking(int id) {
        if (!bookingRepository.existsById(id)) {
            return false;
        }
        bookingRepository.deleteById(id);
        return true;
    }

    public List<Buy> getAllBookingsSortedBy(String sortBy) {
        return bookingRepository.findAll(Sort.by(sortBy));
    }

    public Page<Buy> getAllBookingsPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return bookingRepository.findAll(pageable);
    }
}
