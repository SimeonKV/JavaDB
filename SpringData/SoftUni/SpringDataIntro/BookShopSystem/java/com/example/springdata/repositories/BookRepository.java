package com.example.springdata.repositories;

import com.example.springdata.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByReleaseDateAfter(LocalDate localDate);
}
