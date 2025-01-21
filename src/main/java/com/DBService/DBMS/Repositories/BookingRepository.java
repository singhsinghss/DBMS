package com.DBService.DBMS.Repositories;

import com.DBService.DBMS.Models.Bookings;
import com.DBService.DBMS.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Bookings,Long> {

    List<Bookings> findByUser_UserId(Long userId);
    List<Bookings> findByFlight_FlightId(Long flightId);

}
