package com.tmf.partymgmtservices.repository.db.model;

import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;
import com.tmf.partymgmtservices.api.organization.domain.model.*;

import java.util.List;
import java.util.Map;


public interface OrganizationDaoWriteRepository {
    Boolean saveOrganization(Organization organization);
    Long getNextSequenceValue(String sequenceName);
    Long saveValidFor(ValidFor validFor);
    String saveOrganizationRef(OrganizationRef organizationRef);
    String saveOrganizationParentRelationship(OrganizationParentRelationship parentRelationship);
    int[] saveOtherNameOrganization(List<OtherNameOrganization> otherNameOrganizationList);
    int[] saveOrganizationChildRelationshipRequest(List<OrganizationChildRelationship> contactMediumList);
    Boolean deleteChildRelationshipByOrganizationId(String organizationId);
    Boolean deleteParentRelationshipByOrganizationId(String organizationId);
    Boolean updateOrganizationById(String id, Map<String, String> map);
    Boolean deleteOrganizationById(String organizationId);
}
