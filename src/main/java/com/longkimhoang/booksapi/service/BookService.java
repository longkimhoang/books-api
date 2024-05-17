package com.longkimhoang.booksapi.service;

import com.longkimhoang.booksapi.dto.CreateBookDto;
import com.longkimhoang.booksapi.entity.Book;
import com.longkimhoang.booksapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(@NonNull CreateBookDto dto) {
        var book = Book.builder()
                .name(dto.name())
                .description(dto.description())
                .build();

        return bookRepository.save(book);
    }

    public Book replaceBook(long bookId, @NonNull CreateBookDto dto) {
        return bookRepository.findById(bookId)
                .map(book -> {
                    book.setName(dto.name());
                    book.setDescription(dto.description());
                    return bookRepository.save(book);
                })
                .orElseGet(() -> createBook(dto));
    }

    public void deleteBook(long bookId) {
        bookRepository.deleteById(bookId);
    }
}
