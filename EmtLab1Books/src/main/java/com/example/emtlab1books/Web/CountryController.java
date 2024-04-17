package com.example.emtlab1books.Web;


import com.example.emtlab1books.Model.Country;
import com.example.emtlab1books.Model.DTOS.CountryDTO;
import com.example.emtlab1books.Service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/app")
public class CountryController {

    public final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/add-country")
    public ResponseEntity<Country> addCountry(@RequestBody CountryDTO countryDTO)
    {
        Optional<Country> countryOptional = countryService.addCountry(countryDTO);

        return countryOptional
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
