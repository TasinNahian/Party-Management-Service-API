package com.tmf.partymgmtservices.api.organization.domain.model;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;

import java.io.Serializable;

public class OtherNameOrganization extends BaseBundled implements Serializable {
    private String id;
    private String name;
    private String nameType;
    private String tradingName;
    private Long validForId;
    private String organizationId;

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

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public Long getValidForId() {
        return validForId;
    }

    public void setValidForId(Long validForId) {
        this.validForId = validForId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}
