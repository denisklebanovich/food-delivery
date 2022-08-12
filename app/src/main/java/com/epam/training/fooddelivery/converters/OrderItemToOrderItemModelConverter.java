package com.epam.training.fooddelivery.converters;

import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.domain.OrderItem;
import com.epam.training.fooddelivery.model.FoodModel;
import com.epam.training.fooddelivery.model.OrderItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderItemToOrderItemModelConverter implements Converter<OrderItem, OrderItemModel> {

    @Autowired
    Converter<Food,FoodModel> foodToFoodModelConverter;

    @Override
    public OrderItemModel convert(OrderItem source) {
        OrderItemModel orderItemModel = new OrderItemModel();
        orderItemModel.setId(source.getId());
        orderItemModel.setFoodModel(foodToFoodModelConverter.convert(source.getFood()));
        orderItemModel.setPieces(source.getPieces());
        orderItemModel.setPrice(source.getPrice());
        return orderItemModel;
    }
}
