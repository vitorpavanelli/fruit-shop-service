package com.devbox.fruitshop.controllers.vo.order.response;

import java.math.BigDecimal;
import java.util.List;

public record OrderCreationResponse(
    Long id, BigDecimal totalAmount, List<OrderLineCreationResponse> orderLines) {}
