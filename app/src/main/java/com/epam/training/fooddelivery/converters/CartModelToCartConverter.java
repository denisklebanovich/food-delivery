package com.epam.training.fooddelivery.converters;

import com.epam.training.fooddelivery.domain.Cart;
import com.epam.training.fooddelivery.domain.OrderItem;
import com.epam.training.fooddelivery.model.CartModel;
import com.epam.training.fooddelivery.model.OrderItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartModelToCartConverter implements Converter<CartModel, Cart> {

    @Autowired
    Converter<OrderItemModel,OrderItem> converter;

    @Override
    public Cart convert(CartModel source) {
        Cart cart = new Cart();
        cart.setPrice(BigDecimal.valueOf(source.getPrice()));
        List<OrderItem> orderItems = new ArrayList<>();
        source.getOrderItemModels().forEach(orderItemModel -> orderItems.add(converter.convert(orderItemModel)));
        cart.setOrderItems(orderItems);
        return cart;
    }
}
