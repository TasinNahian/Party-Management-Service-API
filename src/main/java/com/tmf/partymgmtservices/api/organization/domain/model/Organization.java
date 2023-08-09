package com.tmf.partymgmtservices.api.organization.domain.model;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;

public class Organization extends BaseBundled {
    private String id;
    private boolean headOffice;
    private boolean legalEntity;
    private String name;
    private String nameType;
    private String organizationType;
    private String tradingName;
    private String status;
    private String organizationParentRelationshipId;
    private String existsDuringId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHeadOffice() {
        return headOffice;
    }

    public void setHeadOffice(boolean headOffice) {
        this.headOffice = headOffice;
    }

    public boolean isLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(boolean legalEntity) {
        this.legalEntity = legalEntity;
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

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrganizationParentRelationshipId() {
        return organizationParentRelationshipId;
    }

    public void setOrganizationParentRelationshipId(String organizationParentRelationshipId) {
        this.organizationParentRelationshipId = organizationParentRelationshipId;
    }

    public String getExistsDuringId() {
        return existsDuringId;
    }

    public void setExistsDuringId(String existsDuringId) {
        this.existsDuringId = existsDuringId;
    }
}
