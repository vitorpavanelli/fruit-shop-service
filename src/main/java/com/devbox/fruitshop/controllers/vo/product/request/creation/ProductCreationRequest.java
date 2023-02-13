package com.devbox.fruitshop.controllers.vo.product.request.creation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductCreationRequest(@NotBlank String name, @NotNull BigDecimal price) {}
