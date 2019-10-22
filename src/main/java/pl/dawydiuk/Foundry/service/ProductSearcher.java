package pl.dawydiuk.Foundry.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import models.ProductRS;
import models.dto.ProductDto;
import pl.dawydiuk.Foundry.repository.ProductDao;

@AllArgsConstructor
@Slf4j
public class ProductSearcher {

    private ProductDao productDao;

    public ProductRS getAllProducts() {
        ProductRS productRS = new ProductRS();
        productDao.getAll().forEach(product -> productRS.getProductsList().add(mapToDto(product)));
        return productRS;
    }

    private ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setType(product.getName());
        productDto.setId(product.getId());
        return productDto;
    }

}
