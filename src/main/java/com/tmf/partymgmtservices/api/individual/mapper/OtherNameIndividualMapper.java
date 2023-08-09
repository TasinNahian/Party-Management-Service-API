package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.OtherNameIndividual;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OtherNameIndividualMapper implements RowMapper<OtherNameIndividual> {
    @Override
    public OtherNameIndividual mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OtherNameIndividual otherNameIndividual = new OtherNameIndividual();

        otherNameIndividual.setId(resultSet.getString("id"));
        otherNameIndividual.setAristocraticTitle(resultSet.getString("aristocratic_title"));
        otherNameIndividual.setFamilyName(resultSet.getString("family_name"));
        otherNameIndividual.setFamilyNamePrefix(resultSet.getString("family_name_prefix"));
        otherNameIndividual.setFormattedName(resultSet.getString("formatted_name"));
        otherNameIndividual.setFullName(resultSet.getString("full_name"));
        otherNameIndividual.setGeneration(resultSet.getString("generation"));
        otherNameIndividual.setGivenName(resultSet.getString("given_name"));
        otherNameIndividual.setLegalName(resultSet.getString("legal_name"));
        otherNameIndividual.setMiddleName(resultSet.getString("middle_name"));
        otherNameIndividual.setPreferredGivenName(resultSet.getString("preferred_given_name"));
        otherNameIndividual.setTitle(resultSet.getString("title"));
        otherNameIndividual.setValidForId(resultSet.getLong("valid_for_id"));

        otherNameIndividual.setBaseType(resultSet.getString("base_type"));
        otherNameIndividual.setSchemaLocation(resultSet.getString("schema_location"));
        otherNameIndividual.setType(resultSet.getString("type"));

        otherNameIndividual.setId(resultSet.getString("individual_id"));

        return otherNameIndividual;
    }
}
