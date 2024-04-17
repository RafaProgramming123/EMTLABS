package com.example.emtlab1books.Model;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="Book")
@Data
public class Book {

    @Id @GeneratedValue
private Long Id;

private String name;

private Integer availableCopies;

@Enumerated
private BookCategory bookCategory;

@ManyToOne
private Author author;

    private Boolean isRented;


    public Book(String name, Integer availableCopies, BookCategory bookCategory, Author author) {
       // Id = id;
        this.name = name;
        this.availableCopies = availableCopies;
        this.bookCategory = bookCategory;
        this.author = author;
        this.isRented=false;
    }

    public Book() {

    }
}
