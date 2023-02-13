package com.devbox.fruitshop.services.mappers;

import com.devbox.fruitshop.controllers.vo.order.response.OrderCreationResponse;
import com.devbox.fruitshop.repositories.models.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
  OrderCreationResponse toOrderCreationResponse(final Order order);
}
