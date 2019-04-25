package pl.dawydiuk.Foundry.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.ProductRS;
import pl.dawydiuk.Foundry.mapper.ProductDtoMapper;
import pl.dawydiuk.Foundry.repository.ProductDao;

@AllArgsConstructor
@Slf4j
public class ProductSearcher {

    private ProductDao productDao;

    public ProductRS getAllProducts() {
        ProductRS productRS = new ProductRS();
        productDao.getAll().forEach(product -> {
            productRS.getProductsList().add(ProductDtoMapper.mapTo.apply(product));
        });
        return productRS;
    }
}
