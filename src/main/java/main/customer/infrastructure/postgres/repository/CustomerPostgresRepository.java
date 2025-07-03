package main.customer.infrastructure.postgres.repository;

import jakarta.persistence.EntityNotFoundException;
import main.customer.domain.models.CustomerId;
import main.customer.domain.models.CustomerModel;
import main.customer.domain.repository.CustomerRepository;
import main.customer.infrastructure.mappers.CustomerEntityDataMapper;
import main.customer.infrastructure.postgres.daos.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CustomerPostgresRepository implements CustomerRepository {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerPostgresRepository(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<CustomerModel> getAll() {
        return this.customerDAO.findAll()
                .stream()
                .map(CustomerEntityDataMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerModel create(CustomerModel model) {
        return CustomerEntityDataMapper.toModel(
                this.customerDAO.save(CustomerEntityDataMapper.fromModel(model))
        );
    }

    @Override
    public Optional<CustomerModel> getById(CustomerId customerId) {

        try {
            return Optional.of(
                    CustomerEntityDataMapper.toModel(this.customerDAO.getById(customerId.getId()))
            );
        } catch (EntityNotFoundException enfe) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<CustomerModel> getByEmail(String email) {
        return this.customerDAO.getByEmail(email)
                .map(CustomerEntityDataMapper::toModel);
    }

    @Override
    public void delete(CustomerId customerId) {
        this.customerDAO.deleteById(customerId.getId());
    }

    @Override
    public void update(CustomerModel customer) {
        this.customerDAO.save(CustomerEntityDataMapper.fromModel(customer));
    }
}
