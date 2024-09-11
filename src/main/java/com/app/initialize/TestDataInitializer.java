package com.app.initialize;

import com.app.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.app.constants.Role;
import com.app.models.*;
import com.app.constants.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataInitializer implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final BookingRepository bookingRepository;
    private final BookingRoomRepository bookingRoomRepository;
    private final HotelRepository hotelRepository;
    private final PaymentRepository paymentRepository;
    private final ReviewRepository reviewRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) {

        try {
            log.warn("Checking if test data persistence is required...");

            if (userRepository.count() == 0) {
                log.info("Initiating test data persistence");

                User user1 = User.builder().email("admin@hotel.com").password(passwordEncoder.encode("123456")).name("Admin").role(Role.valueOf("ADMIN")).build();
                User user2 = User.builder().email("customer1@hotel.com").password(passwordEncoder.encode("123456")).name("Kaya Alp").role(Role.valueOf("CUSTOMER")).build();
                User user3 = User.builder().email("customer2@hotel.com").password(passwordEncoder.encode("123456")).name("Sam Smith").role(Role.valueOf("CUSTOMER")).build();
                User user4 = User.builder().email("customer3@hotel.com").password(passwordEncoder.encode("123456")).name("Adele").role(Role.valueOf("CUSTOMER")).build();
                User user5 = User.builder().email("manager1@hotel.com").password(passwordEncoder.encode("123456")).name("John").role(Role.valueOf("HOTEL_STAFF")).build();
                User user6 = User.builder().email("manager2@hotel.com").password(passwordEncoder.encode("123456")).name("Max").role(Role.valueOf("HOTEL_STAFF")).build();
                User user7 = User.builder().email("manager3@hotel.com").password(passwordEncoder.encode("123456")).name("Taylor").role(Role.valueOf("HOTEL_STAFF")).build();

                userRepository.save(user1);
                userRepository.save(user2);
                userRepository.save(user3);
                userRepository.save(user4);
                userRepository.save(user5);
                userRepository.save(user6);
                userRepository.save(user7);

                Hotel hotel1 = Hotel.builder()
                        .name("Sunrise Hotel")
                        .location("123 Beach Ave, Miami, FL")
                        .description("A beautiful beachfront hotel with excellent amenities.")
                        .contactInfo("info@sunrisehotel.com")
                        .imageUrl("/img/hotels/hotel1.jpg")
                        .build();

                Hotel hotel2 = Hotel.builder()
                        .name("Mountain View Resort")
                        .location("456 Alpine St, Denver, CO")
                        .description("Experience nature at its best with our mountain-side rooms.")
                        .contactInfo("info@mountainview.com")
                        .imageUrl("/img/hotels/hotel2.jpeg")
                        .build();

                Hotel hotel3 = Hotel.builder()
                        .name("City Lights Hotel")
                        .location("789 Broadway Ave, New York, NY")
                        .description("Located in the heart of the city, offering the best urban experience.")
                        .contactInfo("info@citylightshotel.com")
                        .imageUrl("/img/hotels/hotel3.jpg")
                        .build();

                Hotel hotel4 = Hotel.builder()
                        .name("Desert Oasis Hotel")
                        .location("101 Desert Rd, Phoenix, AZ")
                        .description("An oasis of luxury in the midst of the desert.")
                        .contactInfo("info@desertoasis.com")
                        .imageUrl("/img/hotels/hotel4.jpg")
                        .build();

                Hotel hotel5 = Hotel.builder()
                        .name("Coastal Breeze Inn")
                        .location("234 Seaside Blvd, San Diego, CA")
                        .description("Relax by the ocean with our cozy and comfortable rooms.")
                        .contactInfo("info@coastalbreezeinn.com")
                        .imageUrl("/img/hotels/hotel5.jpeg")
                        .build();

                Hotel hotel6 = Hotel.builder()
                        .name("Green Valley Lodge")
                        .location("567 Nature Park, Asheville, NC")
                        .description("Enjoy a peaceful retreat surrounded by lush greenery.")
                        .contactInfo("info@greenvalleylodge.com")
                        .imageUrl("/img/hotels/hotel6.jpg")
                        .build();

                Hotel hotel7 = Hotel.builder()
                        .name("Skyline Suites")
                        .location("678 Skyline Dr, Seattle, WA")
                        .description("Experience luxury with breathtaking city views.")
                        .contactInfo("info@skylinesuites.com")
                        .imageUrl("/img/hotels/hotel7.jpg")
                        .build();

                Hotel hotel8 = Hotel.builder()
                        .name("Lakeview Retreat")
                        .location("890 Lake Rd, Portland, OR")
                        .description("Charming retreat with scenic lake views and tranquility.")
                        .contactInfo("info@lakeviewretreat.com")
                        .imageUrl("/img/hotels/hotel1.jpg")
                        .build();

                Hotel hotel9 = Hotel.builder()
                        .name("Historic Grand Hotel")
                        .location("101 Historic St, Charleston, SC")
                        .description("Step back in time with our elegant historic accommodations.")
                        .contactInfo("info@historicgrandhotel.com")
                        .imageUrl("/img/hotels/hotel3.jpg")
                        .build();

                Hotel hotel10 = Hotel.builder()
                        .name("Urban Oasis Hotel")
                        .location("202 City Center, Chicago, IL")
                        .description("Modern hotel offering convenience and style in the heart of the city.")
                        .contactInfo("info@urbanoasishotel.com")
                        .imageUrl("/img/hotels/hotel4.jpg")
                        .build();

                Hotel hotel11 = Hotel.builder()
                        .name("Mountain Lodge")
                        .location("303 Mountain Pass, Aspen, CO")
                        .description("Rustic lodge with stunning mountain views and outdoor activities.")
                        .contactInfo("info@mountainlodge.com")
                        .imageUrl("/img/hotels/hotel6.jpg")
                        .build();

                Hotel hotel12 = Hotel.builder()
                        .name("Tropical Paradise Resort")
                        .location("404 Tropical Way, Honolulu, HI")
                        .description("Luxurious resort with beautiful beaches and tropical ambiance.")
                        .contactInfo("info@tropicalparadiseresort.com")
                        .imageUrl("/img/hotels/hotel7.jpg")
                        .build();

                Hotel hotel13 = Hotel.builder()
                        .name("Cityscape Hotel")
                        .location("505 Downtown Ave, Los Angeles, CA")
                        .description("Stylish hotel with panoramic city views and vibrant nightlife.")
                        .contactInfo("info@cityscapehotel.com")
                        .imageUrl("/img/hotels/hotel3.jpg")
                        .build();

                Hotel hotel14 = Hotel.builder()
                        .name("Countryside Inn")
                        .location("606 Rural Rd, Lexington, KY")
                        .description("Charming inn offering a cozy atmosphere and beautiful countryside views.")
                        .contactInfo("info@countrysideinn.com")
                        .imageUrl("/img/hotels/hotel6.jpg")
                        .build();

                Hotel hotel15 = Hotel.builder()
                        .name("Seaside Haven")
                        .location("707 Beach Blvd, Galveston, TX")
                        .description("Relax by the sea with comfortable rooms and stunning ocean views.")
                        .contactInfo("info@seasidehaven.com")
                        .imageUrl("/img/hotels/hotel7.jpg")
                        .build();

                Hotel hotel16 = Hotel.builder()
                        .name("Forest Lodge")
                        .location("808 Woodland Dr, Eugene, OR")
                        .description("Serene lodge nestled in the forest, perfect for nature lovers.")
                        .contactInfo("info@forestlodge.com")
                        .imageUrl("/img/hotels/hotel1.jpg")
                        .build();

                Hotel hotel17 = Hotel.builder()
                        .name("Chic Boutique Hotel")
                        .location("909 Fashion St, Miami, FL")
                        .description("Trendy boutique hotel with stylish rooms and modern amenities.")
                        .contactInfo("info@chicboutiquehotel.com")
                        .imageUrl("/img/hotels/hotel3.jpg")
                        .build();

                Hotel hotel18 = Hotel.builder()
                        .name("Elegant Manor")
                        .location("1010 Estate Rd, Nashville, TN")
                        .description("Luxurious manor offering elegant rooms and high-end service.")
                        .contactInfo("info@elegantmanor.com")
                        .imageUrl("/img/hotels/hotel4.jpg")
                        .build();

                Hotel hotel19 = Hotel.builder()
                        .name("Desert Mirage Hotel")
                        .location("1111 Mirage Blvd, Las Vegas, NV")
                        .description("Opulent hotel with unique desert-inspired decor and premium amenities.")
                        .contactInfo("info@desertmiragehotel.com")
                        .imageUrl("/img/hotels/hotel6.jpg")
                        .build();

                Hotel hotel20 = Hotel.builder()
                        .name("Royal Plaza Hotel")
                        .location("1212 Plaza Dr, San Francisco, CA")
                        .description("Sophisticated hotel offering top-notch facilities and exceptional service.")
                        .contactInfo("info@royalplazahotel.com")
                        .imageUrl("/img/hotels/hotel7.jpg")
                        .build();

                Hotel hotel21 = Hotel.builder()
                        .name("Cozy Cabin Inn")
                        .location("1313 Cabin Rd, Lake Tahoe, CA")
                        .description("Charming cabin-style inn with a warm, rustic atmosphere.")
                        .contactInfo("info@cozycabininn.com")
                        .imageUrl("/img/hotels/hotel4.jpg")
                        .build();

                hotelRepository.save(hotel1);
                hotelRepository.save(hotel2);
                hotelRepository.save(hotel3);
                hotelRepository.save(hotel4);
                hotelRepository.save(hotel5);
                hotelRepository.save(hotel6);
                hotelRepository.save(hotel7);
                hotelRepository.save(hotel8);
                hotelRepository.save(hotel9);
                hotelRepository.save(hotel10);
                hotelRepository.save(hotel11);
                hotelRepository.save(hotel12);
                hotelRepository.save(hotel13);
                hotelRepository.save(hotel14);
                hotelRepository.save(hotel15);
                hotelRepository.save(hotel16);
                hotelRepository.save(hotel17);
                hotelRepository.save(hotel18);
                hotelRepository.save(hotel19);
                hotelRepository.save(hotel20);
                hotelRepository.save(hotel21);



                Booking booking1 = Booking.builder()
                        .customer(user1)
                        .name("Booking for Kaya Alp")
                        .email("customer1@hotel.com")
                        .phone("123456789")
                        .address("123 Main St, City A")
                        .checkinDate(LocalDate.of(2024, 9, 15))
                        .checkoutDate(LocalDate.of(2024, 9, 20))
                        .status(BookingStatus.CONFIRMED)
                        .totalAmount(500.00)
                        .createdAt(LocalDateTime.now())
                        .build();

                Booking booking2 = Booking.builder()
                        .customer(user2)
                        .name("Booking for Max")
                        .email("customer2@hotel.com")
                        .phone("987654321")
                        .address("456 Main St, City B")
                        .checkinDate(LocalDate.of(2024, 10, 5))
                        .checkoutDate(LocalDate.of(2024, 10, 10))
                        .status(BookingStatus.PENDING)
                        .totalAmount(750.00)
                        .createdAt(LocalDateTime.now())
                        .build();

                Booking booking3 = Booking.builder()
                        .customer(user1)
                        .name("Booking for Kaya's Family")
                        .email("customer1@hotel.com")
                        .phone("123456788")
                        .address("123 Main St, City A")
                        .checkinDate(LocalDate.of(2024, 11, 1))
                        .checkoutDate(LocalDate.of(2024, 11, 5))
                        .status(BookingStatus.CONFIRMED)
                        .totalAmount(600.00)
                        .createdAt(LocalDateTime.now())
                        .build();

                Booking booking4 = Booking.builder()
                        .customer(user3)
                        .name("Booking for Hotel Staff Event")
                        .email("customer3@hotel.com")
                        .phone("555555555")
                        .address("789 Main St, City C")
                        .checkinDate(LocalDate.of(2024, 12, 15))
                        .checkoutDate(LocalDate.of(2024, 12, 20))
                        .status(BookingStatus.CONFIRMED)
                        .totalAmount(900.00)
                        .createdAt(LocalDateTime.now())
                        .build();

                Booking booking5 = Booking.builder()
                        .customer(user2)
                        .name("Booking for Business Trip")
                        .email("customer2@hotel.com")
                        .phone("987654320")
                        .address("456 Main St, City B")
                        .checkinDate(LocalDate.of(2024, 10, 20))
                        .checkoutDate(LocalDate.of(2024, 10, 25))
                        .status(BookingStatus.CANCELLED)
                        .totalAmount(400.00)
                        .createdAt(LocalDateTime.now())
                        .build();

                bookingRepository.save(booking1);
                bookingRepository.save(booking2);
                bookingRepository.save(booking3);
                bookingRepository.save(booking4);
                bookingRepository.save(booking5);

                Payment payment1 = Payment.builder()
                        .customer(user1)
                        .booking(booking1)
                        .method("Credit Card")
                        .amount(500.00)
                        .status(PaymentStatus.COMPLETED)
                        .timestamp(LocalDateTime.now())
                        .transactionId("TXN123456789")
                        .build();

                Payment payment2 = Payment.builder()
                        .customer(user2)
                        .booking(booking2)
                        .method("PayPal")
                        .amount(750.00)
                        .status(PaymentStatus.PENDING)
                        .timestamp(LocalDateTime.now())
                        .transactionId("TXN987654321")
                        .build();

                Payment payment3 = Payment.builder()
                        .customer(user3)
                        .booking(booking3)
                        .method("Bank Transfer")
                        .amount(600.00)
                        .status(PaymentStatus.REFUNDED)
                        .timestamp(LocalDateTime.now())
                        .transactionId("TXN543216789")
                        .build();

                Payment payment4 = Payment.builder()
                        .customer(user4)
                        .booking(booking4)
                        .method("Credit Card")
                        .amount(400.00)
                        .status(PaymentStatus.FAILED)
                        .timestamp(LocalDateTime.now())
                        .transactionId("TXN678912345")
                        .build();

                paymentRepository.save(payment1);
                paymentRepository.save(payment2);
                paymentRepository.save(payment3);
                paymentRepository.save(payment4);

                Room room1 = Room.builder()
                        .hotel(hotel1)
                        .name("Deluxe Suite - Hotel 1")
                        .type(RoomType.SINGLE)
                        .pricePerNight(150.00)
                        .status(RoomStatus.AVAILABLE)
                        .description("A luxurious suite with stunning city views at Hotel 1.")
                        .build();

                Room room2 = Room.builder()
                        .hotel(hotel2)
                        .name("Standard Room - Hotel 2")
                        .type(RoomType.DOUBLE)
                        .pricePerNight(80.00)
                        .status(RoomStatus.OCCUPIED)
                        .description("Comfortable room with essential amenities at Hotel 2.")
                        .build();

                Room room3 = Room.builder()
                        .hotel(hotel3)
                        .name("Executive Room - Hotel 3")
                        .type(RoomType.FAMILY)
                        .pricePerNight(120.00)
                        .status(RoomStatus.MAINTENANCE)
                        .description("Spacious room with a work desk and premium facilities at Hotel 3.")
                        .build();

                Room room4 = Room.builder()
                        .hotel(hotel4)
                        .name("Economy Room - Hotel 4")
                        .type(RoomType.SINGLE)
                        .pricePerNight(60.00)
                        .status(RoomStatus.AVAILABLE)
                        .description("Affordable room with basic amenities at Hotel 4.")
                        .build();

                roomRepository.save(room1);
                roomRepository.save(room2);
                roomRepository.save(room3);
                roomRepository.save(room4);

                Review review1 = Review.builder()
                        .customer(user1)
                        .room(room1)
                        .content("Great stay, very comfortable and clean room!")
                        .rating(5)
                        .createdAt(LocalDateTime.now())
                        .build();

                Review review2 = Review.builder()
                        .customer(user2)
                        .room(room2)
                        .content("Good value for the money, but the room could use some updates.")
                        .rating(3)
                        .createdAt(LocalDateTime.now())
                        .build();

                Review review3 = Review.builder()
                        .customer(user3)
                        .room(room3)
                        .content("Excellent service and the room was fantastic!")
                        .rating(4)
                        .createdAt(LocalDateTime.now())
                        .build();

                Review review4 = Review.builder()
                        .customer(user4)
                        .room(room4)
                        .content("The room was okay, but the noise from the street was disruptive.")
                        .rating(2)
                        .createdAt(LocalDateTime.now())
                        .build();

                reviewRepository.save(review1);
                reviewRepository.save(review2);
                reviewRepository.save(review3);
                reviewRepository.save(review4);

                BookingRoom bookingRoom1 = BookingRoom.builder()
                        .booking(booking1)
                        .room(room1)
                        .currentPricePerNight(150.00)
                        .build();

                BookingRoom bookingRoom2 = BookingRoom.builder()
                        .booking(booking2)
                        .room(room2)
                        .currentPricePerNight(120.00)
                        .build();

                BookingRoom bookingRoom3 = BookingRoom.builder()
                        .booking(booking3)
                        .room(room4)
                        .currentPricePerNight(150.00)
                        .build();

                BookingRoom bookingRoom4 = BookingRoom.builder()
                        .booking(booking4)
                        .room(room4)
                        .currentPricePerNight(120.00)
                        .build();

                bookingRoomRepository.save(bookingRoom1);
                bookingRoomRepository.save(bookingRoom2);
                bookingRoomRepository.save(bookingRoom3);
                bookingRoomRepository.save(bookingRoom4);
            } else {
                log.info("Test data persistence is not required");
            }
            log.warn("App ready");
        } catch (DataAccessException e) {
            log.error("Exception occurred during data persistence: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected exception occurred: " + e.getMessage());
        }
    }
}
