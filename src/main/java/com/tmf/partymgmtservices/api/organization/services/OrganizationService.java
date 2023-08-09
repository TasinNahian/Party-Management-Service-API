package com.tmf.partymgmtservices.api.organization.services;

import com.tmf.partymgmtservices.api.organization.domain.request.OrganizationRequest;
import com.tmf.partymgmtservices.api.organization.domain.response.OrganizationResponse;

import java.util.List;
import java.util.Map;

public interface OrganizationService {
    OrganizationResponse saveOrganization(OrganizationRequest organizationRequest) throws Exception;
    OrganizationResponse getOrganizationById(String id) throws Exception;
    List<OrganizationResponse> getPaginatedOrganization(Map<String, Object> requestParams, Integer limit, Integer offset) throws Exception;
    String deleteOrganization(String organizationId) throws Exception;
    OrganizationResponse updateOrganization(String organizationId, OrganizationRequest organizationRequest) throws Exception;
}
