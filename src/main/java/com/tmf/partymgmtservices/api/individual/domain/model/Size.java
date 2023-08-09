package com.tmf.partymgmtservices.api.individual.domain.model;

import java.io.Serializable;

public class Size implements Serializable {
    private Long id;
    private Double amount;
    private String units;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
