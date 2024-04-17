package com.example.emtlab1books.Web;

import com.example.emtlab1books.Model.Author;
import com.example.emtlab1books.Model.Book;
import com.example.emtlab1books.Model.Country;
import com.example.emtlab1books.Model.DTOS.AuthorDTO;
import com.example.emtlab1books.Model.DTOS.CountryDTO;
import com.example.emtlab1books.Service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class AuthorController {
    public final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("getAuthors")
    public ResponseEntity<List<Author>> getAuthors()
    {
       List<Author> authors=authorService.listAllAuthors();
       System.out.print(authors);
        if (authors.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
        }
        return ResponseEntity.ok( authors);
    }


    @PostMapping("/add-author")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDTO authorDTO)
    {
        List<Author> authors=authorService.listAllAuthors();
        System.out.print(authors);
        Optional<Author> countryOptional = authorService.addAuthor(authorDTO);
        return countryOptional
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
