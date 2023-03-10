package com.devbox.fruitshop.services;

import static com.devbox.fruitshop.constants.LogConstant.ORDER;
import static com.devbox.fruitshop.constants.LogConstant.Service.DEBUG_CREATING;
import static com.devbox.fruitshop.constants.LogConstant.Service.INFO_CREATING;
import static com.devbox.fruitshop.constants.LogConstant.Service.INFO_DELETING;

import com.devbox.fruitshop.controllers.vo.order.request.OrderCreationRequest;
import com.devbox.fruitshop.controllers.vo.order.response.OrderCreationResponse;
import com.devbox.fruitshop.exceptions.ProductNotFoundException;
import com.devbox.fruitshop.repositories.OrderRepository;
import com.devbox.fruitshop.repositories.ProductRepository;
import com.devbox.fruitshop.repositories.models.Order;
import com.devbox.fruitshop.repositories.models.OrderLine;
import com.devbox.fruitshop.repositories.models.Product;
import com.devbox.fruitshop.services.mappers.OrderMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class OrderService {
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
  private final OrderRepository repository;
  private final ProductRepository productRepository;
  private final OrderMapper mapper;

  public OrderService(
      final OrderRepository repository,
      final ProductRepository productRepository,
      final OrderMapper mapper) {
    this.repository = repository;
    this.productRepository = productRepository;
    this.mapper = mapper;
  }

  public OrderCreationResponse createOrder(final OrderCreationRequest orderCreationRequest) {
    LOGGER.info(INFO_CREATING, ORDER);
    LOGGER.debug(DEBUG_CREATING, ORDER, orderCreationRequest);

    final var missingProducts = new ArrayList<String>();
    final var order = buildOrder(orderCreationRequest, missingProducts);
    validateProduct(missingProducts);

    repository.save(order);

    return mapper.toOrderCreationResponse(order);
  }

  public void deleteOrder(final Long id) {
    LOGGER.info(INFO_DELETING, ORDER, id);
    repository.deleteById(id);
  }

  private Order buildOrder(
      final OrderCreationRequest orderCreationRequest, final List<String> missingProducts) {
    final var order = new Order();
    for (final var item : orderCreationRequest.orderLines()) {
      final var optionalProduct = productRepository.findById(item.productId());
      if (optionalProduct.isEmpty()) {
        missingProducts.add(item.productId().toString());

      } else {
        final var product = optionalProduct.get();
        addOrderLine(order, product, item.amount());
      }
    }

    return order;
  }

  private void addOrderLine(final Order order, final Product product, final BigDecimal amount) {
    final var orderLine = new OrderLine();
    orderLine.setOrder(order);
    orderLine.setProduct(product);
    orderLine.setPrice(amount.multiply(product.getPrice()));
    orderLine.setAmount(amount);

    order.getOrderLines().add(orderLine);
    order.setTotalAmount(order.getTotalAmount().add(orderLine.getPrice()));
  }

  private void validateProduct(final List<String> ids) {
    // this validation is not really necessary, but I added only to have something to double-check
    // the product existence
    // in h2 db for this sample application. It can be improved to something better.
    if (!CollectionUtils.isEmpty(ids)) {
      final var idStringList = ids.stream().map(String::valueOf).toList();
      throw new ProductNotFoundException(idStringList);
    }
  }
}
