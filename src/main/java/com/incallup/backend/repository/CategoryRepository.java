package com.incallup.backend.repository;

import com.incallup.backend.domain.Admin;
import com.incallup.backend.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
