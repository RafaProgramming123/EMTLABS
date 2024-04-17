package com.example.emtlab1books.Service.impl;

import com.example.emtlab1books.Model.Country;
import com.example.emtlab1books.Model.DTOS.CountryDTO;
import com.example.emtlab1books.Model.Exeptions.InvalidCountryIdExeption;
import com.example.emtlab1books.Repository.CountryRepository;
import com.example.emtlab1books.Service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country findCountryById(Long id) {
        return countryRepository.findById(id).orElseThrow(InvalidCountryIdExeption::new);
    }

    @Override
    public Optional<Country> addCountry(CountryDTO countryDto) {

        Country country=new Country(countryDto.getName(),countryDto.getContinent());
        countryRepository.save(country);

        return Optional.of(country);
    }
}
