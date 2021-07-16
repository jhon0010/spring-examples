package main.customer.infrastructure.postgres.repository;

import main.customer.domain.models.CustomerModel;
import main.customer.domain.repository.CustomerRepository;
import main.customer.infrastructure.mappers.CustomerEntityDataMapper;
import main.customer.infrastructure.postgres.daos.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
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
}
