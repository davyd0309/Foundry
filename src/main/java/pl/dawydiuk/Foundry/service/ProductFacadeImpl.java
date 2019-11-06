package pl.dawydiuk.Foundry.service;

import lombok.extern.slf4j.Slf4j;
import models.ProductCreateRQ;
import models.ProductRS;


@Slf4j
public class ProductFacadeImpl implements ProductFascade{

    private ProductProducer productProducer;
    private  ProductSearcher productSearcher;

    public ProductFacadeImpl(ProductProducer productProducer, ProductSearcher productSearcher) {
        this.productProducer = productProducer;
        this.productSearcher = productSearcher;
    }

    @Override
    public ProductRS createProduct(ProductCreateRQ productCreateRQ) {
        return productProducer.createProduct(productCreateRQ);
    }

    @Override
    public ProductRS getAllProducts() {
        return productSearcher.getAllProducts();
    }

}
