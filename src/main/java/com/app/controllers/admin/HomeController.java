package com.app.controllers.admin;

import com.app.services.BookingsService;
import com.app.services.HotelsService;
import com.app.services.UsersService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @Autowired
    BookingsService bookingsService;
    @Autowired
    UsersService usersService;
    @Autowired
    HotelsService hotelsService;
    @GetMapping("/dashboard")
    public String showDashboard(Model model){
        // Retrieve counts from the service layer
        int bookingCount = bookingsService.countBookings();
        int hotelCount = hotelsService.countHotels();
        int userCount = usersService.countUsers();

        // Add attributes to the model to be used in the Thymeleaf template
        model.addAttribute("bookingCount", bookingCount);
        model.addAttribute("hotelCount", hotelCount);
        model.addAttribute("userCount", userCount);
        return "admin/dashboard";}
}
