package com.incallup.backend.repository;

import com.incallup.backend.domain.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Integer> {
}
