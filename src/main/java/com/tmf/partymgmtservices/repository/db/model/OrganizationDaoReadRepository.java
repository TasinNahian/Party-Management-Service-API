package com.tmf.partymgmtservices.repository.db.model;


import com.tmf.partymgmtservices.api.individual.domain.model.IndividualOrganizationIdentification;
import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;
import com.tmf.partymgmtservices.api.organization.domain.model.*;

import java.util.List;

public interface OrganizationDaoReadRepository {
    Organization getOrganization(String organizationId);
    ValidFor getValidFor(String individualId);
    List<OtherNameOrganization> getOtherNameOrganization(String organizationId);


    List<String> getPaginatedOrganization(Integer limit, Integer offset);
    OrganizationParentRelationship getOrganizationParentRelationship(String id);

    OrganizationRef getOrganizationRef(String id);

    List<OrganizationChildRelationship> getOrganizationChildRelationship(String id);

    List<IndividualOrganizationIdentification> getOrganizationIdentification(String organizationId);
}
