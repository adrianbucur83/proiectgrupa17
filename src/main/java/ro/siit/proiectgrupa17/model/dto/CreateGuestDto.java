package ro.siit.proiectgrupa17.model.dto;

import lombok.Data;

@Data
public class CreateGuestDto {
    private long cnp;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
