package com.example.springdata.services.impl;

import com.example.springdata.constants.GlobalConstants;
import com.example.springdata.entities.Author;
import com.example.springdata.repositories.AuthorRepository;
import com.example.springdata.services.AuthorService;
import com.example.springdata.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if(this.authorRepository.count() != 0){
            return;
        }

        String[] authors = fileUtil.readFileContent(GlobalConstants.AUTHORS_FILE_PATH);

        Arrays.stream(authors)
                .forEach(row -> {
                    String[] args = row.split(" ");
                    String firstName = args[0];
                    String lastName = args[1];
                    Author author = new Author(firstName,lastName);
                    this.authorRepository.saveAndFlush(author);
                });

    }

    @Override
    public int getAllAuthorsCount() {
        return (int)this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(int id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAllAuthorsByCountOfBooks() {
        return this.authorRepository.findAuthorOrderedByBooksSize();
    }
}
