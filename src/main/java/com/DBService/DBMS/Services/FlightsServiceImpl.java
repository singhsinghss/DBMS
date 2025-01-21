package com.DBService.DBMS.Services;

import com.DBService.DBMS.Models.Flights;
import com.DBService.DBMS.Models.Users;
import com.DBService.DBMS.Repositories.FlightsRepository;
import com.DBService.DBMS.Services.Interfaces.FlightsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FlightsServiceImpl implements FlightsService{

    @Autowired
    private final FlightsRepository flightRepository;

    @Override
    public List<Flights> getAllFlightDetails() {
        return flightRepository.findAll();
    }

    @Override
    public Flights getFlightDetailById(Long flightId) {
      return flightRepository.findById(flightId).orElse(null);
    }

    @Override
    public Flights addNewFlight(Flights flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flights updateFlightDetails(Long flightId, Flights flight) {

        Optional<Flights> existingFlightId = flightRepository.findById(flightId);
        System.out.println("*****id*****: "+existingFlightId);
        if (existingFlightId.isPresent()) {
            Flights existingFlight = existingFlightId.get();
            System.out.println("Before Update: " + existingFlight);
            if(flight.getFlight_number()!=null)
            {
                existingFlight.setFlight_number(flight.getFlight_number());
            }
            if(flight.getDeparture()!=null) {
                existingFlight.setDeparture(flight.getDeparture());
            }
            if(flight.getArrival()!=null) {
                existingFlight.setArrival(flight.getArrival());
            }
            if(flight.getDeparture_time()!=null)
            {
                existingFlight.setDeparture_time(flight.getDeparture_time());
            }
            if(flight.getArrival_time()!=null) {
                existingFlight.setArrival_time(flight.getArrival_time());
            }
            if(flight.getPrice()!=null)
            {
                existingFlight.setPrice(flight.getPrice());
            }
            if(flight.getAvailable_seats()!=null)
            {
                existingFlight.setAvailable_seats(flight.getAvailable_seats());
            }

            existingFlight.setUpdated_at(LocalDateTime.now());
            return flightRepository.save(existingFlight);
        } else {
            throw new IllegalArgumentException("Flight with ID " + flightId + " does not exist!");
        }
    }

    @Override
    public void deleteFlightById(Long flightId) {
        Optional<Flights> fltId = flightRepository.findById(flightId);
        if (fltId.isEmpty()) {
            throw new RuntimeException("flight details not found for flight id: " + fltId);
        }
        flightRepository.deleteById(flightId);

    }

    @Override
    public void updateAvailableSeats(Long flightId, int seatChange) {
        Optional<Flights> flightOpt = flightRepository.findById(flightId);
        if (flightOpt.isPresent()) {
            Flights flight = flightOpt.get();
            flight.setAvailable_seats(flight.getAvailable_seats() - seatChange);
            flightRepository.save(flight);
        }
    }
}
