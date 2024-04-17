package com.example.emtlab1books.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Author")
@Data
public class Author {


    @Id
    @GeneratedValue
    private Long Id;

    private String name;

    private String surname;


    @ManyToOne
    private Country country;


    public Author(){}
    public Author( String name, String surname, Country country) {
      // Id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
