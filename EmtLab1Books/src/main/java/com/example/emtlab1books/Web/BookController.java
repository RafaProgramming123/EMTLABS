package com.example.emtlab1books.Web;

import com.example.emtlab1books.Model.Author;
import com.example.emtlab1books.Model.Book;
import com.example.emtlab1books.Model.BookCategory;
import com.example.emtlab1books.Model.DTOS.BookDTO;
import com.example.emtlab1books.Service.BookService;
import jakarta.persistence.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class BookController {

    public final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @GetMapping("getBooks")
    public ResponseEntity<List<Book>> getBooks()
    {
         List<Book> books=bookService.listAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if the list is empty
        }
        return ResponseEntity.ok( books);
    }



    @GetMapping("/getBook/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id)
    {
        Book book=bookService.findBookById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<BookCategory>> getCategories() {
        List<BookCategory> categories = Arrays.asList(BookCategory.values());
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/add-book")
    public ResponseEntity<Book> addAuthor(@RequestBody BookDTO bookDTO)
    {
        Optional<Book> countryOptional = bookService.addBook(bookDTO);
        System.out.print("Usepasna Operacija tiriru");
        return countryOptional
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit-book/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {

        Optional<Book> bookOptional = bookService.editBook(id, bookDTO);
        if (bookOptional.isPresent()) {
            return ResponseEntity.ok(bookOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id) {
        Optional<Book> bookOptional=bookService.deleteBook(id);
        if (bookOptional.isPresent()) {

            return ResponseEntity.ok(bookOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/rent-book/{id}")
    public ResponseEntity<?> markRented(@PathVariable Long id){
        return bookService.rent(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> {
                    Book bookCheck = bookService.findBookById(id);
                    if (bookCheck == null) {
                        return ResponseEntity.notFound().build();
                    } else if (bookCheck.getIsRented()) {
                        return ResponseEntity.status(500).build();
                    } else {
                        return ResponseEntity.badRequest().build();
                    }
                });
    }



}
