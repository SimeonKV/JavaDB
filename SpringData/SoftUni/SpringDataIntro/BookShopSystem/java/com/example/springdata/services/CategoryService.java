package com.example.springdata.services;

import com.example.springdata.entities.Category;

import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws IOException;

    Category getCategoryById(long id);
}
