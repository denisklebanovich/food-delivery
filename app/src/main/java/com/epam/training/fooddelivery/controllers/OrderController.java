package com.epam.training.fooddelivery.controllers;

import com.epam.training.fooddelivery.api.OrderserviceApi;
import com.epam.training.fooddelivery.domain.Cart;
import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.domain.Order;
import com.epam.training.fooddelivery.model.CartModel;
import com.epam.training.fooddelivery.model.OrderModel;
import com.epam.training.fooddelivery.repository.OrderRepository;
import com.epam.training.fooddelivery.service.LowBalanceException;
import com.epam.training.fooddelivery.service.OrderService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController implements OrderserviceApi {

    private final OrderRepository orderRepository;

    private final OrderService orderService;

    private final Converter<Order,OrderModel> orderOrderModelConverter;

    private final Converter<CartModel, Cart> cartModelCartConverter;

    public OrderController(OrderRepository orderRepository, OrderService orderService, Converter<Order, OrderModel> orderOrderModelConverter, Converter<CartModel, Cart> cartModelCartConverter) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.orderOrderModelConverter = orderOrderModelConverter;
        this.cartModelCartConverter = cartModelCartConverter;
    }

    @Override
    public ResponseEntity<OrderModel> createOrder(CartModel cartModel) {
        try {
            Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Cart cart = cartModelCartConverter.convert(cartModel);
            customer.setCart(cart);
            Order order = orderService.createOrder(customer);
            OrderModel orderModel = orderOrderModelConverter.convert(order);
            return new ResponseEntity<>(orderModel,HttpStatus.OK);
        }catch (LowBalanceException|IllegalStateException ex){
            return ResponseEntity.status(400).build();
        }
    }

    @Override
    public ResponseEntity<List<OrderModel>> getCustomerOrders() {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orders = orderRepository.findByCustomer(customer);
        List<OrderModel> orderModels = new ArrayList<>();
        orders.forEach(order -> orderModels.add(orderOrderModelConverter.convert(order)));
        return new ResponseEntity<>(orderModels,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderModel> getOrderById(Long orderId) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) return ResponseEntity.status(404).build();
        if (!order.getCustomer().getId().equals(customer.getId())) return ResponseEntity.status(403).build();
        return new ResponseEntity<>(orderOrderModelConverter.convert(order), HttpStatus.OK);
    }
}
