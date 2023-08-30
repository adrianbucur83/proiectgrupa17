package ro.siit.proiectgrupa17.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@Table(name = "guests")
@AllArgsConstructor
@NoArgsConstructor

public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private long cnp;
    private String lastName;
    private String phoneNumber;
    private String firstName;
}
