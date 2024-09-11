package com.app.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BOOKING_ROOMS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@SequenceGenerator(
        name = "bookingroom_seq",
        sequenceName = "id",
        allocationSize = 1
)

public class BookingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingroom_seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(nullable = false, precision = 10)
    private Double currentPricePerNight;
}

