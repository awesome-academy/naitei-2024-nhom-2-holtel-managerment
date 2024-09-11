package com.app.controllers.manager;

import com.app.constants.Role;
import com.app.controllers.BaseController;
import com.app.dtos.UserRegistrationDTO;
import com.app.services.BookingsService;
import com.app.services.HotelsService;
import com.app.services.UsersService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/manager")
@Slf4j
public class HotelManagerController extends BaseController {
    @Autowired
    BookingsService bookingsService;
    @Autowired
    UsersService usersService;
    @Autowired
    HotelsService hotelsService;
    public HotelManagerController(UsersService usersService) {
        super(usersService);
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        int bookingCount = bookingsService.countBookings();
        int hotelCount = hotelsService.countHotels();
        int userCount = usersService.countUsers();

        // Add attributes to the model to be used in the Thymeleaf template
        model.addAttribute("bookingCount", bookingCount);
        model.addAttribute("hotelCount", hotelCount);
        model.addAttribute("userCount", userCount);
        return "manager/dashboard";
    }

    @GetMapping("/users/manager")
    public String showManagerRegistrationForm(@ModelAttribute("user") UserRegistrationDTO registrationDTO, Authentication authentication) {
        String redirect = getAuthenticatedUserRedirectUrl(authentication);
        if (redirect != null) return redirect;
        return "register-manager";
    }

    @PostMapping("/users/manager")
    public String registerManagerAccount(@Valid @ModelAttribute("user") UserRegistrationDTO registrationDTO, BindingResult result) {
        registrationDTO.setRoleType(Role.HOTEL_STAFF);
        return registerUser(registrationDTO, result, "register-manager", "users/manager");
    }
}