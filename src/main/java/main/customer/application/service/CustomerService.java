package main.customer.application.service;

import main.customer.domain.models.Customer;
import main.customer.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CustomerService(CustomerRepository repository) {

    @Autowired
    public CustomerService {
    }

    public List<Customer> defaultList() {
        return this.repository.getAll();
    }

}
