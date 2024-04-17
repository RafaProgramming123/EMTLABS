package com.example.emtlab1books.Model.Events;


import org.springframework.context.ApplicationEvent;


import java.time.Clock;




public class BookCreated extends ApplicationEvent {


    public BookCreated(Object source) {
        super(source);
    }

    public BookCreated(Object source, Clock clock) {
        super(source, clock);
    }
}
