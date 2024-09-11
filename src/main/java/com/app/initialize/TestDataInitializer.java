package com.app.initialize;

import com.app.models.Booking;
import com.app.models.User;
import com.app.repositories.BookingRepository;
import com.app.repositories.UserRepository;
import com.app.constants.BookingStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.app.constants.Role;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        try {
            log.warn("Checking if test data persistence is required...");

            if (userRepository.count() == 0) {
                log.info("Initiating test data persistence");

                User user1 = User.builder().email("admin@hotel.com").password(passwordEncoder.encode("123456")).name("Admin").role(Role.valueOf("ADMIN")).build();
                User user2 = User.builder().email("customer1@hotel.com").password(passwordEncoder.encode("123456")).name("Kaya Alp").role(Role.valueOf("CUSTOMER")).build();
                User user3 = User.builder().email("manager1@hotel.com").password(passwordEncoder.encode("123456")).name("John").role(Role.valueOf("HOTEL_STAFF")).build();
                User user4 = User.builder().email("manager2@hotel.com").password(passwordEncoder.encode("123456")).name("Max").role(Role.valueOf("HOTEL_STAFF")).build();

                log.debug("Saving users...");
                userRepository.save(user1);
                userRepository.save(user2);
                userRepository.save(user3);
                userRepository.save(user4);

                log.info("Users saved. Creating sample bookings...");
                createSampleBookings(user2); // Assuming user2 is a customer

                log.info("Sample bookings created successfully!");

            } else {
                log.info("Test data persistence is not required");
            }
            log.warn("App ready");
        } catch (DataAccessException e) {
            log.error("Exception occurred during data persistence: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Unexpected exception occurred: " + e.getMessage(), e);
        }
    }

    private void createSampleBookings(User customer) {
        List<String> statuses = Arrays.asList("PENDING", "CONFIRMED", "CANCELLED");
        Random random = new Random();

        log.debug("Creating bookings for customer: {}", customer.getEmail());
        for (int i = 1; i <= 10; i++) {
            LocalDate checkinDate = LocalDate.now().plusDays(random.nextInt(30));
            LocalDate checkoutDate = checkinDate.plusDays(random.nextInt(5) + 1); // Random stay between 1 and 5 days

            Booking booking = Booking.builder()
                    .customer(customer) // Assigning the customer from the sample user
                    .name("Booking " + i)
                    .email("customer" + i + "@hotel.com")
                    .phone("+12345678" + i)
                    .address("Address " + i)
                    .checkinDate(checkinDate)
                    .checkoutDate(checkoutDate)
                    .status(BookingStatus.valueOf(statuses.get(random.nextInt(statuses.size()))))
                    .totalAmount(100.0 + random.nextInt(500)) // Random amount between 100 and 600
                    .build();

            log.debug("Saving booking: {}", booking);
            bookingRepository.save(booking);
        }
        log.debug("All bookings saved successfully.");
    }
}
