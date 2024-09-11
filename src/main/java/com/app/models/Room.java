package com.app.models;

import com.app.constants.RoomStatus;
import com.app.constants.RoomType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "ROOMS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@SequenceGenerator(
        name = "room_seq",
        sequenceName = "id",
        allocationSize = 1
)

public class Room {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "type")
    private RoomType type;

    @Column(nullable = false, precision = 10)
    private Double pricePerNight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private RoomStatus status;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "room")
    private Set<Review> reviews;


}

