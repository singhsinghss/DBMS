package com.DBService.DBMS.Services.Interfaces;

import com.DBService.DBMS.Models.Bookings;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    Bookings createNewBooking(Bookings booking);
    Bookings getBookingById(Long id);
    List<Bookings> getBookingByUserId(Long userId);
    Bookings cancelBookingById(Long id,Bookings booking);

}
