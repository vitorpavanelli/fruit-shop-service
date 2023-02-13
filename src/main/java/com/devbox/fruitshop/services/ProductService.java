package com.devbox.fruitshop.services;

import static com.devbox.fruitshop.constants.LogConstant.PRODUCT;
import static com.devbox.fruitshop.constants.LogConstant.Service.DEBUG_CREATING;
import static com.devbox.fruitshop.constants.LogConstant.Service.DEBUG_UPDATING;
import static com.devbox.fruitshop.constants.LogConstant.Service.INFO_CREATING;
import static com.devbox.fruitshop.constants.LogConstant.Service.INFO_DELETING;
import static com.devbox.fruitshop.constants.LogConstant.Service.INFO_UPDATING;

import com.devbox.fruitshop.controllers.vo.product.request.creation.ProductCreationRequest;
import com.devbox.fruitshop.controllers.vo.product.request.update.ProductUpdateRequest;
import com.devbox.fruitshop.controllers.vo.product.response.creation.ProductCreationResponse;
import com.devbox.fruitshop.controllers.vo.product.response.edition.ProductUpdateResponse;
import com.devbox.fruitshop.exceptions.ProductDeletionException;
import com.devbox.fruitshop.exceptions.ProductNotFoundException;
import com.devbox.fruitshop.repositories.ProductRepository;
import com.devbox.fruitshop.repositories.models.Product;
import com.devbox.fruitshop.services.mappers.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

  private final ProductRepository repository;
  private final ProductMapper mapper;

  public ProductService(final ProductRepository repository, final ProductMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public ProductCreationResponse createProduct(
      final ProductCreationRequest productCreationRequest) {
    LOGGER.info(INFO_CREATING, PRODUCT);
    LOGGER.debug(DEBUG_CREATING, PRODUCT, productCreationRequest);

    final var product = mapper.toProduct(productCreationRequest);

    repository.save(product);

    return mapper.toProductCreationResponse(product);
  }

  public ProductUpdateResponse updateProduct(
      final Long id, final ProductUpdateRequest productUpdateRequest) {
    LOGGER.info(INFO_UPDATING, PRODUCT, id);
    LOGGER.debug(DEBUG_UPDATING, PRODUCT, id, productUpdateRequest);

    final var product = findProduct(id);
    product.setName(productUpdateRequest.name());
    product.setPrice(productUpdateRequest.price());

    repository.save(product);

    return mapper.toProductUpdateResponse(product);
  }

  public void deleteProduct(final Long id) {
    LOGGER.info(INFO_DELETING, PRODUCT, id);
    final var product = findProduct(id);
    if (product.getOrderLines().size() > 0) {
      throw new ProductDeletionException(id);
    }

    repository.deleteById(id);
  }

  private Product findProduct(final Long id) {
    return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
  }
}
