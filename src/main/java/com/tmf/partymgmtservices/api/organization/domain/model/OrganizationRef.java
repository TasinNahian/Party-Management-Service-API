package com.tmf.partymgmtservices.api.organization.domain.model;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class OrganizationRef extends BaseBundled implements Serializable {
    private String id;
    private String href;
    private String name;
    @JsonProperty("@referredType")
    private String referredType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getReferredType() {
        return referredType;
    }

    public void setReferredType(String referredType) {
        this.referredType = referredType;
    }
}
