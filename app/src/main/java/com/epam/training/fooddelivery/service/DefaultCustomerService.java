package com.epam.training.fooddelivery.service;

import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;

    public DefaultCustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

}
