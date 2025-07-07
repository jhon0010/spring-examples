package main.customer.application.service;

import lombok.AllArgsConstructor;
import main.customer.domain.models.CustomerId;
import main.customer.domain.models.CustomerModel;
import main.customer.domain.repository.CustomerRepository;
import main.customer.infrastructure.dto.CustomerDTO;
import main.shared.infrastructure.exception.RESTApiRequestException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository repository;

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

    public void delete(CustomerId customerId) {

        try {
            this.repository.delete(customerId);
        } catch (EmptyResultDataAccessException e) {
            throw RESTApiRequestException.createNoCustomerByIdException(customerId.getId(), e);
        }

    }

    @Transactional
    public void update(CustomerId customerId, String name, String email) {

        CustomerModel customer = this.repository.getById(customerId).orElseThrow(() ->
                RESTApiRequestException.createNoCustomerByIdException(customerId.getId(), null)
        );

        if(name != null && !customer.getName().equals(name))  {
            customer.setName(name);
        }

        if(email != null && !customer.getEmail().equals(email)) {
            customer.setEmail(email);
        }

        this.repository.update(customer);

    }
}
