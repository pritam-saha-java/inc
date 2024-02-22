package com.incallup.backend.repository;

import com.incallup.backend.domain.Admin;
import com.incallup.backend.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Optional<Customer> searchByTitle(String title);

    Optional<Customer> searchByCategory(String category);

    Optional<Customer> searchByCategoryAndLocation(String category, String location);

    Optional<Customer> searchByLocation(String location);
}
