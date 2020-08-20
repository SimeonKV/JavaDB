package com.example.springdata.services.impl;

import com.example.springdata.constants.GlobalConstants;
import com.example.springdata.entities.*;
import com.example.springdata.repositories.BookRepository;
import com.example.springdata.services.AuthorService;
import com.example.springdata.services.BookService;
import com.example.springdata.services.CategoryService;
import com.example.springdata.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {
  private BookRepository bookRepository;
  private AuthorService authorService;
  private CategoryService categoryService;
  private FileUtil fileUtil;

  @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService, FileUtil fileUtil) {
      this.bookRepository = bookRepository;
      this.authorService = authorService;
      this.categoryService = categoryService;
      this.fileUtil = fileUtil;
  }

    @Override
    public void seedBooks() throws IOException {
      if(this.bookRepository.count() != 0){
          return;
      }

      String[] books =
              fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);

      Arrays.stream(books)
              .forEach(row -> {
                  String[] params = row.split("\\s+");

                  Author author = this.getRandomAuthor();
                  EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];

                  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                  LocalDate localDate = LocalDate.parse(params[1],dateTimeFormatter);

                  int copies = Integer.parseInt(params[2]);
                  BigDecimal price = new BigDecimal(params[3]);
                  AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];
                  String title = getTitle(params);

                  Set<Category> categories = this.getRandomCategories();

                  Book book = new Book();
                  book.setAuthor(author);
                  book.setEditionType(editionType);
                  book.setReleaseDate(localDate);
                  book.setCopies(copies);
                  book.setPrice(price);
                  book.setAgeRestriction(ageRestriction);
                  book.setTitle(title);
                  book.setCategories(categories);

                  this.bookRepository.saveAndFlush(book);


              });


    }

    @Override
    public List<Book> getAllBooksAfter2000() {
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
      LocalDate releaseDate = LocalDate.parse("31/12/2000",dateTimeFormatter);


      return this.bookRepository.findAllByReleaseDateAfter(releaseDate);
    }

    private Set<Category> getRandomCategories() {
      Set<Category> categories = new HashSet<>();
      Random random = new Random();
      int bound = random.nextInt(3) + 1;

        for (int i = 1; i < bound + 1; i++) {
            int categoryId = random.nextInt(8) + 1;
            categories.add(this.categoryService.getCategoryById(categoryId));
        }

        return categories;
    }

    private String getTitle(String[] params) {
      StringBuilder builder = new StringBuilder();

        for (int i = 5; i < params.length; i++) {
            builder.append(params[i]).append(" ");
        }

        return builder.toString().trim();
    }

    private Author getRandomAuthor() {
       Random random = new Random();
       int randomId = random.nextInt(this.authorService.getAllAuthorsCount()) + 1;

       return this.authorService.findAuthorById(randomId);
    }
}
