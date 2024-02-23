package com.incallup.backend.service.impl;

import com.incallup.backend.domain.Category;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    private Category getCategoryByName(String cateogoryName) throws ApplicationException{
         var cat = categoryRepository.findCategoryByName(cateogoryName);

            if(cat.isEmpty())
                throw ApplicationException.builder()
                        .title("category not found")
                        .status(404)
                        .Description("please choose proper category")
                        .build();

         return  cat.get();
    }
}
