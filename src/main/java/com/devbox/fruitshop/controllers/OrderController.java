package com.devbox.fruitshop.controllers;

import com.devbox.fruitshop.controllers.vo.order.request.OrderCreationRequest;
import com.devbox.fruitshop.controllers.vo.order.response.OrderCreationResponse;
import com.devbox.fruitshop.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService service;

  public OrderController(final OrderService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderCreationResponse createAuthor(
      @RequestBody final OrderCreationRequest orderCreationRequest) {
    return service.createOrder(orderCreationRequest);
  }

  @DeleteMapping("/{id}")
  public void deleteAuthor(@PathVariable final Long id) {
    service.deleteOrder(id);
  }
}
