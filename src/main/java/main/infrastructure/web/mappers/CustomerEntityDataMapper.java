package main.infrastructure.web.mappers;

import main.domain.models.CustomerId;
import main.domain.models.CustomerModel;
import main.infrastructure.db.postgres.entities.CustomerEntity;

public final class CustomerEntityDataMapper {

    public static CustomerModel toModel(CustomerEntity entity){

        return CustomerModel.builder()
                .customerId(
                        CustomerId.builder()
                                .id(entity.getId())
                                .build()
                )
                .name(entity.getName())
                .email(entity.getEmail())
                .gender(entity.getGender())
                .phoneNumber(entity.getPhoneNumber())
                .dateOfBirth(entity.getDateOfBirth())
                .lastName(entity.getLastName())
                .build();
    }

    public static CustomerEntity fromModel(CustomerModel model){

        return CustomerEntity.builder()
                .id(model.getCustomerId() != null ? model.getCustomerId().getId() : null)
                .dateOfBirth(model.getDateOfBirth())
                .email(model.getEmail())
                .gender(model.getGender())
                .lastName(model.getLastName())
                .name(model.getName())
                .phoneNumber(model.getPhoneNumber())
                .build();
    }

}
