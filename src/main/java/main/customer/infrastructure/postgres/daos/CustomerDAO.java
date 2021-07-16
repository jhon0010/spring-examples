package main.customer.infrastructure.postgres.daos;

import main.customer.infrastructure.postgres.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<CustomerEntity, Long> {}
