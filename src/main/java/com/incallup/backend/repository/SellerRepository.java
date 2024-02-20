package com.incallup.backend.repository;

import com.incallup.backend.domain.Admin;
import com.incallup.backend.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
}
