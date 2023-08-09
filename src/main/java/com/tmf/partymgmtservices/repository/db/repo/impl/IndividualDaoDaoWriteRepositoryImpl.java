package com.tmf.partymgmtservices.repository.db.repo.impl;

import com.tmf.partymgmtservices.api.individual.domain.model.*;
import com.tmf.partymgmtservices.api.util.Defs;
import com.tmf.partymgmtservices.repository.db.model.IndividualDaoWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class IndividualDaoDaoWriteRepositoryImpl extends JdbcDaoSupport implements IndividualDaoWriteRepository {
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    private static final String INSERT_ATTACHMENT = "INSERT INTO public.attachment_ref(\n" +
            "\tid, href, attachment_type, content, description, is_ref, mime_type, name, url, size_pm_id, valid_for_id, base_type, schema_location, type, referred_type)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_CONTACT_MEDIUM = "INSERT INTO contact_medium(\n" +
            "\tid, medium_type, preferred, valid_for_id, base_type, schema_location, type, organization_id, medium_characteristic_id, individual_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_DISABILITIES = "INSERT INTO disability(\n" +
            "\tid, disability_code, disability_name, valid_for_id, base_type, schema_location, type, individual_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_EXTERNAL_REFERENCES = "INSERT INTO external_reference(\n" +
            "\tid, external_reference_type, name, base_type, schema_location, type, individual_id, organization_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_INDIVIDUAL_ORGANIZATION_IDENTIFICATIONS = "INSERT INTO individual_organization_identification(\n" +
            "\tidentification_id, identification_type, issuing_authority, issuing_date, valid_for_id, base_type, schema_location, type, attachment_ref_id, organization_id, individual_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_INDIVIDUAL_SQL = " INSERT INTO individual(id, href, aristocratic_title, birth_date, country_of_birth, death_date, family_name, family_name_prefix, formatted_name, full_name, " +
            " gender, generation, given_name, legal_name, location, marital_status, middle_name, nationality, place_of_birth, preferred_given_name, title, status, base_type, schema_location, type)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_LANGUAGE_ABILITY = "INSERT INTO language_ability(\n" +
            "\tid, is_favourite_language, language_code, language_name, listening_proficiency, reading_proficiency, speaking_proficiency, writing_proficiency, valid_for_id, base_type, schema_location, type, individual_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_MEDIUM_CHARACTERISTIC = "INSERT INTO medium_characteristic(\n" +
            "\tid, city, contact_type, country, email_address, fax_number, phone_number, post_code, social_network_id, state_or_province, street1, street2, base_type, schema_location, type)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_OTHER_NAME_INDIVIDUAL = "INSERT INTO public.other_name_individual(\n" +
            "\tid, aristocratic_title, family_name, family_name_prefix, formatted_name, full_name, generation, given_name, legal_name, middle_name, preferred_given_name, title, valid_for_id, base_type, schema_location, type, individual_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_PARTY_CHARACTERISTIC = "INSERT INTO characteristic(\n" +
            "\tid, name, value_type, value, base_type, schema_location, type, individual_id, organization_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_CREDIT_RATINGS_PARTY_CREDIT_PROFILE = "INSERT INTO party_credit_profile(\n" +
            "\tid, credit_agency_name, credit_agency_type, rating_reference, rating_score, valid_for_id, base_type, schema_location, type, organization_id, individual_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_RELATED_PARTY = "INSERT INTO related_party(\n" +
            "\tid, href, name, role, base_type, schema_location, type, referred_type, individual_id, organization_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SIZE = "INSERT INTO size_pm(\n" +
            "\tid, units, amount)\n" +
            "\tVALUES (?, ?, ?);";
    private static final String INSERT_SKILLS = "INSERT INTO skill(\n" +
            "\tid, comment, evaluated_level, skill_code, skill_name, valid_for_id, base_type, schema_location, type, individual_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_TAX_DEFINITION = "INSERT INTO tax_definition(\n" +
            "\tid, name, tax_type, base_type, schema_location, type, referred_type, tax_exemption_certificate_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_TAX_EXEMPTION_CERTIFICATE = "INSERT INTO tax_exemption_certificate(\n" +
            "\tid, valid_for_id, base_type, schema_location, type, attachment_ref_id, individual_id, organization_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_VALID_FOR_SQL = "INSERT INTO valid_for_pm(\n" +
            "\tid, start_datetime, end_datetime)\n" +
            "\tVALUES (?, ?, ?)";


    private static final String DELETE_FROM_INDIVIDUAL_BY_ID = "DELETE FROM INDIVIDUAL WHERE ID = '%s'";
    private static final String DELETE_FROM_CONTACT_MEDIUM_BY_INDIVIDUAL_ID = "DELETE FROM  CONTACT_MEDIUM WHERE INDIVIDUAL_ID = '%s'";
    private static final String DELETE_FROM_CONTACT_MEDIUM_BY_ORGANIZATION_ID = "DELETE FROM  CONTACT_MEDIUM WHERE ORGANIZATION_ID = '%s'";
    private static final String DELETE_FROM_CREDIT_RATING_BY_INDIVIDUAL_ID = "DELETE FROM  PARTY_CREDIT_PROFILE WHERE INDIVIDUAL_ID = '%s'";
    private static final String DELETE_FROM_CREDIT_RATING_BY_ORGANIZATION_ID = "DELETE FROM  PARTY_CREDIT_PROFILE WHERE ORGANIZATION_ID = '%s'";
    private static final String DELETE_FROM_DISABILITY_BY_INDIVIDUAL_ID = "DELETE FROM DISABILITY WHERE INDIVIDUAL_ID = '%s'";
    private static final String DELETE_FROM_EXTERNAL_REFERENCE_BY_INDIVIDUAL_ID = "DELETE FROM EXTERNAL_REFERENCE WHERE INDIVIDUAL_ID = '%s'";
    private static final String DELETE_FROM_EXTERNAL_REFERENCE_BY_ORGANIZATION_ID = "DELETE FROM EXTERNAL_REFERENCE WHERE ORGANIZATION_ID = '%s'";
    private static final String DELETE_FROM_INDIVIDUAL_IDENTIFICATION_BY_INDIVIDUAL_ID = "DELETE FROM INDIVIDUAL_ORGANIZATION_IDENTIFICATION WHERE INDIVIDUAL_ID = '%s'";
    private static final String DELETE_FROM_INDIVIDUAL_IDENTIFICATION_BY_ORGANIZATION_ID = "DELETE FROM INDIVIDUAL_ORGANIZATION_IDENTIFICATION WHERE ORGANIZATION_ID = '%s'";
    private static final String DELETE_FROM_LANGUAGE_ABILITY_BY_INDIVIDUAL_ID = "DELETE FROM LANGUAGE_ABILITY WHERE INDIVIDUAL_ID = '%s'";
    private static final String DELETE_FROM_OTHER_NAME_BY_INDIVIDUAL_ID = "DELETE FROM OTHER_NAME_INDIVIDUAL WHERE INDIVIDUAL_ID = '%s'";
    private static final String DELETE_FROM_OTHER_NAME_BY_ORGANIZATION_ID = "DELETE FROM OTHER_NAME_ORGANIZATION WHERE ORGANIZATION_ID = '%s'";
    private static final String DELETE_FROM_PARTY_CHARACTERISTIC_BY_INDIVIDUAL_ID = "DELETE FROM CHARACTERISTIC WHERE INDIVIDUAL_ID = '%s'";
    private static final String DELETE_FROM_PARTY_CHARACTERISTIC_BY_ORGANIZATION_ID = "DELETE FROM CHARACTERISTIC WHERE ORGANIZATION_ID = '%s'";
    private static final String DELETE_FROM_RELATED_PARTY_BY_INDIVIDUAL_ID = "DELETE FROM RELATED_PARTY WHERE INDIVIDUAL_ID = '%s'";
    private static final String DELETE_FROM_RELATED_PARTY_BY_ORGANIZATION_ID = "DELETE FROM RELATED_PARTY WHERE ORGANIZATION_ID = '%s'";
    private static final String DELETE_FROM_SKILL_BY_INDIVIDUAL_ID = "DELETE FROM SKILL WHERE INDIVIDUAL_ID = '%s'";
    private static final String DELETE_FROM_VALID_FOR_BY_ID = "DELETE FROM VALID_FOR_PM WHERE ID = '%s'";
    private static final String DELETE_FROM_ATTACHMENT_BY_ID_LIST = "DELETE FROM ATTACHMENT_REF WHERE ID IN ( '%s' )";
    private static final String DELETE_FROM_VALID_FOR_BY_ID_LIST = "DELETE FROM VALID_FOR_PM WHERE ID IN ( ";
    private static final String DELETE_FROM_MEDIUM_CHARACTERISTIC_BY_ID_LIST = "DELETE FROM MEDIUM_CHARACTERISTIC WHERE ID IN ( '%s' )";
    private static final String DELETE_FROM_TAX_DEFINITION_BY_TAX_EXEMPTION_CERTIFICATE_ID = "DELETE FROM TAX_DEFINITION WHERE TAX_EXEMPTION_CERTIFICATE_ID IN ( ";
    private static final String DELETE_FROM_TAX_EXEMPTION_CERTIFICATE_BY_INDIVIDUAL_ID = "DELETE FROM TAX_EXEMPTION_CERTIFICATE WHERE INDIVIDUAL_ID = '%s'";
    private static final String DELETE_FROM_TAX_EXEMPTION_CERTIFICATE_BY_ORGANIZATION_ID = "DELETE FROM TAX_EXEMPTION_CERTIFICATE WHERE ORGANIZATION_ID = '%s'";


    @Override
    public Boolean saveIndividual(Individual individual) {
        return Objects.requireNonNull(getJdbcTemplate()).execute(INSERT_INDIVIDUAL_SQL, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                preparedStatement.setString(1, individual.getId());
                preparedStatement.setString(2, individual.getHref());
                preparedStatement.setString(3, individual.getAristocraticTitle());
                preparedStatement.setTimestamp(4, individual.getBirthDate());
                preparedStatement.setString(5, individual.getCountryOfBirth());
                preparedStatement.setTimestamp(6, individual.getDeathDate());
                preparedStatement.setString(7, individual.getFamilyName());
                preparedStatement.setString(8, individual.getFamilyNamePrefix());
                preparedStatement.setString(9, individual.getFormattedName());
                preparedStatement.setString(10, individual.getFullName());
                preparedStatement.setString(11, individual.getGender());
                preparedStatement.setString(12, individual.getGeneration());
                preparedStatement.setString(13, individual.getGivenName());
                preparedStatement.setString(14, individual.getLegalName());
                preparedStatement.setString(15, individual.getLocation());
                preparedStatement.setString(16, individual.getMaritalStatus());
                preparedStatement.setString(17, individual.getMiddleName());
                preparedStatement.setString(18, individual.getNationality());
                preparedStatement.setString(19, individual.getPlaceOfBirth());
                preparedStatement.setString(20, individual.getPreferredGivenName());
                preparedStatement.setString(21, individual.getTitle());
                preparedStatement.setString(22, individual.getStatus());
                preparedStatement.setString(23, individual.getBaseType());
                preparedStatement.setString(24, individual.getSchemaLocation());
                preparedStatement.setString(25, individual.getType());

                return preparedStatement.execute();
            }
        });
    }

    @Override
    public int[] saveContactMedium(List<ContactMedium> contactMediumList) {
        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_CONTACT_MEDIUM, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                ContactMedium contactMedium = contactMediumList.get(i);
                preparedStatement.setString(1, contactMedium.getId());
                preparedStatement.setString(2, contactMedium.getMediumType());
                preparedStatement.setBoolean(3, contactMedium.getPreferred());
                preparedStatement.setLong(4, contactMedium.getValidForId());
                preparedStatement.setString(5, contactMedium.getBaseType());
                preparedStatement.setString(6, contactMedium.getSchemaLocation());
                preparedStatement.setString(7, contactMedium.getType());
                preparedStatement.setString(8, contactMedium.getOrganizationId()!=null? contactMedium.getOrganizationId() : null );
                preparedStatement.setString(9, contactMedium.getCharacteristicId());
                preparedStatement.setString(10, contactMedium.getIndividualId()!=null? contactMedium.getIndividualId() : null );
            }

            @Override
            public int getBatchSize() {
                return contactMediumList.size();
            }
        });
    }
    @Override
    public Boolean saveMediumCharacteristic(MediumCharacteristic mediumCharacteristic) {

        return Objects.requireNonNull(getJdbcTemplate()).execute(INSERT_MEDIUM_CHARACTERISTIC, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                preparedStatement.setString(1, mediumCharacteristic.getId());
                preparedStatement.setString(2, mediumCharacteristic.getCity());
                preparedStatement.setString(3, mediumCharacteristic.getContactType());
                preparedStatement.setString(4, mediumCharacteristic.getCountry());
                preparedStatement.setString(5, mediumCharacteristic.getEmailAddress());
                preparedStatement.setString(6, mediumCharacteristic.getFaxNumber());
                preparedStatement.setString(7, mediumCharacteristic.getPhoneNumber());
                preparedStatement.setString(8, mediumCharacteristic.getPostCode());
                preparedStatement.setString(9, mediumCharacteristic.getSocialNetworkId());
                preparedStatement.setString(10, mediumCharacteristic.getStateOrProvince());
                preparedStatement.setString(11, mediumCharacteristic.getStreet1());
                preparedStatement.setString(12, mediumCharacteristic.getStreet2());
                preparedStatement.setString(13, mediumCharacteristic.getBaseType());
                preparedStatement.setString(14, mediumCharacteristic.getSchemaLocation());
                preparedStatement.setString(15, mediumCharacteristic.getType());
                return preparedStatement.execute();
            }
        });
    }
    @Override
    public int[] savePartyCharacteristic(List<PartyCharacteristic> partyCharacteristicList) {

        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_PARTY_CHARACTERISTIC, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                PartyCharacteristic partyCharacteristic = partyCharacteristicList.get(i);

                preparedStatement.setString(1, partyCharacteristic.getId());
                preparedStatement.setString(2, partyCharacteristic.getName());
                preparedStatement.setString(3, partyCharacteristic.getValueType());
                preparedStatement.setString(4, partyCharacteristic.getValue());

                preparedStatement.setString(5, partyCharacteristic.getBaseType());
                preparedStatement.setString(6, partyCharacteristic.getSchemaLocation());
                preparedStatement.setString(7, partyCharacteristic.getType());

                preparedStatement.setString(8, partyCharacteristic.getIndividualId()!=null? partyCharacteristic.getIndividualId() : null);
                preparedStatement.setString(9, partyCharacteristic.getOrganizationId()!=null? partyCharacteristic.getOrganizationId() : null);
            }

            @Override
            public int getBatchSize() {
                return partyCharacteristicList.size();
            }
        });

    }

    @Override
    public Long getNextSequenceValue(String sequenceName) {
        String sql = "SELECT nextval('%s')";
        final SqlRowSet sqlRowSet = Objects.requireNonNull(getJdbcTemplate()).queryForRowSet(String.format(sql, sequenceName));
        sqlRowSet.next();
        return sqlRowSet.getLong(1);
    }

    @Override
    public Long saveValidFor(ValidFor validFor) {
        Long id = getNextSequenceValue(Defs.VALID_FOR_ID_SEQUENCE);

        int success = Objects.requireNonNull(getJdbcTemplate()).execute(INSERT_VALID_FOR_SQL, new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                preparedStatement.setLong(1, id);
                preparedStatement.setTimestamp(2, validFor.getStartDateTime());
                preparedStatement.setTimestamp(3, validFor.getEndDateTime());
                return preparedStatement.executeUpdate();
            }
        });
        return success > 0 ? id : -1L;
    }
    @Override
    public int[] saveCreditRating(List<CreditRating> creditRatingList) {
        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_CREDIT_RATINGS_PARTY_CREDIT_PROFILE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                CreditRating creditRating = creditRatingList.get(i);
                preparedStatement.setString(1, creditRating.getId());
                preparedStatement.setString(2, creditRating.getCreditAgencyName());
                preparedStatement.setString(3, creditRating.getCreditAgencyType());
                preparedStatement.setString(4, creditRating.getRatingReference());
                preparedStatement.setInt(5, creditRating.getRatingScore());
                preparedStatement.setLong(6, creditRating.getValidForId());
                preparedStatement.setString(7, creditRating.getBaseType());
                preparedStatement.setString(8, creditRating.getSchemaLocation());
                preparedStatement.setString(9, creditRating.getType());
                preparedStatement.setString(10, creditRating.getOrganizationId()!=null? creditRating.getOrganizationId() : null );
                preparedStatement.setString(11, creditRating.getIndividualId()!=null? creditRating.getIndividualId() : null );
            }

            @Override
            public int getBatchSize() {
                return creditRatingList.size();
            }
        });
    }

    @Override
    public int[] saveDisabilities(List<Disability> disabilityList) {
        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_DISABILITIES, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Disability disability = disabilityList.get(i);
                preparedStatement.setString(1, disability.getId());
                preparedStatement.setString(2, disability.getDisabilityCode());
                preparedStatement.setString(3, disability.getDisabilityName());
                preparedStatement.setLong(4, disability.getValidForId());
                preparedStatement.setString(5, disability.getBaseType());
                preparedStatement.setString(6, disability.getSchemaLocation());
                preparedStatement.setString(7, disability.getType());
                preparedStatement.setString(8, disability.getIndividualId()!=null? disability.getIndividualId() : null );
            }

            @Override
            public int getBatchSize() {
                return disabilityList.size();
            }
        });
    }
    @Override
    public int[] saveExternalReferences(List<ExternalReference> externalReferenceList) {

        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_EXTERNAL_REFERENCES, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                ExternalReference disability = externalReferenceList.get(i);
                preparedStatement.setString(1, disability.getId());
                preparedStatement.setString(2, disability.getExternalReferenceType());
                preparedStatement.setString(3, disability.getName());
                preparedStatement.setString(4, disability.getBaseType());
                preparedStatement.setString(5, disability.getSchemaLocation());
                preparedStatement.setString(6, disability.getType());
                preparedStatement.setString(7, disability.getIndividualId()!=null? disability.getIndividualId() : null );
                preparedStatement.setString(8, disability.getOrganizationId()!=null? disability.getOrganizationId() : null );
            }

            @Override
            public int getBatchSize() {
                return externalReferenceList.size();
            }
        });
    }

    @Override
    public String saveSize(Size size){
        Long id = getNextSequenceValue(Defs.SIZE_ID_SEQUENCE);

        int success = Objects.requireNonNull(getJdbcTemplate()).execute(INSERT_SIZE, new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                preparedStatement.setLong(1, id);
                preparedStatement.setString(2, size.getUnits());
                preparedStatement.setDouble(3, size.getAmount());
                return preparedStatement.executeUpdate();
            }
        });
        return success > 0 ? String.valueOf(id) : String.valueOf(-1L);
    }

    @Override
    public Boolean saveAttachment(Attachment attachment){
        int success =  Objects.requireNonNull(getJdbcTemplate()).execute(INSERT_ATTACHMENT, new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                preparedStatement.setString(1, attachment.getId());
                preparedStatement.setString(2, attachment.getHref());
                preparedStatement.setString(3, attachment.getAttachmentType());
                preparedStatement.setString(4, attachment.getContent());
                preparedStatement.setString(5, attachment.getDescription());
                preparedStatement.setBoolean(6, attachment.isRef());
                preparedStatement.setString(7, attachment.getMimeType());
                preparedStatement.setString(8, attachment.getName());
                preparedStatement.setString(9, attachment.getUrl());
                preparedStatement.setString(10, attachment.getSizeId());
                preparedStatement.setLong(11, attachment.getValidForId());
                preparedStatement.setString(12, attachment.getBaseType());
                preparedStatement.setString(13, attachment.getSchemaLocation());
                preparedStatement.setString(14, attachment.getType());
                preparedStatement.setString(15, attachment.getReferredType());
                return preparedStatement.executeUpdate();
            }
        });
        return success > 0 ? true : false;
    }

    @Override
    public int[] saveIndividualIdentification(List<IndividualOrganizationIdentification> individualOrganizationIdentificationList){
        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_INDIVIDUAL_ORGANIZATION_IDENTIFICATIONS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                IndividualOrganizationIdentification individualOrganizationIdentification = individualOrganizationIdentificationList.get(i);
                preparedStatement.setString(1, individualOrganizationIdentification.getIdentificationId());
                preparedStatement.setString(2, individualOrganizationIdentification.getIdentificationType());
                preparedStatement.setString(3, individualOrganizationIdentification.getIssuingAuthority());
                preparedStatement.setTimestamp(4, individualOrganizationIdentification.getIssuingDate());
                preparedStatement.setLong(5, individualOrganizationIdentification.getValidForId());
                preparedStatement.setString(6, individualOrganizationIdentification.getBaseType());
                preparedStatement.setString(7, individualOrganizationIdentification.getSchemaLocation());
                preparedStatement.setString(8, individualOrganizationIdentification.getType());
                preparedStatement.setString(9, individualOrganizationIdentification.getAttachmentId());
                preparedStatement.setString(10, individualOrganizationIdentification.getOrganizationId()!=null? individualOrganizationIdentification.getOrganizationId() : null );
                preparedStatement.setString(11, individualOrganizationIdentification.getIndividualId()!=null? individualOrganizationIdentification.getIndividualId() : null );
            }

            @Override
            public int getBatchSize() {
                return individualOrganizationIdentificationList.size();
            }
        });
    }

    @Override
    public int[] saveLanguageAbility(List<LanguageAbility> languageAbilityList){
        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_LANGUAGE_ABILITY, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                LanguageAbility languageAbility = languageAbilityList.get(i);
                preparedStatement.setString(1, languageAbility.getId());
                preparedStatement.setBoolean(2, languageAbility.isFavouriteLanguage());
                preparedStatement.setString(3, languageAbility.getLanguageCode());
                preparedStatement.setString(4, languageAbility.getLanguageName());
                preparedStatement.setString(5, languageAbility.getListeningProficiency());
                preparedStatement.setString(6, languageAbility.getReadingProficiency());
                preparedStatement.setString(7, languageAbility.getSpeakingProficiency());
                preparedStatement.setString(8, languageAbility.getWritingProficiency());
                preparedStatement.setLong(9, languageAbility.getValidForId());
                preparedStatement.setString(10, languageAbility.getBaseType());
                preparedStatement.setString(11, languageAbility.getSchemaLocation());
                preparedStatement.setString(12, languageAbility.getType());
                preparedStatement.setString(13, languageAbility.getIndividualId()!=null? languageAbility.getIndividualId() : null );
            }
            @Override
            public int getBatchSize() {
                return languageAbilityList.size();
            }
        });
    }

    @Override
    public int[] saveOtherNameIndividual(List<OtherNameIndividual> otherNameIndividualList){

        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_OTHER_NAME_INDIVIDUAL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                OtherNameIndividual otherNameIndividual = otherNameIndividualList.get(i);
                preparedStatement.setString(1, otherNameIndividual.getId());
                preparedStatement.setString(2, otherNameIndividual.getAristocraticTitle());
                preparedStatement.setString(3, otherNameIndividual.getFamilyName());
                preparedStatement.setString(4, otherNameIndividual.getFamilyNamePrefix());
                preparedStatement.setString(5, otherNameIndividual.getFormattedName());
                preparedStatement.setString(6, otherNameIndividual.getFullName());
                preparedStatement.setString(7, otherNameIndividual.getGeneration());
                preparedStatement.setString(8, otherNameIndividual.getGivenName());
                preparedStatement.setString(9, otherNameIndividual.getLegalName());
                preparedStatement.setString(10, otherNameIndividual.getMiddleName());
                preparedStatement.setString(11, otherNameIndividual.getPreferredGivenName());
                preparedStatement.setString(12, otherNameIndividual.getTitle());
                preparedStatement.setLong(13, otherNameIndividual.getValidForId());
                preparedStatement.setString(14, otherNameIndividual.getBaseType());
                preparedStatement.setString(15, otherNameIndividual.getSchemaLocation());
                preparedStatement.setString(16, otherNameIndividual.getType());
                preparedStatement.setString(17, otherNameIndividual.getIndividualId()!=null? otherNameIndividual.getIndividualId() : null );
            }
            @Override
            public int getBatchSize() {
                return otherNameIndividualList.size();
            }
        });
    }

    @Override
    public int[] saveRelatedPartyList(List<RelatedParty> relatedPartyList){
        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_RELATED_PARTY, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                RelatedParty relatedParty = relatedPartyList.get(i);
                preparedStatement.setString(1, relatedParty.getId());
                preparedStatement.setString(2, relatedParty.getHref());
                preparedStatement.setString(3, relatedParty.getName());
                preparedStatement.setString(4, relatedParty.getRole());
                preparedStatement.setString(5, relatedParty.getBaseType());
                preparedStatement.setString(6, relatedParty.getSchemaLocation());
                preparedStatement.setString(7, relatedParty.getType());
                preparedStatement.setString(8, relatedParty.getReferredType());
                preparedStatement.setString(9, relatedParty.getIndividualId()!=null? relatedParty.getIndividualId() : null );
                preparedStatement.setString(10, relatedParty.getOrganizationId()!=null? relatedParty.getOrganizationId() : null );
            }

            @Override
            public int getBatchSize() {
                return relatedPartyList.size();
            }
        });
    }
    @Override
    public int[] saveSkills(List<Skill> skillList){

        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_SKILLS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Skill skill = skillList.get(i);
                preparedStatement.setString(1, skill.getId());
                preparedStatement.setString(2, skill.getComment());
                preparedStatement.setString(3, skill.getEvaluatedLevel());
                preparedStatement.setString(4, skill.getSkillCode());
                preparedStatement.setString(5, skill.getSkillName());
                preparedStatement.setLong(6, skill.getValidForId());
                preparedStatement.setString(7, skill.getBaseType());
                preparedStatement.setString(8, skill.getSchemaLocation());
                preparedStatement.setString(9, skill.getType());
                preparedStatement.setString(10, skill.getIndividualId()!=null? skill.getIndividualId() : null );
            }

            @Override
            public int getBatchSize() {
                return skillList.size();
            }
        });
    }

    @Override
    public int[] saveTaxExemptionCertificate(List<TaxExemptionCertificate> taxExemptionCertificateList){
        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_TAX_EXEMPTION_CERTIFICATE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                TaxExemptionCertificate taxExemptionCertificate = taxExemptionCertificateList.get(i);
                preparedStatement.setString(1, taxExemptionCertificate.getId());
                preparedStatement.setLong(2, taxExemptionCertificate.getValidForId());
                preparedStatement.setString(3, taxExemptionCertificate.getBaseType());
                preparedStatement.setString(4, taxExemptionCertificate.getSchemaLocation());
                preparedStatement.setString(5, taxExemptionCertificate.getType());
                preparedStatement.setString(6, taxExemptionCertificate.getAttachmentId());
                preparedStatement.setString(7, taxExemptionCertificate.getIndividualId()!=null? taxExemptionCertificate.getIndividualId() : null );
                preparedStatement.setString(8, taxExemptionCertificate.getOrganizationId()!=null? taxExemptionCertificate.getOrganizationId() : null );
            }
            @Override
            public int getBatchSize() {
                return taxExemptionCertificateList.size();
            }
        });
    }

    @Override
    public int[] saveTaxDefinition(List<TaxDefinition> taxDefinitionList) {
        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_TAX_DEFINITION, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                TaxDefinition taxDefinition = taxDefinitionList.get(i);
                preparedStatement.setString(1, taxDefinition.getId());
                preparedStatement.setString(2, taxDefinition.getName());
                preparedStatement.setString(3, taxDefinition.getTaxType());
                preparedStatement.setString(4, taxDefinition.getBaseType());
                preparedStatement.setString(5, taxDefinition.getSchemaLocation());
                preparedStatement.setString(6, taxDefinition.getType());
                preparedStatement.setString(7, taxDefinition.getReferredType());
                preparedStatement.setString(8, taxDefinition.getTaxExemptionCertificateId());
            }
            @Override
            public int getBatchSize() {
                return taxDefinitionList.size();
            }
        });

    }

    @Override
    public Boolean deleteIndividualById(String individualId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_INDIVIDUAL_BY_ID, individualId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteMediumCharacteristicByIdList(List<String> mediumCharacteristicIdList){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_MEDIUM_CHARACTERISTIC_BY_ID_LIST, String.join(",", mediumCharacteristicIdList)));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteTaxExemptionCertificateByIndividualId(String individualId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_TAX_EXEMPTION_CERTIFICATE_BY_INDIVIDUAL_ID, individualId));
        return success > 0 ? true : false;
    }
    @Override
    public Boolean deleteTaxExemptionCertificateByOrganizationId(String organizationId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_TAX_EXEMPTION_CERTIFICATE_BY_ORGANIZATION_ID, organizationId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteContactMediumByIndividualId(String individualId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_CONTACT_MEDIUM_BY_INDIVIDUAL_ID, individualId));
        return success > 0 ? true : false;
    }
    @Override
    public Boolean deleteContactMediumByOrganizationId(String organizationId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_CONTACT_MEDIUM_BY_ORGANIZATION_ID, organizationId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteCreditRatingByIndividualId(String individualId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_CREDIT_RATING_BY_INDIVIDUAL_ID, individualId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteCreditRatingByOrganizationId(String organizationId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_CREDIT_RATING_BY_ORGANIZATION_ID, organizationId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteDisabilityByIndividualId(String individualId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_DISABILITY_BY_INDIVIDUAL_ID, individualId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteExternalReferenceByIndividualId(String individualId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_EXTERNAL_REFERENCE_BY_INDIVIDUAL_ID, individualId));
        return success > 0 ? true : false;
    }
    @Override
    public Boolean deleteExternalReferenceByOrganizationId(String organizationId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_EXTERNAL_REFERENCE_BY_ORGANIZATION_ID, organizationId));

        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteIndividualIdentificationByIndividualId(String individualId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_INDIVIDUAL_IDENTIFICATION_BY_INDIVIDUAL_ID, individualId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteIndividualIdentificationByOrganizationId(String organizationId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_INDIVIDUAL_IDENTIFICATION_BY_ORGANIZATION_ID, organizationId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteLanguageAbilityByIndividualId(String individualId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_LANGUAGE_ABILITY_BY_INDIVIDUAL_ID, individualId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteOtherNameByIndividualId(String individualId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_OTHER_NAME_BY_INDIVIDUAL_ID, individualId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteOtherNameByOrganizationId(String organizationId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_OTHER_NAME_BY_ORGANIZATION_ID, organizationId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deletePartyCharacteristicByIndividualId(String individualId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_PARTY_CHARACTERISTIC_BY_INDIVIDUAL_ID, individualId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deletePartyCharacteristicByOrganizationId(String organizationId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_PARTY_CHARACTERISTIC_BY_ORGANIZATION_ID, organizationId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteRelatedPartyByIndividualId(String individualId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_RELATED_PARTY_BY_INDIVIDUAL_ID, individualId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteSkillByIndividualId(String individualId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_SKILL_BY_INDIVIDUAL_ID, individualId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteRelatedPartyByOrganizationId(String organizationId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_RELATED_PARTY_BY_ORGANIZATION_ID, organizationId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteAttachmentByIdList(List<String> idList){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_ATTACHMENT_BY_ID_LIST, String.join(",", idList)));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteValidForByIdList(List<String> idList){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(DELETE_FROM_VALID_FOR_BY_ID_LIST+String.join(",", idList)+" )");
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteValidForById(String id){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_VALID_FOR_BY_ID, id));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteTaxDefinitionById(List<String> idList){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_TAX_DEFINITION_BY_TAX_EXEMPTION_CERTIFICATE_ID+ String.join(",", idList)+" )"));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean updateIndividualById(String id, Map<String, String> map){
        String updateSql = "UPDATE INDIVIDUAL SET ";
        for (Map.Entry<String, String> entry : map.entrySet()){
            updateSql += entry.getKey()+" = "+entry.getValue()+", ";
        }
        updateSql = updateSql.substring(0, updateSql.length()-2);
        updateSql += " WHERE ID = '"+id+"'";

        int success = Objects.requireNonNull(getJdbcTemplate()).update(updateSql);
        return success > 0 ? true : false;
    }
}
