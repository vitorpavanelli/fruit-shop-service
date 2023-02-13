package com.devbox.fruitshop.services.mappers;

import com.devbox.fruitshop.controllers.vo.product.request.creation.ProductCreationRequest;
import com.devbox.fruitshop.controllers.vo.product.response.creation.ProductCreationResponse;
import com.devbox.fruitshop.controllers.vo.product.response.edition.ProductUpdateResponse;
import com.devbox.fruitshop.repositories.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  Product toProduct(final ProductCreationRequest productCreationRequest);

  ProductCreationResponse toProductCreationResponse(final Product product);

  ProductUpdateResponse toProductUpdateResponse(final Product product);
}
