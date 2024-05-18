package com.longkimhoang.booksapi.controller;

import com.longkimhoang.booksapi.dto.CreateBookDto;
import com.longkimhoang.booksapi.entity.Book;
import com.longkimhoang.booksapi.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
public class BooksControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService mockBookService;

    @Test
    public void getBooks() throws Exception {
        when(mockBookService.getBooks()).thenReturn(List.of(
                new Book(1, "Learn Java", "Description")));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": 1,
                                "name": "Learn Java",
                                "description": "Description"
                            }
                        ]
                        """));
    }

    @Test
    public void getBookById() throws Exception {
        when(mockBookService.getBookById(eq(1L))).thenReturn(
                Optional.of(new Book(1, "Learn Java", "Description")));

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                                "id": 1,
                                "name": "Learn Java",
                                "description": "Description"
                        }
                        """));
    }

    @Test
    public void getBookById_returnsNull() throws Exception {
        when(mockBookService.getBookById(eq(1L))).thenReturn(Optional.empty());

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("null"));
    }

    @Test
    public void createBook() throws Exception {
        when(mockBookService.createBook(
                argThat(argument -> argument.equals(new CreateBookDto(
                        "Learn Java", "Description")
                )))).thenReturn(new Book(1, "Learn Java", "Description"));

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "name": "Learn Java",
                            "description": "Description"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "name": "Learn Java",
                            "description": "Description"
                        }
                        """));
    }
}
