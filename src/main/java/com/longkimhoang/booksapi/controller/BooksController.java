package com.longkimhoang.booksapi.controller;

import com.longkimhoang.booksapi.dto.CreateBookDto;
import com.longkimhoang.booksapi.entity.Book;
import com.longkimhoang.booksapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BooksController {

    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/books/{bookId}")
    public Optional<Book> getBookById(@PathVariable final long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping("/books")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Book createBook(@RequestBody final CreateBookDto dto) {
        return bookService.createBook(dto);
    }

    @PutMapping("/books/{bookId}")
    public Book replaceBook(
            @PathVariable final long bookId,
            @RequestBody final CreateBookDto dto) {
        return bookService.replaceBook(bookId, dto);
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable final long bookId) {
        bookService.deleteBook(bookId);
    }

}
