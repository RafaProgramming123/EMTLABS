package com.example.emtlab1books.Service;

import com.example.emtlab1books.Model.Author;
import com.example.emtlab1books.Model.DTOS.AuthorDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> listAllAuthors();
    Author findAuthorById(Long id);

    Optional<Author> addAuthor(AuthorDTO authorDto);
}
