package com.app.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "HOTELS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@SequenceGenerator(
        name = "hotel_seq",
        sequenceName = "id",
        allocationSize = 1
)

public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_seq")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String location;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String contactInfo;

    @OneToMany(mappedBy = "hotel")
    private Set<Room> rooms;

    // hotels image
    @Column(length = 255)
    private String imageUrl;

}
