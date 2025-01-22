package com.DBService.DBMS.Services;

import com.DBService.DBMS.Models.Bookings;
import com.DBService.DBMS.Models.Flights;
import com.DBService.DBMS.Models.Users;
import com.DBService.DBMS.Repositories.BookingRepository;
import com.DBService.DBMS.Repositories.FlightsRepository;
import com.DBService.DBMS.Repositories.UsersRepository;
import com.DBService.DBMS.Services.Interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private  FlightsRepository flightRepository;
    @Autowired
    private UsersRepository userRepository;

    @Override
    public Bookings createNewBooking(Bookings booking) {

        Users user = userRepository.findById(booking.getUser().getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
       Long userId=booking.getUser().getUserId();
        System.out.println("Print user id: "+userId);
        System.out.println("User id: "+userRepository.findById(booking.getUser().getUserId()));

        Flights flight = flightRepository.findById(booking.getFlight().getFlightId())
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));
        System.out.println("Flight id: "+flight.getFlightId());
        // Create a new Booking entity and map the DTO fields to the entity fields
        Bookings bookings = new Bookings();
        bookings.setUser(user);
        bookings.setFlight(flight);
        bookings.setStatus(booking.getStatus());

        // Call the service to save the new booking

        return bookingRepository.save(bookings);

    }

    @Override
    public Bookings getBookingById(Long id) {

        return bookingRepository.findById(id)
                .orElse(null);

    }

    @Override
    public List<Bookings> getBookingByUserId(Long userId) {
        return bookingRepository.findByUser_UserId(userId);
    }

    @Override
    public Bookings cancelBookingById(Long id, Bookings booking) {
        Optional<Bookings> existingBookingId = bookingRepository.findById(id);
        //Bookings existingBooking = getBookingById(id);
       /* if (existingBookingId.isPresent()) {*/
            Bookings existingBooking = existingBookingId.get();
            System.out.println("Status: "+booking.getStatus());
            if (booking != null && (booking.getStatus().equalsIgnoreCase("Booked")|| booking.getStatus().equalsIgnoreCase("Cancelled"))) {
                // Change booking status to Cancelled
                //System.out.println("Status: "+booking.getStatus());
                existingBooking.setStatus(booking.getStatus());
            }
            else
            {
                System.out.println("Invalid status: "+booking.getStatus());
                return null;
            }
           return bookingRepository.save(existingBooking);
    }


}
