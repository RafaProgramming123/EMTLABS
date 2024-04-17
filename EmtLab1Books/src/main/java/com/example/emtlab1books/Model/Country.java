package com.example.emtlab1books.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Country")
@Data
public class Country {
    //id (Long), name (String), continent (String)
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String continent;


    public Country(){}
    public Country( String name, String continent) {
       // this.id = id;
        this.name = name;
        this.continent = continent;
    }
}
