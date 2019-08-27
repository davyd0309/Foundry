package pl.dawydiuk.Foundry.service;

import models.ProductRQ;
import models.ProductRS;

import java.util.List;


public interface ProductFascade {

    ProductRS createProduct(final List<ProductRQ> productRQ);
    ProductRS getAllProducts();
}
