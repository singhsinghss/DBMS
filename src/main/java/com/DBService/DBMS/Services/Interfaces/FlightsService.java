package com.DBService.DBMS.Services.Interfaces;

import com.DBService.DBMS.Models.Flights;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface FlightsService {

    List<Flights> getAllFlightDetails();
    Flights getFlightDetailById(Long flightId);
    Flights addNewFlight(Flights flight);
    Flights updateFlightDetails(Long flightId,Flights flight);
    void deleteFlightById(Long flightId);
    void updateAvailableSeats(Long flightId, int seatChange);

}
