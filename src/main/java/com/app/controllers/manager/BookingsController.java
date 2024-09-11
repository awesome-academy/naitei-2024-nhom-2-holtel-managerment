package com.app.controllers.manager;

import com.app.dtos.BookingDTO;
import com.app.models.Booking;
import com.app.services.BookingsService;
import com.app.services.UsersService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/manager/bookings")
@RequiredArgsConstructor
@Slf4j
public class BookingsController {

    @Autowired
    BookingsService bookingsService;

    @Autowired
    UsersService usersService;

    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Booking> bookingPage = bookingsService.findPaginatedBookings(pageable);
        int totalPages = bookingPage.getTotalPages();
        model.addAttribute("bookingPage", bookingPage);
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "manager/bookings/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("bookingDTO", new BookingDTO());
        model.addAttribute("customers", usersService.findAll());
        return "manager/bookings/add-booking";
    }

    @PostMapping("/")
    public String saveBooking(@ModelAttribute("bookingDTO") BookingDTO bookingDTO, RedirectAttributes redirectAttributes) {
        bookingsService.saveBooking(bookingDTO);
        redirectAttributes.addFlashAttribute("message", "Booking created successfully!");
        return "redirect:/manager/bookings";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Booking booking = bookingsService.findById(id);
        model.addAttribute("bookingDTO", bookingsService.convertToDTO(booking));
        return "manager/bookings/edit-booking";
    }

    @PatchMapping("/{id}/update")
    public String updateBooking(@PathVariable("id") Integer id, @ModelAttribute("bookingDTO") BookingDTO bookingDTO, RedirectAttributes redirectAttributes) {
        bookingsService.updateBooking(id, bookingDTO);
        redirectAttributes.addFlashAttribute("message", "Booking updated successfully!");
        return "redirect:/manager/bookings";
    }
}
