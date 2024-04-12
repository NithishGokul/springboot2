package com.example.springboot.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Buy> bookings;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnoreProperties({"users"})
    private Book book;

    // Constructors, getters, and setters
    public User() {
    }

    public User(String firstName, String lastName, String email, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Buy> getBookings() {
        return bookings;
    }

    public void setBookings(List<Buy> bookings) {
        this.bookings = bookings;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
