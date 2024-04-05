package com.incallup.backend.repository;

import com.incallup.backend.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Integer> {
}
