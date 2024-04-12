package com.example.springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.springboot.model.Book;
import com.example.springboot.repository.BookRepo;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepository;

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {   
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    public boolean updateBooking(int id, Book book) {
        if (!bookRepository.existsById(id)) {
            return false;
        }
        book.setId(id);
        bookRepository.save(book);
        return true;
    }

    public boolean deleteBook(int id) {
        if (!bookRepository.existsById(id)) {
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }

    public List<Book> getAllBooksSortedBy(String sortBy) {
        return bookRepository.findAll(Sort.by(sortBy));
    }

    public Page<Book> getAllBooksPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return bookRepository.findAll(pageable);
    }
    public List<Book> findByBookName(String busName) {
        return bookRepository.findByBusName(busName);
    }

    public List<Book> findByUserId(int userId) {
        return bookRepository.findByUserId(userId);
    }

}
