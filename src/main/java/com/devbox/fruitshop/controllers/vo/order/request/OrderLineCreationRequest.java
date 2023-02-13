package com.devbox.fruitshop.controllers.vo.order.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderLineCreationRequest(@NotNull Long productId, @NotNull BigDecimal amount) {}
