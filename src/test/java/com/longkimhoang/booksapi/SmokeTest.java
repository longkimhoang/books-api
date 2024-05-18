package com.longkimhoang.booksapi;

import com.longkimhoang.booksapi.controller.BooksController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SmokeTest {

	@Autowired
	private BooksController booksController;

	@Test
	void contextLoads() {
		assertNotNull(booksController);
	}

}
