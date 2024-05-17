package com.longkimhoang.booksapi.repository;

import com.longkimhoang.booksapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
