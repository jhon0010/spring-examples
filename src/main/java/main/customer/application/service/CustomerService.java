package main.customer.application.service;

import main.customer.domain.Customer;
import main.customer.domain.Gender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class CustomerService {

    public List<Customer> defaultList(){

        return asList(
                new Customer(1L,"Jhon", "Doe", "jhon.doe@gmail.com",
                        Gender.MALE, "+57300111222", LocalDate.now()),
                new Customer(2L,"Carlos", "Perez", "carlos.perez@gmail.com",
                        Gender.MALE, "+57300333444", LocalDate.now())
        );
    }

}
