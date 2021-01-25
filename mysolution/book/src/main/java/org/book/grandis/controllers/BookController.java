package org.book.grandis.controllers;

import org.book.grandis.model.Book;
import org.book.grandis.repos.BookRepository;
import org.book.grandis.services.TraceService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(value = "/v2/book")
public class BookController {

    @Autowired
    TraceService traceService;

    @Autowired
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // CREATE
    @RequestMapping(method = RequestMethod.PUT)
    public void addNewBook(@RequestBody Book book) {
        System.out.println(book);
        this.bookRepository.save(book);
    }


    // READ
    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    public Book getBook(@PathVariable String bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        return bookOptional.get();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<Book> getAllBook() {
        Logger log= null;
        log.info("Get all Books");
        return bookRepository.findAll();
    }


    // UPDATE
    @RequestMapping(value = "/{bookId}", method = RequestMethod.POST)
    public Book modifyBook(@RequestBody Book book, @RequestBody String bookId ) {
        return bookRepository.save(book);
    }


    // DELETE
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllBook() {
        bookRepository.deleteAll();
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable String bookId) {
        bookRepository.deleteById(bookId);
    }
}
