package com.longkimhoang.booksapi.service;

import com.longkimhoang.booksapi.dto.CreateBookDto;
import com.longkimhoang.booksapi.entity.Book;
import com.longkimhoang.booksapi.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BookServiceTests {

    @Mock
    private BookRepository mockBookRepository;

    @InjectMocks
    private BookService service;

    @Test
    public void getBooks() {
        // Given
        var books = List.of(
                new Book(1, "Learn Java", "Description"));
        when(mockBookRepository.findAll()).thenReturn(books);

        // When
        var result = service.getBooks();

        // Then
        assertEquals(result, books);
    }

    @Test
    public void getBookById_returnsBook() {
        // Given
        var book = new Book(1, "Learn Java", "Description");
        when(mockBookRepository.findById(eq(1L))).thenReturn(Optional.of(book));

        // When
        var result = service.getBookById(1);

        // Then
        assertEquals(result.orElseThrow(), book);
    }

    @Test
    public void getBookById_returnsNull() {
        // Given
        when(mockBookRepository.findById(any())).thenReturn(Optional.empty());

        // When
        var result = service.getBookById(1);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void createBook() {
        // Given
        var book = new Book(1, "Learn Java", "Description");
        when(mockBookRepository
                .save(argThat(arg -> arg.getName().equals("Learn Java") &&
                        arg.getDescription().equals("Description"))))
                .thenReturn(book);

        // When
        var result = service.createBook(new CreateBookDto("Learn Java", "Description"));

        // Then
        assertEquals(result, book);
    }
}
