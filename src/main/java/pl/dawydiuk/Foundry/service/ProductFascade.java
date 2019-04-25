package pl.dawydiuk.Foundry.service;

import models.CreateProductRQ;
import models.ProductRS;

import java.util.List;


public interface ProductFascade {

    ProductRS createProduct(final List<CreateProductRQ> createProductRQ);
    ProductRS getAllProducts();
}
