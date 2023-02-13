package com.devbox.fruitshop.controllers.vo.order.request;

import java.math.BigDecimal;

public record OrderLineCreationRequest(Long product, BigDecimal amount) {}
