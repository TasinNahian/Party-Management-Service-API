package com.tmf.partymgmtservices.api.individual.domain.model;

import java.io.Serializable;

public class ContactMedium extends BaseBundled implements Serializable {
    private String id;
    private String mediumType;
    private Boolean preferred;
    private String characteristicId;
    private Long  validForId;
    private String organizationId;
    private String individualId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediumType() {
        return mediumType;
    }

    public void setMediumType(String mediumType) {
        this.mediumType = mediumType;
    }

    public Boolean getPreferred() {
        return preferred;
    }

    public void setPreferred(Boolean preferred) {
        this.preferred = preferred;
    }

    public String getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(String characteristicId) {
        this.characteristicId = characteristicId;
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

    public String getIndividualId() {
        return individualId;
    }

    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }
}
