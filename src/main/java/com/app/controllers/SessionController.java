package com.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.app.services.UsersService;


@Controller
@Slf4j
public class SessionController extends BaseController {
    public SessionController(UsersService usersService) {
        super(usersService);
    }

    @GetMapping("/login")
    public String showLoginPage(Authentication authentication) {
        String redirect = getAuthenticatedUserRedirectUrl(authentication);
        if (redirect != null) return redirect;
        return "login";
    }


}
