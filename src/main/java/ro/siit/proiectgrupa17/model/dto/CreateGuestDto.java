package ro.siit.proiectgrupa17.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateGuestDto {
    private long cnp;
    @NotBlank
    @Pattern(regexp = "([A-Z][a-z]{2,20})\\w", message = "Invalid first name, use only letters starting with a capital ")
    private String firstName;
    @NotNull
    private String lastName;
    private String phoneNumber;

}
