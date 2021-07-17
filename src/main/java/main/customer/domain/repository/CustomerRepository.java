package main.customer.domain.repository;

import main.customer.domain.models.CustomerModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository {

    List<CustomerModel> getAll();
    CustomerModel create(CustomerModel model);
    Optional<CustomerModel> getByEmail(String email);
}
