package com.DBService.DBMS.Controllers;

import com.DBService.DBMS.Models.Bookings;
import com.DBService.DBMS.Models.Flights;
import com.DBService.DBMS.Models.Users;
import com.DBService.DBMS.Repositories.FlightsRepository;
import com.DBService.DBMS.Repositories.UsersRepository;
import com.DBService.DBMS.Services.BookingServiceImpl;
import com.DBService.DBMS.Services.Interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bookings")
public class BookingsController {
    @Autowired
    private BookingServiceImpl bookingService;
    @Autowired
    private FlightsRepository flightRepository;
    @Autowired
    private UsersRepository userRepository;
    @PostMapping
    public ResponseEntity<Bookings> createBooking(@RequestBody Bookings booking) {

        Bookings createdBooking = bookingService.createNewBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookings> getBookingById(@PathVariable Long id) {
        /*Bookings booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);*/
        return new ResponseEntity<>(bookingService.getBookingById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bookings>> getBookingsByUserId(@PathVariable Long userId) {
        List<Bookings> bookings = bookingService.getBookingByUserId(userId);
        return ResponseEntity.ok(bookings);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Bookings> cancelBooking(@PathVariable Long id,@RequestBody Bookings booking) {
        try {
            //bookingService.cancelBookingById(id,booking);
            Bookings updatedBooking = bookingService.cancelBookingById(id, booking);
            return ResponseEntity.ok(updatedBooking);
           // return new ResponseEntity<>(bookingService.cancelBookingById(id,booking),HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
