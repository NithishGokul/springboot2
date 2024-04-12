package com.example.springboot.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bookName;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<User> users;

    // Constructors, getters, and setters
    public Book() {
    }

    public Book(String bookName) {
        this.bookName = bookName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBusName(String bookName) {
        this.bookName = bookName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
