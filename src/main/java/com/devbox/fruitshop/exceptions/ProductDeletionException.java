package com.devbox.fruitshop.exceptions;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.devbox.fruitshop.exceptions.model.ProblemDetailBuilder;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class ProductDeletionException extends ErrorResponseException {
  public ProductDeletionException(final Long id) {
    super(INTERNAL_SERVER_ERROR, getProblemDetail(id), null);
  }

  private static ProblemDetail getProblemDetail(final Long id) {
    return new ProblemDetailBuilder(INTERNAL_SERVER_ERROR)
        .withDefaultInfo()
        .withTitle("Product cannot be deleted")
        .withType("http://localhost:8080/errors/internal-server-error")
        .withDetail(
            "Product with id %s cannot be deleted due to being associated to an order"
                .formatted(id))
        .build();
  }
}
