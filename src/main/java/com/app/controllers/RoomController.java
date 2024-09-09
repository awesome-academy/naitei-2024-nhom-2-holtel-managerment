package com.app.controllers;

import com.app.models.Room;
import com.app.services.RoomService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;
    @GetMapping("/")
    public  String room(Model model, @Param("type") String type) {
    List<Room> list = this.roomService.getAll();
    if (type != null ){
        list = this.roomService.SearchByType(type);
    }
    model.addAttribute("SearchRoom", list);
    return "index";
}
}
