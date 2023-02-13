package com.devbox.fruitshop.controllers.vo.order.request;


import java.util.List;

public record OrderCreationRequest(List<OrderLineCreationRequest> orderLines) {}
