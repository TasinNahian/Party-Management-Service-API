package com.tmf.partymgmtservices.repository.db.model;

import com.tmf.partymgmtservices.api.individual.domain.model.*;

import java.util.List;
import java.util.Map;

public interface IndividualDaoWriteRepository {
    Boolean saveIndividual(Individual individual);
    int[] saveContactMedium(List<ContactMedium> contactMediumList);
    Boolean saveMediumCharacteristic(MediumCharacteristic mediumCharacteristic);
    int[] savePartyCharacteristic(List<PartyCharacteristic> partyCharacteristicList);
    Long getNextSequenceValue(String sequenceName);
    Long saveValidFor(ValidFor validFor);
    int[] saveCreditRating(List<CreditRating> creditRatingList);
    int[] saveDisabilities(List<Disability> disabilityList);
    int[] saveExternalReferences(List<ExternalReference> externalReferenceList);
    String saveSize(Size size);
    Boolean saveAttachment(Attachment attachment);
    int[] saveIndividualIdentification(List<IndividualOrganizationIdentification> individualOrganizationIdentificationList);
    int[] saveLanguageAbility(List<LanguageAbility> languageAbilityList);
    int[] saveOtherNameIndividual(List<OtherNameIndividual> otherNameIndividualList);
    int[] saveRelatedPartyList(List<RelatedParty> relatedPartyList);
    int[] saveSkills(List<Skill> skillList);
    int[] saveTaxExemptionCertificate(List<TaxExemptionCertificate> taxExemptionCertificateList);
    int[] saveTaxDefinition(List<TaxDefinition> taxDefinitionList);
    Boolean deleteIndividualById(String individualId);
    Boolean deleteMediumCharacteristicByIdList(List<String> mediumCharacteristicIdList);
    Boolean deleteTaxExemptionCertificateByIndividualId(String individualId);
    Boolean deleteTaxExemptionCertificateByOrganizationId(String organizationId);
    Boolean deleteContactMediumByIndividualId(String individualId);
    Boolean deleteContactMediumByOrganizationId(String organizationId);
    Boolean deleteCreditRatingByIndividualId(String individualId);
    Boolean deleteCreditRatingByOrganizationId(String organizationId);
    Boolean deleteDisabilityByIndividualId(String individualId);
    Boolean deleteExternalReferenceByIndividualId(String individualId);
    Boolean deleteExternalReferenceByOrganizationId(String organizationId);
    Boolean deleteIndividualIdentificationByIndividualId(String individualId);
    Boolean deleteIndividualIdentificationByOrganizationId(String organizationId);
    Boolean deleteLanguageAbilityByIndividualId(String individualId);
    Boolean deleteOtherNameByIndividualId(String individualId);
    Boolean deleteOtherNameByOrganizationId(String organizationId);
    Boolean deletePartyCharacteristicByIndividualId(String individualId);
    Boolean deletePartyCharacteristicByOrganizationId(String organizationId);
    Boolean deleteRelatedPartyByIndividualId(String individualId);
    Boolean deleteSkillByIndividualId(String individualId);
    Boolean deleteRelatedPartyByOrganizationId(String organizationId);
    Boolean deleteAttachmentByIdList(List<String> idList);
    Boolean deleteValidForByIdList(List<String> idList);
    Boolean deleteValidForById(String id);
    Boolean deleteTaxDefinitionById(List<String> idList);
    Boolean updateIndividualById(String id, Map<String, String> map);
}
