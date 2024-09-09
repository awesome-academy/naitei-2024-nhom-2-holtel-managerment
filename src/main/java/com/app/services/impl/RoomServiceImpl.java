package com.app.services.impl;

import com.app.models.Room;
import com.app.repositories.RoomRepository;
import com.app.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<Room> getAll() {
        return this.roomRepository.findAll();
    }

    @Override
    public List<Room> SearchByType(String type) {
        return this.SearchByType(type);
    }
}
