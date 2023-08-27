package ro.siit.proiectgrupa17.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.siit.proiectgrupa17.model.Guest;
import ro.siit.proiectgrupa17.model.dto.CreateGuestDto;
import ro.siit.proiectgrupa17.repository.GuestRepository;
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

    @GetMapping("/get1ById")
    public String forwarder(@RequestParam String id) {
        return "redirect:/guests/" + id;
    }
    @GetMapping("/guests/{id}")
    public String findGuestById(@PathVariable("id") int id, Model model) {
        Guest guest = guestService.findById(id);
        model.addAttribute("guests", List.of(guest));
        System.out.println("Found guest with ID: " + id);
        return "guests";
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createGuests(@RequestBody CreateGuestDto createGuestDto) {
        System.out.println("Guest created");
        return guestService.createGuest(createGuestDto);
    }

    @PatchMapping("/guests")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGuest(@RequestParam("id") int id, @RequestParam("phoneNumber") String phoneNumber) {
         guestService.updateGuestById(id, phoneNumber);
         System.out.println("Guest updated with ID: " + id);
    }

    @DeleteMapping("/guests/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuest(@PathVariable("id") int id) {
        guestService.deleteGuestById(id);
    }

    @GetMapping("/deleteConfirmation/{id}")
    public String deleteConfirmation(@PathVariable("id") int id, Model model) {
        Guest guest = guestService.findById(id);
        model.addAttribute("guest", guest);
        return "deleteConfirmation";
    }

    @PostMapping("/guests/{id}/delete")
    public String deleteGuest(@PathVariable("id") int id, @RequestParam("confirmation") String confirmation) {
        if (confirmation.equals("yes")) {
            guestService.deleteGuestById(id);
            System.out.println("Guest deleted with ID: " + id);

        } else if (confirmation.equals("undo")) {
        System.out.println("Delete operation was undone for guest with ID: " + id);
    }
        else if (confirmation.equals("no")) {
            System.out.println("Delete operation complete for guest with ID: " + id);
    }
        return "redirect:/guests";
    }
}
