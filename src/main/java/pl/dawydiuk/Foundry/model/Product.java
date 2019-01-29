package pl.dawydiuk.Foundry.model;

import java.time.LocalDateTime;

/**
 * Created by Judith on 09.12.2018.
 */

public class Product {

    private Integer id;
    private String name;
    private Employee foundryEmployee;
    private LocalDateTime foundryDate;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getFoundryEmployee() {
        return foundryEmployee;
    }

    public void setFoundryEmployee(Employee foundryEmployee) {
        this.foundryEmployee = foundryEmployee;
    }

    public LocalDateTime getFoundryDate() {
        return foundryDate;
    }

    public void setFoundryDate(LocalDateTime foundryDate) {
        this.foundryDate = foundryDate;
    }
}
