package com.app.services.impl;

import com.app.repositories.HotelRepository;
import com.app.services.HotelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelsServiceImpl implements HotelsService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public int countHotels() {
        return (int) hotelRepository.count();
    }
}
