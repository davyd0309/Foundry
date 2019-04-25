package pl.dawydiuk.Foundry.mapper;

import models.Product;
import models.dto.ProductDto;

import java.util.function.Function;

public class ProductDtoMapper {

    public static Function<Product,ProductDto> mapTo = product -> {
        ProductDto productDto = new ProductDto();
        productDto.setType(product.getName());
        productDto.setId(product.getId());
        return productDto;
    };

}
