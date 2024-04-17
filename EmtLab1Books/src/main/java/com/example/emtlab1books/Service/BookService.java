package com.example.emtlab1books.Service;

import com.example.emtlab1books.Model.Author;
import com.example.emtlab1books.Model.Book;
import com.example.emtlab1books.Model.DTOS.BookDTO;


import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAllBooks();
    Book findBookById(Long id);
    Optional<Book> addBook(BookDTO bookDto);
    Optional<Book> deleteBook(Long id);
    Optional<Book> editBook(Long id,BookDTO bookDto);

    Optional<Book> rent(Long id);

//    void refreshMaterializedView();
}
