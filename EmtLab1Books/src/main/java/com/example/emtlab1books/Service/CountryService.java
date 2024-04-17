package com.example.emtlab1books.Service;

import com.example.emtlab1books.Model.Author;
import com.example.emtlab1books.Model.Country;
import com.example.emtlab1books.Model.DTOS.AuthorDTO;
import com.example.emtlab1books.Model.DTOS.CountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryService {


    List<Country> listAllCountries();
    Country findCountryById(Long id);
    Optional<Country> addCountry(CountryDTO countryDto);
}
