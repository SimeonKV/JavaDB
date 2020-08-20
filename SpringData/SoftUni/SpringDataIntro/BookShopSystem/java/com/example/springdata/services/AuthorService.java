package com.example.springdata.services;

import com.example.springdata.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;
    int getAllAuthorsCount();
    Author findAuthorById(int id);
    List<Author> findAllAuthorsByCountOfBooks();
}
