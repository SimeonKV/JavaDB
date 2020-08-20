package com.example.springdata.repositories;

import com.example.springdata.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Author findById(long id);

    @Query("FROM Author ORDER BY  books.size DESC ")
    List<Author> findAuthorOrderedByBooksSize();
}
