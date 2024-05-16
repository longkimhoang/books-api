package com.longkimhoang.booksapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longkimhoang.booksapi.api.Book;

@RestController
public class BooksController {

    @GetMapping("/books")
    public List<Book> getBooks() {
        return List.of(
            new Book(1, "Java Programming"),
            new Book(2, "Spring Boot in Action")
        );
    }

}
