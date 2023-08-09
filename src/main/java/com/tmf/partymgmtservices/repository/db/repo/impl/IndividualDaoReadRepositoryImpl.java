package com.tmf.partymgmtservices.repository.db.repo.impl;


import com.tmf.partymgmtservices.api.individual.domain.model.*;
import com.tmf.partymgmtservices.api.individual.mapper.*;
import com.tmf.partymgmtservices.api.organization.domain.model.OtherNameOrganization;
import com.tmf.partymgmtservices.api.organization.mapper.OtherNameOrganizationMapper;
import com.tmf.partymgmtservices.repository.db.model.IndividualDaoReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

@Repository
public class IndividualDaoReadRepositoryImpl extends JdbcDaoSupport implements IndividualDaoReadRepository {
    private static final String SELECT_ATTACHMENT_REF_SQL = "SELECT * FROM ATTACHMENT_REF WHERE ID='%s'";

    private static final String SELECT_CONTACT_MEDIUM_INDIVIDUAL_SQL = "SELECT * FROM CONTACT_MEDIUM WHERE INDIVIDUAL_ID='%s'";
    private static final String SELECT_CONTACT_MEDIUM_ORGANIZATION_SQL = "SELECT * FROM CONTACT_MEDIUM WHERE ORGANIZATION_ID='%s'";

    private static final String SELECT_DISABILITY_FOR_INDIVIDUAL_SQL = "SELECT * FROM DISABILITY WHERE INDIVIDUAL_ID='%s'";
    private static final String SELECT_DISABILITY_FOR_ORGANIZATION_SQL = "SELECT * FROM DISABILITY WHERE ORGANIZATION_ID='%s'";


    private static final String SELECT_EXTERNAL_REFERENCE_INDIVIDUAL_SQL = "SELECT * FROM EXTERNAL_REFERENCE WHERE INDIVIDUAL_ID='%s'";
    private static final String SELECT_EXTERNAL_REFERENCE_ORGANIZATION_SQL = "SELECT * FROM EXTERNAL_REFERENCE WHERE ORGANIZATION_ID='%s'";

    private static final String SELECT_INDIVIDUAL_IDENTIFICATION_SQL = "SELECT * FROM INDIVIDUAL_ORGANIZATION_IDENTIFICATION WHERE INDIVIDUAL_ID='%s'";


    private static final String SELECT_INDIVIDUAL_SQL = "SELECT * FROM INDIVIDUAL WHERE ID='%s'";
    private static final String SELECT_LANGUAGE_ABILITY_FOR_INDIVIDUAL_SQL = "SELECT * FROM LANGUAGE_ABILITY WHERE INDIVIDUAL_ID='%s'";
    private static final String SELECT_LANGUAGE_ABILITY_FOR_ORGANIZATION_SQL = "SELECT * FROM LANGUAGE_ABILITY WHERE INDIVIDUAL_ID='%s'";


    private static final String SELECT_MEDIUM_CHARACTERISTIC_SQL = "SELECT * FROM MEDIUM_CHARACTERISTIC WHERE ID='%s'";
    private static final String SELECT_OTHER_NAME_INDIVIDUAL_SQL = "SELECT * FROM OTHER_NAME_INDIVIDUAL WHERE INDIVIDUAL_ID='%s'";
    private static final String SELECT_OTHER_NAME_ORGANIZATION_SQL = "SELECT * FROM OTHER_NAME_ORGANIZATION WHERE ORGANIZATION_ID='%s'";


    private static final String SELECT_PARTY_CHARACTERISTIC_INDIVIDUAL_SQL = "SELECT * FROM CHARACTERISTIC WHERE INDIVIDUAL_ID='%s'";
    private static final String SELECT_PARTY_CHARACTERISTIC_ORGANIZATION_SQL = "SELECT * FROM CHARACTERISTIC WHERE ORGANIZATION_ID='%s'";

    private static final String SELECT_PARTY_CREDIT_PROFILE_INDIVIDUAL_SQL = "SELECT * FROM PARTY_CREDIT_PROFILE WHERE INDIVIDUAL_ID='%s'";
    private static final String SELECT_PARTY_CREDIT_PROFILE_ORGANIZATION_SQL = "SELECT * FROM PARTY_CREDIT_PROFILE WHERE ORGANIZATION_ID='%s'";

    private static final String SELECT_RELATED_PARTY_INDIVIDUAL_SQL = "SELECT * FROM RELATED_PARTY WHERE INDIVIDUAL_ID='%s'";
    private static final String SELECT_RELATED_PARTY_ORGANIZATION_SQL = "SELECT * FROM RELATED_PARTY WHERE ORGANIZATION_ID='%s'";


    private static final String SELECT_SIZE_PM_SQL = "SELECT * FROM SIZE_PM WHERE ID='%s'";
    private static final String SELECT_SKILL_FOR_INDIVIDUAL_SQL = "SELECT * FROM SKILL WHERE INDIVIDUAL_ID='%s'";
    private static final String SELECT_SKILL_FOR_ORGANIZATION_SQL = "SELECT * FROM SKILL WHERE ORGANIZATION_ID='%s'";


