package com.epam.training.fooddelivery.converters;

import com.epam.training.fooddelivery.domain.Order;
import com.epam.training.fooddelivery.domain.OrderItem;
import com.epam.training.fooddelivery.model.OrderItemModel;
import com.epam.training.fooddelivery.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderToOrderModelConverter implements Converter<Order, OrderModel> {

    @Autowired
    Converter<OrderItem,OrderItemModel> orderItemOrderItemModelConverter;

    @Override
    public OrderModel convert(Order source) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(source.getId());
        List<OrderItemModel> orderItemModels = new ArrayList<>();
        source.getOrderItems().forEach(orderItem -> orderItemModels.add(orderItemOrderItemModelConverter.convert(orderItem)));
        orderModel.setOrderItemModels(orderItemModels);
        orderModel.setPrice(source.getPrice().longValue());
        orderModel.setTimestampCreated(source.getTimestampCreated());
        return orderModel;
    }
}
