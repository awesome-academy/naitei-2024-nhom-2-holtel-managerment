package com.app.services;

import com.app.models.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAll();
    List<Room> SearchByType(String type);
}
