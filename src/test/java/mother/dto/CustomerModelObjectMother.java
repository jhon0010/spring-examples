package mother.dto;

import main.domain.models.CustomerId;
import main.domain.models.CustomerModel;

import java.time.LocalDate;
import java.util.List;

/**
 * Class in charge of creating CustomerModel for testing purposes.
 */
public class CustomerModelObjectMother {

    public static List<CustomerModel> getCustomers() {
        return List.of(
                CustomerModel.builder()
                        .customerId(CustomerId.builder().id(1L).build())
                        .name("John")
                        .lastName("Doe")
                        .email("random@gmail.com")
                        .dateOfBirth(LocalDate.now())
                        .build(),
                CustomerModel.builder()
                        .customerId(CustomerId.builder().id(2L).build())
                        .name("Jane")
                        .lastName("Doe")
                        .email("some@other.com")
                        .dateOfBirth(LocalDate.now())
                        .build()
        );
    }

}
