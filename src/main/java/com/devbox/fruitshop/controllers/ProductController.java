package com.devbox.fruitshop.controllers;

import com.devbox.fruitshop.controllers.vo.product.request.creation.ProductCreationRequest;
import com.devbox.fruitshop.controllers.vo.product.request.update.ProductUpdateRequest;
import com.devbox.fruitshop.controllers.vo.product.response.creation.ProductCreationResponse;
import com.devbox.fruitshop.controllers.vo.product.response.edition.ProductUpdateResponse;
import com.devbox.fruitshop.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService service;

  public ProductController(final ProductService service) {
    this.service = service;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ProductCreationResponse createUser(
      @RequestBody @Valid final ProductCreationRequest productCreationRequest) {
    return service.createProduct(productCreationRequest);
  }

  @PatchMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ProductUpdateResponse updateUser(
      @PathVariable final Long id,
      @RequestBody @Valid final ProductUpdateRequest productUpdateRequest) {
    return service.updateProduct(id, productUpdateRequest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable final Long id) {
    service.deleteProduct(id);
  }
}
