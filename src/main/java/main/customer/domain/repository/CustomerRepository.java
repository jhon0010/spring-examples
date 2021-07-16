package main.customer.domain.repository;

import main.customer.domain.models.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository {

    List<Customer> getAll();

}
