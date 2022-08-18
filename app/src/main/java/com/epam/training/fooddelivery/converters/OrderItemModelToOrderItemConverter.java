package com.epam.training.fooddelivery.converters;

import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.domain.OrderItem;
import com.epam.training.fooddelivery.model.FoodModel;
import com.epam.training.fooddelivery.model.OrderItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderItemModelToOrderItemConverter implements Converter<OrderItemModel, OrderItem> {

    @Autowired
    Converter<FoodModel, Food> foodModelFoodConverter;

    @Override
    public OrderItem convert(OrderItemModel source) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(source.getId());
        orderItem.setFood(foodModelFoodConverter.convert(source.getFoodModel()));
        orderItem.setPieces(source.getPieces());
        orderItem.setPrice(BigDecimal.valueOf(source.getPrice()));
        return orderItem;
    }
}
