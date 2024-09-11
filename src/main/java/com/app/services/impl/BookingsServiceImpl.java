package com.app.services.impl;

import com.app.constants.BookingStatus;
import com.app.dtos.BookingDTO;
import com.app.models.Booking;
import com.app.models.User;
import com.app.repositories.BookingRepository;
import com.app.repositories.UserRepository;
import com.app.services.BookingsService;
import com.app.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingsServiceImpl implements BookingsService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersService usersService;

    @Override
    public int countBookings() {
        return (int) bookingRepository.count();
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(Integer id) {
        return bookingRepository.findById(id.intValue()).orElse(null);
    }

    @Override
    public List<Booking> findBookingsByHotelStaff() {
        // Get the current logged-in user
        User currentUser = usersService.getCurrentUser();
        // Assuming each staff user is associated with one or more hotels
        return bookingRepository.findByBookingRooms_Room_Hotel_Id(currentUser.getId());
    }

    @Override
    public void saveBooking(BookingDTO bookingDTO) {
        Booking booking = convertToEntity(bookingDTO);
        bookingRepository.save(booking);
    }

    @Override
    public void updateBooking(Integer id, BookingDTO bookingDTO) {
        Booking existingBooking = findById(id);

        // Lấy đối tượng User từ customerId
        User customer = userRepository.findById(Long.valueOf(bookingDTO.getCustomerId()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking updatedBooking = Booking.builder()
                .id(existingBooking.getId())
                .customer(customer)
                .name(bookingDTO.getName())
                .email(bookingDTO.getEmail())
                .phone(bookingDTO.getPhone())
                .address(bookingDTO.getAddress())
                .checkinDate(bookingDTO.getCheckinDate())
                .checkoutDate(bookingDTO.getCheckoutDate())
                .status(BookingStatus.valueOf(bookingDTO.getStatus()))
                .totalAmount(bookingDTO.getTotalAmount())
                .createdAt(existingBooking.getCreatedAt())
                .build();

        bookingRepository.save(updatedBooking);
    }

    @Override
    public void deleteBooking(Integer id) {
        Booking booking = findById(id);
        bookingRepository.delete(booking);
    }

    @Override
    public BookingDTO convertToDTO(Booking booking) {
        return BookingDTO.builder()
                .id(booking.getId())
                .customerId(booking.getCustomer().getId())
                .name(booking.getName())
                .email(booking.getEmail())
                .phone(booking.getPhone())
                .address(booking.getAddress())
                .checkinDate(booking.getCheckinDate())
                .checkoutDate(booking.getCheckoutDate())
                .status(String.valueOf(booking.getStatus()))
                .totalAmount(booking.getTotalAmount())
                .build();
    }

    @Override
    public Booking convertToEntity(BookingDTO bookingDTO) {
        User customer = userRepository.findById(Long.valueOf(bookingDTO.getCustomerId()))
                .orElseThrow(() -> new RuntimeException("User not found"));
        return Booking.builder()
                .customer(customer)
                .name(bookingDTO.getName())
                .email(bookingDTO.getEmail())
                .phone(bookingDTO.getPhone())
                .address(bookingDTO.getAddress())
                .checkinDate(bookingDTO.getCheckinDate())
                .checkoutDate(bookingDTO.getCheckoutDate())
                .status(BookingStatus.valueOf(bookingDTO.getStatus()))
                .totalAmount(bookingDTO.getTotalAmount())
                .build();
    }

    @Override
    public Page<Booking> findPaginatedBookings(Pageable pageable){return bookingRepository.findAll(pageable);}
}

