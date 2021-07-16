package main.customer.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@Builder
public class Customer {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Gender gender;
    private String phoneNumber;
    private LocalDate dateOfBirth;
}
