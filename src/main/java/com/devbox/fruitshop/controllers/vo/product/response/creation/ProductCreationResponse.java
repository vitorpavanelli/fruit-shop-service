package com.devbox.fruitshop.controllers.vo.product.response.creation;


import java.math.BigDecimal;

public record ProductCreationResponse(Long id, String name, BigDecimal price) {}
