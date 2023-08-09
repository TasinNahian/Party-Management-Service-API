package com.tmf.partymgmtservices.api.individual.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class PartyCharacteristic extends BaseBundled implements Serializable {
    private String id;
    private String name;
    private String valueType;
    private String value;
    @JsonIgnore
    private String organizationId;
    @JsonIgnore
    private String individualId;

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

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
