package com.devbox.fruitshop.controllers.vo.order.response;

import java.math.BigDecimal;

public record OrderLineCreationResponse(OrderProductCreationResponse product, BigDecimal amount, BigDecimal price) {}
