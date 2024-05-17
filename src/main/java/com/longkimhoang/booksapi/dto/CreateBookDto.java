package com.longkimhoang.booksapi.dto;

import org.springframework.lang.NonNull;

public record CreateBookDto(@NonNull String name, String description) {
}
