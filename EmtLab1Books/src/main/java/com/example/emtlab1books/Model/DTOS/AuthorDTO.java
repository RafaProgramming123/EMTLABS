package com.example.emtlab1books.Model.DTOS;

import com.example.emtlab1books.Model.Country;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AuthorDTO {
    private String name;

    private String surname;

    private Long countryId;
}
