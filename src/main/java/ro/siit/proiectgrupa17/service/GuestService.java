package ro.siit.proiectgrupa17.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.siit.proiectgrupa17.model.Guest;
import ro.siit.proiectgrupa17.model.dto.CreateGuestDto;
import ro.siit.proiectgrupa17.repository.GuestRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public String createGuest(CreateGuestDto createGuestDto) {
        Guest guest = Guest.builder()
                .phoneNumber(createGuestDto.getPhoneNumber())
                .cnp(createGuestDto.getCnp())
                .firstName(createGuestDto.getFirstName())
                .lastName(createGuestDto.getLastName())
                .build();
        validateGuest(guest);
        return "Create guest with id: " + guestRepository.save(guest).getId();
    }

    private void validateGuest(Guest guest) {
        if (guest == null || guest.getFirstName().isEmpty()
                || guest.getLastName().isEmpty() || guest.getPhoneNumber().isEmpty()
                || guest.getCnp() < 999999999999L) {
            throw new RuntimeException("Invalid data, please try again");
        }
    }

    public Guest findById(int id) {
        return guestRepository.findById(id).orElseThrow(() -> new RuntimeException("Guest not found"));
    }

    public void updateGuestById(int id, String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            throw new RuntimeException("Invalid phone number");
        }
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("User not found"));
        guest.setPhoneNumber(phoneNumber);
        guestRepository.save(guest);
    }

    public void deleteGuestById(int id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("User not found"));
        guestRepository.deleteById(id);
    }

    void batchDelete(int[] ids) {
        for (int id: ids) {
            deleteGuestById(id);
        }
    }
}
