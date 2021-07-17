package main.customer.application.service;

import main.customer.domain.models.CustomerModel;
import main.customer.domain.repository.CustomerRepository;
import main.customer.infrastructure.dto.CustomerDTO;
import main.shared.infrastructure.exception.RESTApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

        Optional<CustomerModel> customer = this.repository.getByEmail(dto.email());

        if(customer.isPresent()) {
            throw new RESTApiRequestException("The email es already taken," +
                    " please verify if you are already registered in our site");
        }

        return CustomerDTO.fromModel(
                this.repository.create(dto.toModel())
        );
    }

}
