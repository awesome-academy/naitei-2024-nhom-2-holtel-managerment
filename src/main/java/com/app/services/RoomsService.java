package com.app.services;

import com.app.models.Room;

import java.util.List;

public interface RoomsService {
    List<Room> getAll();
    List<Room> SearchByString(String type);
}
