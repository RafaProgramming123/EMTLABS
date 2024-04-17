package com.example.emtlab1books.Service.impl;

import com.example.emtlab1books.Model.Author;
import com.example.emtlab1books.Model.DTOS.AuthorDTO;
import com.example.emtlab1books.Model.Exeptions.InvalidAuthorIdExeption;
import com.example.emtlab1books.Model.Exeptions.InvalidCountryIdExeption;
import com.example.emtlab1books.Repository.AuthorRepository;
import com.example.emtlab1books.Repository.CountryRepository;
import com.example.emtlab1books.Service.AuthorService;
import com.example.emtlab1books.Service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(InvalidAuthorIdExeption::new);
    }

    @Override
    public Optional<Author> addAuthor(AuthorDTO authorDto) {
        Long postedCountry=authorDto.getCountryId();
        Author author=new Author(authorDto.getName(),authorDto.getSurname(),countryService.findCountryById(authorDto.getCountryId()));
        authorRepository.save(author);
        return Optional.of(author);
    }
}
