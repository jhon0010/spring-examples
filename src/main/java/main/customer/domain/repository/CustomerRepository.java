package main.customer.domain.repository;

import main.customer.domain.models.CustomerModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository {

    List<CustomerModel> getAll();
    CustomerModel create(CustomerModel model);

}
