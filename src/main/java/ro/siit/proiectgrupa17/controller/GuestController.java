package ro.siit.proiectgrupa17.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.siit.proiectgrupa17.model.Guest;
import ro.siit.proiectgrupa17.model.dto.CreateGuestDto;
import ro.siit.proiectgrupa17.service.GuestService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @GetMapping("/guests")
    public String listGuests(Model model) {
        List<Guest> guests = guestService.findAll();
        model.addAttribute("guests", guests);
        return "guests";
    }

    @GetMapping("/findById")
    public String findByIdForm() {
        return "findById";
    }

    @GetMapping("/createGuestForm")
    public String createGuestForm() {
        return "createGuestForm";
    }

    @GetMapping("/get1ById")
    public String forwarder(@RequestParam String id) {
        return "redirect:/guests/" + id;
    }

    @GetMapping("/guests/{id}")
    public String findGuestById(@PathVariable("id") int id, Model model) {
        Guest guest = guestService.findById(id);
        model.addAttribute("guests", List.of(guest));
        return "guests";
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @Operation(summary = "Please send full guest details here: cnp + first name + ....", description = "This is the full documentation for the api")
    public String createGuests(@Valid @RequestBody CreateGuestDto createGuestDto) {
        try {
            return guestService.createGuest(createGuestDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data, try again", e);
        }
    }

    @PatchMapping("/guests")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void updateGuest(@RequestParam("id") int id, @RequestParam("phoneNumber") String phoneNumber) {
        guestService.updateGuestById(id, phoneNumber);
    }

    @DeleteMapping("/guests/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteGuest(@PathVariable("id") int id) {
        guestService.deleteGuestById(id);
    }

}
