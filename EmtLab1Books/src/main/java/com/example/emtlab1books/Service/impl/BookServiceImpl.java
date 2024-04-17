package com.example.emtlab1books.Service.impl;

import com.example.emtlab1books.Model.Book;
import com.example.emtlab1books.Model.BookCategory;
import com.example.emtlab1books.Model.DTOS.BookDTO;
import com.example.emtlab1books.Model.Events.BookCreated;
import com.example.emtlab1books.Model.Exeptions.InvalidBookIdExeption;
import com.example.emtlab1books.Repository.AuthorRepository;
import com.example.emtlab1books.Repository.BookRepository;
import com.example.emtlab1books.Service.AuthorService;
import com.example.emtlab1books.Service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

   private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final AuthorService authorService;
    private final ApplicationEventPublisher eventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, AuthorService authorService, ApplicationEventPublisher eventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorService = authorService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(InvalidBookIdExeption::new);
    }

    @Override
    public Optional<Book> addBook(BookDTO bookDto) {
      //  Long postedAuthor=bookDto.getAuthorId();
      //  BookCategory bookCategory=BookCategory.valueOf(bookDto.getBookCategory());
        Book book=new Book(bookDto.getName(),bookDto.getAvailableCopies(),bookDto.getBookCategory(),authorService.findAuthorById(bookDto.getAuthorId()));


        eventPublisher.publishEvent(new BookCreated(book));


        return Optional.of( bookRepository.save(book));
    }

    @Override
    public Optional<Book> deleteBook(Long id) {

     Book book=findBookById(id);
     bookRepository.delete(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> editBook(Long id, BookDTO bookDto) {

       Book book=findBookById(id);
       book.setName(bookDto.getName());
        //BookCategory bookCategory=BookCategory.valueOf(bookDto.getBookCategory());
       book.setBookCategory(bookDto.getBookCategory());
       book.setAvailableCopies(book.getAvailableCopies());
       book.setAuthor(authorService.findAuthorById(bookDto.getAuthorId()));

       bookRepository.save(book);
       return Optional.of(book);
    }

    @Override
    public Optional<Book> rent(Long id) {
        Book book = findBookById(id);

        // Check if the book exists
        if (book == null) {
            return Optional.empty();  // Book not found
        }

        // Only rent the book if it is not already rented
        if (!book.getIsRented()) {
            book.setIsRented(true);
            book.setAvailableCopies(book.getAvailableCopies()-1);
            bookRepository.save(book);
            return Optional.of(book);  // Successfully rented the book
        }

        // If the book is already rented, return empty or consider another response
        return Optional.empty();  // Book is already rented
    }


//    @Override
//    public void refreshMaterializedView() {
//
//    }
}
