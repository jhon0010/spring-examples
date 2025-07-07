package main.infrastructure.web.controllers;

import main.application.services.CustomerService;
import main.domain.models.CustomerId;
import main.infrastructure.web.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<List<CustomerDTO>> getCustomers(){
        return ResponseEntity.ok(this.customerService.defaultList());
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

    @PutMapping(
            path = "{customerId}"
    )
    public void updateCustomer (
            @PathVariable("customerId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        this.customerService.update(CustomerId.builder().id(id).build(), name , email);
    }

}
