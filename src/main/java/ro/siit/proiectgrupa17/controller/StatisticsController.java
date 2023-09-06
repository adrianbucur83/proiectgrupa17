package ro.siit.proiectgrupa17.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.siit.proiectgrupa17.repository.GuestRepository;

@Controller
@RequiredArgsConstructor
public class StatisticsController {

    private final GuestRepository guestRepository;

    @GetMapping("/statistics")

    public String getNumberOfGuests(Model model) {
        String guestsNumber = String.valueOf(guestRepository.findAll().size());
        model.addAttribute("guestsNumber", guestsNumber);
        return "statistics";
    }

}
