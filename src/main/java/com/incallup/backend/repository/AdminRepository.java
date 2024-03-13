package com.incallup.backend.repository;

import com.incallup.backend.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Optional<Admin> findUserByUsername(String username);
}
