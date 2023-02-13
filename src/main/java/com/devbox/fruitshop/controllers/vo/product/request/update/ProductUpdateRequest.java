package com.devbox.fruitshop.controllers.vo.product.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductUpdateRequest(@NotBlank String name, @NotNull BigDecimal price) {}
