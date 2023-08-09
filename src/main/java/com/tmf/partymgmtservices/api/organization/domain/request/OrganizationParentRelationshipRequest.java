package com.tmf.partymgmtservices.api.organization.domain.request;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;
import com.tmf.partymgmtservices.api.organization.domain.model.OrganizationRef;

import java.io.Serializable;

public class OrganizationParentRelationshipRequest extends BaseBundled implements Serializable {
    private String id;
    private String relationshipType;
    private OrganizationRef organization;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public OrganizationRef getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationRef organization) {
        this.organization = organization;
    }

}
