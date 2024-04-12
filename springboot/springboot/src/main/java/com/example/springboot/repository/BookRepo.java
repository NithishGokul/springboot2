package com.example.springboot.repository;

import com.example.springboot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE b.bookName = ?1")
    List<Book> findByBusName(String busName);
    
    @Query("SELECT b FROM Book b WHERE b.id IN (SELECT DISTINCT b.id FROM Book b INNER JOIN b.users u WHERE u.id = ?1)")
    List<Book> findByUserId(int userId);
}