    private static final String SELECT_TAX_DEFINITION_SQL = "SELECT * FROM TAX_DEFINITION WHERE TAX_EXEMPTION_CERTIFICATE_ID='%s'";

    private static final String SELECT_TAX_EXEMPTION_CERTIFICATE_INDIVIDUAL_SQL = "SELECT * FROM TAX_EXEMPTION_CERTIFICATE WHERE INDIVIDUAL_ID='%s'";
    private static final String SELECT_TAX_EXEMPTION_CERTIFICATE_ORGANIZATION_SQL = "SELECT * FROM TAX_EXEMPTION_CERTIFICATE WHERE ORGANIZATION_ID='%s'";

    private static final String SELECT_VALID_FOR_PM_SQL = "SELECT * FROM VALID_FOR_PM WHERE ID='%s'";

    private static final String SELECT_INDIVIDUAL_WITH_LIMIT_OFFSET_SQL = "SELECT ID FROM INDIVIDUAL ORDER BY ID";



    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public Individual getIndividual(String individualId) {
        List<Individual> individualList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_INDIVIDUAL_SQL, individualId), new IndividualMapper());
        return individualList.isEmpty() ? null : individualList.get(0);
    }

    @Override
    public List<ContactMedium> getContactMediumForIndividual(String individualId) {
        List<ContactMedium> contactMediumList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_CONTACT_MEDIUM_INDIVIDUAL_SQL, individualId), new ContactMediumMapper());

        return contactMediumList.isEmpty() ? null : contactMediumList;
    }
    @Override
    public List<ContactMedium> getContactMediumForOrganization(String organizationId) {
        List<ContactMedium> contactMediumList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_CONTACT_MEDIUM_ORGANIZATION_SQL, organizationId), new ContactMediumMapper());

        return contactMediumList.isEmpty() ? null : contactMediumList;
    }

    @Override
    public MediumCharacteristic getMediumCharacteristic(String contactMediumId) {
        List<MediumCharacteristic> mediumCharacteristicList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_MEDIUM_CHARACTERISTIC_SQL, contactMediumId), new MediumCharacteristicMapper());
        return mediumCharacteristicList.isEmpty() ? null : mediumCharacteristicList.get(0);
    }

    @Override
    public ValidFor getValidFor(String organizationId) {
        List<ValidFor> validForList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_VALID_FOR_PM_SQL, organizationId), new ValidForMapper());

        return validForList.isEmpty() ? null : validForList.get(0);
    }

    @Override
    public List<CreditRating> getCreditRatingForIndividual(String individualId) {
        List<CreditRating> creditRatingList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_PARTY_CREDIT_PROFILE_INDIVIDUAL_SQL, individualId), new CreditRatingMapper());
        return creditRatingList.isEmpty() ? null : creditRatingList;
    }

    @Override
    public List<CreditRating> getCreditRatingForOrganization(String organizationId) {
        List<CreditRating> creditRatingList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_PARTY_CREDIT_PROFILE_ORGANIZATION_SQL, organizationId), new CreditRatingMapper());
        return creditRatingList.isEmpty() ? null : creditRatingList;
    }

    @Override
    public List<Disability> getDisabilityByIndividualId(String individualId) {
        List<Disability> disabilityList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_DISABILITY_FOR_INDIVIDUAL_SQL, individualId), new DisabilityMapper());
        return disabilityList.isEmpty() ? null : disabilityList;
    }

    @Override
    public List<Disability> getDisabilityByOrganizationId(String organizationId) {
        List<Disability> disabilityList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_DISABILITY_FOR_ORGANIZATION_SQL, organizationId), new DisabilityMapper());
        return disabilityList.isEmpty() ? null : disabilityList;
    }

    @Override
    public List<ExternalReference> getExternalReferenceForIndividual(String individualId) {
        List<ExternalReference> externalReferenceList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_EXTERNAL_REFERENCE_INDIVIDUAL_SQL, individualId), new ExternalReferenceMapper());

        return externalReferenceList.isEmpty() ? null : externalReferenceList;
    }
    @Override
    public List<ExternalReference> getExternalReferenceForOrganization(String organizationId) {
        List<ExternalReference> externalReferenceList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_EXTERNAL_REFERENCE_ORGANIZATION_SQL, organizationId), new ExternalReferenceMapper());

        return externalReferenceList.isEmpty() ? null : externalReferenceList;
    }

    @Override
    public List<IndividualOrganizationIdentification> getIndividualIdentification(String individualId) {
        List<IndividualOrganizationIdentification> individualOrganizationIdentificationList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_INDIVIDUAL_IDENTIFICATION_SQL, individualId), new IndividualIdentificationMapper());

        return individualOrganizationIdentificationList.isEmpty() ? null : individualOrganizationIdentificationList;
    }

    @Override
    public List<LanguageAbility> getLanguageAbilityForIndividual(String individualId) {
        List<LanguageAbility> languageAbilityList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_LANGUAGE_ABILITY_FOR_INDIVIDUAL_SQL, individualId), new LanguageAbilityMapper());

        return languageAbilityList.isEmpty() ? null : languageAbilityList;
    }
    @Override
    public List<LanguageAbility> getLanguageAbilityForOrganization(String organizationId) {
        List<LanguageAbility> languageAbilityList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_LANGUAGE_ABILITY_FOR_ORGANIZATION_SQL, organizationId), new LanguageAbilityMapper());

        return languageAbilityList.isEmpty() ? null : languageAbilityList;
    }

    @Override
    public List<OtherNameIndividual> getOtherNameForIndividual(String individualId) {
        List<OtherNameIndividual> otherNameIndividualList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_OTHER_NAME_INDIVIDUAL_SQL, individualId), new OtherNameIndividualMapper());

        return otherNameIndividualList.isEmpty() ? null : otherNameIndividualList;
    }

    @Override
    public List<OtherNameOrganization> getOtherNameForOrganization(String organizationId) {
        List<OtherNameOrganization> otherNameOrganizationList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_OTHER_NAME_ORGANIZATION_SQL, organizationId), new OtherNameOrganizationMapper());

        return otherNameOrganizationList.isEmpty() ? null : otherNameOrganizationList;
    }

    @Override
    public List<PartyCharacteristic> getPartyCharacteristicForIndividual(String individualId) {

        List<PartyCharacteristic> partyCharacteristicList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_PARTY_CHARACTERISTIC_INDIVIDUAL_SQL, individualId), new PartyCharacteristicMapper());

        return partyCharacteristicList.isEmpty() ? null : partyCharacteristicList;
    }

    @Override
    public List<PartyCharacteristic> getPartyCharacteristicForOrganization(String organizationId) {

        List<PartyCharacteristic> partyCharacteristicList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_PARTY_CHARACTERISTIC_ORGANIZATION_SQL, organizationId), new PartyCharacteristicMapper());

        return partyCharacteristicList.isEmpty() ? null : partyCharacteristicList;
    }

    @Override
    public List<RelatedParty> getRelatedPartyForIndividual(String individualId) {
        List<RelatedParty> relatedPartyList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_RELATED_PARTY_INDIVIDUAL_SQL, individualId), new RelatedPartyMapper());
        return relatedPartyList.isEmpty() ? null : relatedPartyList;
    }

    @Override
    public List<RelatedParty> getRelatedPartyForOrganization(String organizationId) {
        List<RelatedParty> relatedPartyList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_RELATED_PARTY_ORGANIZATION_SQL, organizationId), new RelatedPartyMapper());
        return relatedPartyList.isEmpty() ? null : relatedPartyList;
    }

    @Override
    public List<Skill> getSkillForIndividual(String individualId) {
        List<Skill> skillList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_SKILL_FOR_INDIVIDUAL_SQL, individualId), new SkillsMapper());
        return skillList.isEmpty() ? null : skillList;
    }
    @Override
    public List<Skill> getSkillForOrganization(String organizationId) {
        List<Skill> skillList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_SKILL_FOR_ORGANIZATION_SQL, organizationId), new SkillsMapper());
        return skillList.isEmpty() ? null : skillList;
    }

    @Override
    public List<TaxExemptionCertificate> getTaxExemptionCertificateForIndividual(String individualId) {
        List<TaxExemptionCertificate> taxExemptionCertificateList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_TAX_EXEMPTION_CERTIFICATE_INDIVIDUAL_SQL, individualId), new TaxExemptionCertificateMapper());

        return taxExemptionCertificateList.isEmpty() ? null : taxExemptionCertificateList;
    }

    @Override
    public List<TaxExemptionCertificate> getTaxExemptionCertificateForOrganization(String organizationId) {
        List<TaxExemptionCertificate> taxExemptionCertificateList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_TAX_EXEMPTION_CERTIFICATE_ORGANIZATION_SQL, organizationId), new TaxExemptionCertificateMapper());

        return taxExemptionCertificateList.isEmpty() ? null : taxExemptionCertificateList;
    }

    @Override
    public Attachment getAttachment(String organizationId) {
        List<Attachment> attachmentList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_ATTACHMENT_REF_SQL, organizationId), new AttachmentMapper());

        return attachmentList.isEmpty() ? null : attachmentList.get(0);
    }

    @Override
    public Size getSize(String sizeId) {
        List<Size> sizeList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_SIZE_PM_SQL, sizeId), new SizeMapper());
        return sizeList.isEmpty() ? null : sizeList.get(0);
    }

    @Override
    public List<TaxDefinition> getTaxDefinition(String taxExemptionCertificateId){
        List<TaxDefinition> taxDefinitionList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_TAX_DEFINITION_SQL, taxExemptionCertificateId), new TaxDefinitionMapper());

        return taxDefinitionList.isEmpty() ? null : taxDefinitionList;
    }

    @Override
    public List<String> getPaginatedIndividual(Integer limit, Integer offset){

        return Objects.requireNonNull(getJdbcTemplate()).queryForList(
                SELECT_INDIVIDUAL_WITH_LIMIT_OFFSET_SQL+" LIMIT "+limit+ " OFFSET "+offset, String.class);
    }

}
