package com.example.emtlab1books.Model.DTOS;


import com.example.emtlab1books.Model.Author;
import com.example.emtlab1books.Model.BookCategory;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BookDTO {


    private String name;

    private Integer availableCopies;

   private BookCategory bookCategory;

    private Long authorId;




}
