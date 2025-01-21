package com.DBService.DBMS.Repositories;

import com.DBService.DBMS.Models.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightsRepository extends JpaRepository<Flights,Long> {
    Optional<Flights>findById(Long flightId);
}
