package com.tmf.partymgmtservices.api.organization.domain.model;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;

public class OrganizationParentRelationship extends BaseBundled {
    private String id;
    private String relationshipType;
    private String organizationRefId;

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

    public String getOrganizationRefId() {
        return organizationRefId;
    }

    public void setOrganizationRefId(String organizationRefId) {
        this.organizationRefId = organizationRefId;
    }

}
