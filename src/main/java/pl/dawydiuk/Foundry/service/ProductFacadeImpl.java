package pl.dawydiuk.Foundry.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.CreateProductRQ;
import models.ProductRS;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class ProductFacadeImpl implements ProductFascade{

    private ProductProducer productProducer;
    private  ProductSearcher productSearcher;

    @Override
    public ProductRS createProduct(List<CreateProductRQ> createProductRQ) {
        return productProducer.createProduct(createProductRQ);
    }

    @Override
    public ProductRS getAllProducts() {
        return productSearcher.getAllProducts();
    }

}
