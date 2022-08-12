package com.epam.training.fooddelivery.service;

import com.epam.training.fooddelivery.domain.Cart;
import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.domain.Order;
import com.epam.training.fooddelivery.domain.OrderItem;
import com.epam.training.fooddelivery.repository.CustomerRepository;
import com.epam.training.fooddelivery.repository.OrderItemRepository;
import com.epam.training.fooddelivery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public DefaultOrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
    }

    @Override
     public Order createOrder(Customer customer) throws LowBalanceException,IllegalStateException {
        validateBalance(customer);
        validateCart(customer.getCart());

        List<OrderItem> orderItems = customer.getCart().getOrderItems();

        Order order = new Order();
        order.setCustomer(customer);
        order.setPrice(customer.getCart().getPrice());
        order.setTimestampCreated(LocalDateTime.now());
        order.setOrderItems(orderItems);
        customer.addOrder(order);
        customer.setBalance(customer.getBalance().subtract(order.getPrice()));


        orderItems.forEach(orderItem -> orderItem.setOrder(order));


        orderRepository.saveAndFlush(order);
        customerRepository.saveAndFlush(customer);
        orderItemRepository.saveAllAndFlush(orderItems);

        return order;
    }

    private void validateBalance(Customer customer) {
        if (customer.getCart().getPrice().compareTo(customer.getBalance()) > 0)
            throw new LowBalanceException("You don't have enough money, your balance is only " + customer.getBalance() + " EUR\n" +
                    " We do not empty your cart, please remove some of the items.");
    }

    private void validateCart(Cart cart){
        if(cart.getOrderItems().isEmpty()) throw new IllegalStateException("Cart can't be empty");
    }
}
