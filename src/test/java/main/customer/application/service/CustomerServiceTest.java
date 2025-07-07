package main.customer.application.service;

import main.domain.models.CustomerModel;
import main.domain.repository.CustomerRepository;
import main.infrastructure.web.dto.CustomerDTO;
import main.service.CustomerService;
import mother.dto.CustomerModelObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Collectors;

class CustomerServiceTest {

	private CustomerService customerService;
	private CustomerRepository repository;

	@BeforeEach
	public void setUp() {
		this.repository = Mockito.mock(CustomerRepository.class);
		this.customerService = new CustomerService(repository);
	}

	@Test
	void contextLoads() {

		Mockito.when(this.repository.getAll()).thenReturn(CustomerModelObjectMother.getCustomers());

		List<CustomerDTO> customersDtos = customerService.defaultList();
		Assertions.assertNotNull(customersDtos);

		List<CustomerModel> customerModels = customersDtos.stream().map(CustomerDTO::toModel).collect(Collectors.toList());

		Assertions.assertNotNull(customerModels);
		Assertions.assertEquals(CustomerModelObjectMother.getCustomers().size(),customerModels.size());
	}

}
