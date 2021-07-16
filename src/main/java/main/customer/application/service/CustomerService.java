package main.customer.application.service;

import main.customer.domain.repository.CustomerRepository;
import main.customer.infrastructure.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerDTO> defaultList() {
        return this.repository.getAll().stream()
                .map(CustomerDTO::fromModel)
                .collect(Collectors.toList());
    }


    public CustomerDTO create(CustomerDTO dto){
        return CustomerDTO.fromModel(
                this.repository.create(dto.toModel())
        );
    }

}
