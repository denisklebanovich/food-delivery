package com.epam.training.fooddelivery.controller;

import com.epam.training.fooddelivery.api.OrderserviceApi;
import com.epam.training.fooddelivery.domain.Cart;
import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.domain.Order;
import com.epam.training.fooddelivery.exceptions.LowBalanceException;
import com.epam.training.fooddelivery.model.CartModel;
import com.epam.training.fooddelivery.model.OrderModel;
import com.epam.training.fooddelivery.repository.CustomerRepository;
import com.epam.training.fooddelivery.repository.OrderRepository;
import com.epam.training.fooddelivery.service.OrderService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderservice")
public class OrderController implements OrderserviceApi {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final OrderService orderService;

    private final Converter<Order, OrderModel> orderOrderModelConverter;

    private final Converter<CartModel, Cart> cartModelCartConverter;

    public OrderController(OrderRepository orderRepository, CustomerRepository customerRepository, OrderService orderService, Converter<Order, OrderModel> orderOrderModelConverter, Converter<CartModel, Cart> cartModelCartConverter) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderService = orderService;
        this.orderOrderModelConverter = orderOrderModelConverter;
        this.cartModelCartConverter = cartModelCartConverter;
    }

    @Override
    @PostMapping("/orders")
    public ResponseEntity<OrderModel> createOrder(CartModel cartModel) throws LowBalanceException, IllegalStateException {
        Customer customer = getCustomer();
        if (cartModel == null) throw new IllegalStateException();
        Cart cart = cartModelCartConverter.convert(cartModel);
        customer.setCart(cart);
        Order order = orderService.createOrder(customer);
        OrderModel orderModel = orderOrderModelConverter.convert(order);
        return new ResponseEntity<>(orderModel, HttpStatus.OK);
    }

    @Override
    @GetMapping("/orders")
    public ResponseEntity<List<OrderModel>> getCustomerOrders() {
        Customer customer = getCustomer();
        List<Order> orders = customer.getOrders();
        if(orders == null || orders.isEmpty()) return new ResponseEntity<>(null,HttpStatus.OK);
        List<OrderModel> orderModels = orders.stream().map(orderOrderModelConverter::convert).toList();
        return new ResponseEntity<>(orderModels, HttpStatus.OK);
    }

    private Customer getCustomer() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customerRepository.findByEmail(userDetails.getUsername());
    }

    @Override
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderModel> getOrderById(Long orderId) throws AccessDeniedException, NullPointerException {
        Customer customer = getCustomer();
        Order order = orderRepository.findById(orderId).orElseThrow(NullPointerException::new);
        if (!order.getCustomer().equals(customer)) throw new AccessDeniedException("Acces denied");
        return new ResponseEntity<>(orderOrderModelConverter.convert(order), HttpStatus.OK);
    }
}
