package com.app.controllers.customer;

import com.app.models.Hotel;
import com.app.repositories.HotelRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customer")
@Slf4j
@RequiredArgsConstructor
public class BookingController {
    private final HotelRepository hotelRepository;

    @GetMapping("/hotels")
    public String getHotels(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(value = "keyword", required = false) String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Hotel> hotelPage;

        if (keyword != null && !keyword.isEmpty()) {
            hotelPage = hotelRepository.findByNameContainingIgnoreCase(keyword, pageable);
            model.addAttribute("keyword", keyword);
        } else {
            hotelPage = hotelRepository.findAll(pageable);
        }

        model.addAttribute("hotels", hotelPage.getContent());
        model.addAttribute("currentPage", hotelPage.getNumber());
        model.addAttribute("totalPages", hotelPage.getTotalPages());

        return "customer/hotels";
    }
}
