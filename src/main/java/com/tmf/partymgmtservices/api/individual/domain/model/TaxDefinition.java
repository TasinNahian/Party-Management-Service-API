package com.tmf.partymgmtservices.api.individual.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TaxDefinition extends BaseBundled implements Serializable {
     private String id;
     private String name;
     private String taxType;
    @JsonProperty("@referredType")
    private String referredType;

    @JsonIgnore
    private String taxExemptionCertificateId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public String getReferredType() {
        return referredType;
    }

    public void setReferredType(String referredType) {
        this.referredType = referredType;
    }

    public String getTaxExemptionCertificateId() {
        return taxExemptionCertificateId;
    }

    public void setTaxExemptionCertificateId(String taxExemptionCertificateId) {
        this.taxExemptionCertificateId = taxExemptionCertificateId;
    }
}
