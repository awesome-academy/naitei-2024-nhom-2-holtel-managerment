package com.app.repositories;

import com.app.models.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    Page<Hotel> findAll(Pageable pageable);
    Page<Hotel> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
