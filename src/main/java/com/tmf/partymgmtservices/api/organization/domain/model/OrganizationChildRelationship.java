package com.tmf.partymgmtservices.api.organization.domain.model;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;

public class OrganizationChildRelationship extends BaseBundled {
    private String id;
    private String relationshipType;
    private String organizationRefId;
    private String organizationId;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

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
