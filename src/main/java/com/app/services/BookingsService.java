package com.app.services;

import com.app.dtos.BookingDTO;
import com.app.models.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingsService {

    public int countBookings();

    public List<Booking> findAll() ;

    public Booking findById(Integer id) ;

    public List<Booking> findBookingsByHotelStaff() ;

    Page<Booking> findPaginatedBookings(Pageable pageable);

    void saveBooking(BookingDTO bookingDTO);

    void updateBooking(Integer id, BookingDTO bookingDTO);

    void deleteBooking(Integer id);

    BookingDTO convertToDTO(Booking booking);

    Booking convertToEntity(BookingDTO bookingDTO);
}


