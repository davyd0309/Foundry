package pl.dawydiuk.Foundry.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Judith on 09.12.2018.
 */
public class Product {

    private Integer id;
    private String name;
    private Address address;
    private List<Employee> employees;
    private RawMaterials usedRawMaterials;
    private BigDecimal price;


}
