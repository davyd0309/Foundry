package pl.dawydiuk.Foundry.service;

import models.ProductCreateRQ;
import models.ProductRS;


public interface ProductFascade {

    ProductRS createProduct(ProductCreateRQ productCreateRQ);
    ProductRS getAllProducts();
}
