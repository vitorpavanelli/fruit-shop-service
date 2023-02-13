package com.devbox.fruitshop.controllers.vo.order.request;


import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderCreationRequest(@NotEmpty List<OrderLineCreationRequest> orderLines) {}
