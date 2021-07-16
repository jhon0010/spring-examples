package main.customer.infrastructure.dto;

import lombok.Builder;
import main.customer.domain.models.CustomerModel;
import main.customer.domain.models.Gender;

import java.time.LocalDate;

@Builder
public record CustomerDTO (
        String name,
        String lastName,
        String email,
        Gender gender,
        String phoneNumber,
        LocalDate dateOfBirth,
        Integer age
){
    public static CustomerDTO fromModel(CustomerModel model) {
        return new  CustomerDTO(
               model.getName(),
               model.getLastName(),
               model.getEmail(),
               model.getGender(),
               model.getPhoneNumber(),
               model.getDateOfBirth(),
               model.getAge()
        );
    }

    public CustomerModel toModel() {
        return CustomerModel.builder()
                .name(this.name())
                .email(this.email())
                .gender(this.gender())
                .phoneNumber(this.phoneNumber())
                .dateOfBirth(this.dateOfBirth())
                .lastName(this.lastName())
                .build();
    }
}



