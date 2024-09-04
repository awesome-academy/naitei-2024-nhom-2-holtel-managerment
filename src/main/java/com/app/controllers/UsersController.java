package com.app.controllers;

import com.app.constants.Role;
import com.app.dtos.UserRegistrationDTO;
import com.app.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UsersController extends BaseController {
    public UsersController(UserService userService) {
        super(userService);
    }

    @GetMapping("/register/customer")
    public String showCustomerRegistrationForm(@ModelAttribute("user") UserRegistrationDTO registrationDTO, Authentication authentication) {
        String redirect = getAuthenticatedUserRedirectUrl(authentication);
        if (redirect != null) return redirect;
        return "register-customer";
    }

    @PostMapping("/register/customer")
    public String registerCustomerAccount(@Valid @ModelAttribute("user") UserRegistrationDTO registrationDTO, BindingResult result) {
        registrationDTO.setRoleType(Role.CUSTOMER);
        return registerUser(registrationDTO, result, "register-customer", "register/customer");
    }

    @GetMapping("/register/manager")
    public String showManagerRegistrationForm(@ModelAttribute("user") UserRegistrationDTO registrationDTO, Authentication authentication) {
        String redirect = getAuthenticatedUserRedirectUrl(authentication);
        if (redirect != null) return redirect;
        return "register-manager";
    }

    @PostMapping("/register/manager")
    public String registerManagerAccount(@Valid @ModelAttribute("user") UserRegistrationDTO registrationDTO, BindingResult result) {
        registrationDTO.setRoleType(Role.HOTEL_STAFF);
        return registerUser(registrationDTO, result, "register-manager", "register/manager");
    }
}
