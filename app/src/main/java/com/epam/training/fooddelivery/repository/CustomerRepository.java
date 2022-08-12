package com.epam.training.fooddelivery.repository;

import com.epam.training.fooddelivery.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public Customer findByEmail(String email);
    public Customer findByEmailAndPassword(String email,String password);
}
