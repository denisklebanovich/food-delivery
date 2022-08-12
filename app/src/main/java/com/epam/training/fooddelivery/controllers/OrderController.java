package com.epam.training.fooddelivery.controllers;

import com.epam.training.fooddelivery.api.OrderserviceApi;
import com.epam.training.fooddelivery.config.SecurityConfig;
import com.epam.training.fooddelivery.domain.Cart;
import com.epam.training.fooddelivery.domain.Order;
import com.epam.training.fooddelivery.model.CartModel;
import com.epam.training.fooddelivery.model.OrderModel;
import com.epam.training.fooddelivery.repository.OrderRepository;
import com.epam.training.fooddelivery.service.LowBalanceException;
import com.epam.training.fooddelivery.service.OrderService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            Cart cart = cartModelCartConverter.convert(cartModel);
            SecurityConfig.AUTHENTICATED_CUSTOMER.setCart(cart);
            Order order = orderService.createOrder(SecurityConfig.AUTHENTICATED_CUSTOMER);
            OrderModel orderModel = orderOrderModelConverter.convert(order);
            return new ResponseEntity<>(orderModel,HttpStatus.OK);
        }catch (LowBalanceException|IllegalStateException ex){
            return ResponseEntity.status(400).build();
        }
    }

    @Override
    public ResponseEntity<List<OrderModel>> getCustomerOrders() {
        List<Order> orders = orderRepository.findByCustomer(SecurityConfig.AUTHENTICATED_CUSTOMER);
        List<OrderModel> orderModels = new ArrayList<>();
        orders.forEach(order -> orderModels.add(orderOrderModelConverter.convert(order)));
        return new ResponseEntity<>(orderModels,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderModel> getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) return ResponseEntity.status(404).build();
        if (!order.getCustomer().getId().equals(SecurityConfig.AUTHENTICATED_CUSTOMER.getId())) return ResponseEntity.status(403).build();
        return new ResponseEntity<>(orderOrderModelConverter.convert(order), HttpStatus.OK);
    }
}
