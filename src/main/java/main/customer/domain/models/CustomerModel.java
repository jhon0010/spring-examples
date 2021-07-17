package main.customer.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;


@Data
@AllArgsConstructor
@Builder
public class CustomerModel {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Gender gender;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Integer age;

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
