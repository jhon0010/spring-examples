package main.domain.repository;

import main.domain.models.CustomerId;
import main.domain.models.CustomerModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository {

    List<CustomerModel> getAll();
    CustomerModel create(CustomerModel model);

    Optional<CustomerModel> getById(CustomerId customerId);
    Optional<CustomerModel> getByEmail(String email);
    void delete(CustomerId customerId);
    void update(CustomerModel customer);
}
