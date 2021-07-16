package main.customer.infrastructure.mappers;

import main.customer.domain.models.Customer;
import main.customer.infrastructure.postgres.entities.CustomerEntity;

public final class CustomerEntityDataMapper {

    public static Customer toModel(CustomerEntity entity){

        return Customer.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .gender(entity.getGender())
                .phoneNumber(entity.getPhoneNumber())
                .dateOfBirth(entity.getDateOfBirth())
                .lastName(entity.getLastName())
                .build();
    }

}
