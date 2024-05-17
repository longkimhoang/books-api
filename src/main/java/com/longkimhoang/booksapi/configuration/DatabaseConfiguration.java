package com.longkimhoang.booksapi.configuration;

import com.longkimhoang.booksapi.entity.Book;
import com.longkimhoang.booksapi.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Autowired
    private BookRepository bookRepository;

    @Bean
    CommandLineRunner initializeDatabase() {
        return args -> {
            log.info("Create book {}",
                    bookRepository.save(new Book(1, "Learn Java", "Learn Java")));
            log.info("Create book {}",
                    bookRepository.save(new Book(2, "Learn Spring", "Learn Spring")));
        };
    }
}
