package com.devbox.fruitshop.services;

import static com.devbox.fruitshop.constants.LogConstant.ORDER;
import static com.devbox.fruitshop.constants.LogConstant.Service.DEBUG_CREATING;
import static com.devbox.fruitshop.constants.LogConstant.Service.INFO_CREATING;
import static com.devbox.fruitshop.constants.LogConstant.Service.INFO_DELETING;

import com.devbox.fruitshop.controllers.vo.order.request.OrderCreationRequest;
import com.devbox.fruitshop.controllers.vo.order.response.OrderCreationResponse;
import com.devbox.fruitshop.repositories.OrderRepository;
import com.devbox.fruitshop.services.mappers.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
  private final OrderRepository repository;
  private final OrderMapper mapper;

  public OrderService(final OrderRepository repository, final OrderMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public OrderCreationResponse createOrder(final OrderCreationRequest orderCreationRequest) {
    LOGGER.info(INFO_CREATING, ORDER);
    LOGGER.debug(DEBUG_CREATING, ORDER, orderCreationRequest);

    return null;
  }

  public void deleteOrder(final Long id) {
    LOGGER.info(INFO_DELETING, ORDER, id);
    repository.deleteById(id);
  }
}
