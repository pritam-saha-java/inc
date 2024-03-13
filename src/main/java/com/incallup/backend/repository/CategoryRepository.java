package com.incallup.backend.repository;


import com.incallup.backend.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findCategoryByTitle(String title);
    Optional<Category> findCategoryByName(String name);
}
