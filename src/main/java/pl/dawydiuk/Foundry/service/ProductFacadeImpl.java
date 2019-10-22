package pl.dawydiuk.Foundry.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.ProductCreateRQ;
import models.ProductRS;

@AllArgsConstructor
@Slf4j
public class ProductFacadeImpl implements ProductFascade{

    private ProductProducer productProducer;
    private  ProductSearcher productSearcher;

    @Override
    public ProductRS createProduct(ProductCreateRQ productCreateRQ) {
        return productProducer.createProduct(productCreateRQ);
    }

    @Override
    public ProductRS getAllProducts() {
        return productSearcher.getAllProducts();
    }

}
