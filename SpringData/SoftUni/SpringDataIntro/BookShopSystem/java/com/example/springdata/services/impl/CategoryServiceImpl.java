package com.example.springdata.services.impl;

import com.example.springdata.constants.GlobalConstants;
import com.example.springdata.entities.Category;
import com.example.springdata.repositories.CategoryRepository;
import com.example.springdata.services.CategoryService;
import com.example.springdata.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
   private CategoryRepository categoryRepository;
   private FileUtil fileUtil;

   @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
       if(this.categoryRepository.count() != 0){
           return;
       }

       String[] fileContent =
               this.fileUtil.readFileContent(GlobalConstants.CATEGORIES_FILE_PATH);

       Arrays.stream(fileContent)
               .forEach( row -> {
                   Category category = new Category(row);
                   this.categoryRepository.saveAndFlush(category);
               });
    }

    @Override
    public Category getCategoryById(long id) {
        return this.categoryRepository.getOne(id);
    }
}
