package com.tmf.partymgmtservices.api.individual.services;

import com.tmf.partymgmtservices.api.individual.domain.model.ExternalReference;
import com.tmf.partymgmtservices.api.individual.domain.model.PartyCharacteristic;
import com.tmf.partymgmtservices.api.individual.domain.model.RelatedParty;
import com.tmf.partymgmtservices.api.individual.domain.request.*;
import com.tmf.partymgmtservices.api.individual.domain.response.IndividualResponse;

import java.util.List;
import java.util.Map;

public interface IndividualService {
    IndividualResponse saveIndividual(IndividualRequest individualRequest) throws Exception;
    IndividualResponse updateIndividual(String id, IndividualRequest individualRequest) throws Exception;
    void saveOtherNames(List<OtherNameIndividualRequest> otherNameIndividualRequestList, String individualId, String organizationId);
    void savePartyCharacteristics(List<PartyCharacteristic> partyCharacteristicList, String individualId, String organizationId);
    void saveRelatedParties(List<RelatedParty> relatedPartyList, String individualId, String organizationId);
    void saveTaxExemptionCertificates(List<TaxExemptionCertificateRequest> taxExemptionCertificateRequestList, String individualId, String organizationId);
    IndividualResponse getIndividualById(String id) throws Exception;
    List<IndividualResponse> getPaginatedIndividual(Map<String, Object> requestParams, Integer limit, Integer offset) throws Exception;
    void saveContactMedium(List<ContactMediumRequest> contactMediumRequestList, String individualId, String organizationId);
    void saveCreditRatings(List<CreditRatingRequest> creditRatingRequestList, String individualId, String organizationId);
    void saveExternalReferences(List<ExternalReference> externalReferenceList, String individualId, String organizationId);
    void saveIndividualOrganizationIdentifications(List<IndividualOrganizationIdentificationRequest> individualOrganizationIdentificationRequestList, String individualId, String organizationId);
    String deleteIndividual(String individualId) throws Exception;
}
