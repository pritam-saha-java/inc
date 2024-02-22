package com.incallup.backend.repository;

import com.incallup.backend.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

     Optional<Location> findLocationByDistrict(String district);

}
