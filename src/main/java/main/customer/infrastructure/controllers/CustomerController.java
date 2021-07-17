package main.customer.infrastructure.controllers;

import main.customer.application.service.CustomerService;
import main.customer.domain.models.CustomerId;
import main.customer.infrastructure.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> getCustomers(){
        return this.customerService.defaultList();
    }

    @PostMapping
    public CustomerDTO createCustomer (@RequestBody CustomerDTO dto) {
        return this.customerService.create(dto);
    }

    @DeleteMapping(
        path = "{customerId}"
    )
    public void deleteCustomer (@PathVariable("customerId") Long id) {
        this.customerService.delete(CustomerId.builder().id(id).build());
    }

}
