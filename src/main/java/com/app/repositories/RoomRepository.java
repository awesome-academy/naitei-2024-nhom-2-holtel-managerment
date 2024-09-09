package com.app.repositories;

import com.app.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT r from Room r WHERE r.type =:type")
    List<Room> SearchRoom(@Param("type") String type);

}
