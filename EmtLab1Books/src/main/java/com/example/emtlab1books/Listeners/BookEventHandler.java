package com.example.emtlab1books.Listeners;

//public class BookEventListener {
//}

import com.example.emtlab1books.Model.Events.BookCreated;
import com.example.emtlab1books.Service.BookService;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventHandler {

    public final BookService bookService;

    public BookEventHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @EventListener
    public void onBookCreated(BookCreated event) {
       System.out.print("Usepasna Operacija tiriru");
    }
}
