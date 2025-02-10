package com.DBService.DBMS.Controllers;

import com.DBService.DBMS.Models.Flights;
import com.DBService.DBMS.Models.Users;
import com.DBService.DBMS.Services.FlightsServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/flights")
@AllArgsConstructor
public class FlightController {

    @Autowired
    private final FlightsServiceImpl flightService;
    @GetMapping
    public ResponseEntity<List<Flights>> getAllFlights() {
        List<Flights> flights = flightService.getAllFlightDetails();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{flightId}")
    private ResponseEntity<Flights> getUserById(@PathVariable Long flightId)
    {
        return new ResponseEntity<>(flightService.getFlightDetailById(flightId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Flights> registerUser(@Valid @RequestBody Flights flight)
    {
        return new ResponseEntity<>(flightService.addNewFlight(flight), HttpStatus.CREATED);
    }
    @PutMapping("/{flightId}")
    public ResponseEntity<Flights> updateUserDetails(@PathVariable Long flightId,@RequestBody Flights flight)
    {
        try {
            //flightService.updateFlightDetails(flightId,flight);
            return new ResponseEntity<>(flightService.updateFlightDetails(flightId,flight), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{flightId}")
    public String deleteUser(@PathVariable Long flightId)
    {
        flightService.deleteFlightById(flightId);
        return "User with ID " + flightId + " deleted successfully.";

    }
}
