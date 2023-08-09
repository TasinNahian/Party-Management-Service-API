package com.tmf.partymgmtservices.repository.db.model;


import com.tmf.partymgmtservices.api.individual.domain.model.*;
import com.tmf.partymgmtservices.api.organization.domain.model.OtherNameOrganization;

import java.util.List;

public interface IndividualDaoReadRepository {
    Individual getIndividual(String individualId);
    List<ContactMedium> getContactMediumForIndividual(String individualId);

    List<ContactMedium> getContactMediumForOrganization(String organizationId);

    MediumCharacteristic getMediumCharacteristic(String contactMediumId);
    ValidFor getValidFor(String individualId);
    List<CreditRating> getCreditRatingForIndividual(String individualId);

    List<CreditRating> getCreditRatingForOrganization(String organizationId);

    List<Disability> getDisabilityByIndividualId(String individualId);

    List<Disability> getDisabilityByOrganizationId(String organizationId);

    List<ExternalReference> getExternalReferenceForIndividual(String individualId);

    List<ExternalReference> getExternalReferenceForOrganization(String organizationId);

    List<IndividualOrganizationIdentification> getIndividualIdentification(String individualId);
    List<LanguageAbility> getLanguageAbilityForIndividual(String individualId);

    List<LanguageAbility> getLanguageAbilityForOrganization(String organizationId);

    List<OtherNameIndividual> getOtherNameForIndividual(String individualId);

    List<OtherNameOrganization> getOtherNameForOrganization(String organizationId);

    List<PartyCharacteristic> getPartyCharacteristicForIndividual(String individualId);

    List<PartyCharacteristic> getPartyCharacteristicForOrganization(String organizationId);

    List<RelatedParty> getRelatedPartyForIndividual(String individualId);

    List<RelatedParty> getRelatedPartyForOrganization(String organizationId);

    List<Skill> getSkillForIndividual(String individualId);

    List<Skill> getSkillForOrganization(String organizationId);

    List<TaxExemptionCertificate> getTaxExemptionCertificateForIndividual(String individualId);

    List<TaxExemptionCertificate> getTaxExemptionCertificateForOrganization(String organizationId);

    Attachment getAttachment(String id);
    Size getSize(String id);

    List<TaxDefinition> getTaxDefinition(String taxExemptionCertificateId);

    List<String> getPaginatedIndividual(Integer limit, Integer offset);
}
