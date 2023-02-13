package com.devbox.fruitshop.controllers.vo.order.response;

import java.math.BigDecimal;

public record OrderLineCreationResponse(String productName, BigDecimal amount, BigDecimal price) {}
