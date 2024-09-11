package com.app.controllers;

import com.app.models.Room;
import com.app.services.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class RoomsController {
    @Autowired
    private RoomsService roomService;
    @GetMapping("/rooms")
    public  String index(Model model, @Param("keyword") String keyword) {
    List<Room> rooms = this.roomService.getAll();
    if (keyword != null && !keyword.isEmpty()) {
        rooms = this.roomService.SearchByString(keyword);
    }

    model.addAttribute("room", rooms);
    return "index";
}
}
