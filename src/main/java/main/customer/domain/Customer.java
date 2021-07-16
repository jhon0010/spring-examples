package main.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Customer {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Gender gender;
    private String phoneNumber;
    private LocalDate dateOfBirth;
}
