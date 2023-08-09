package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.Individual;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IndividualMapper implements RowMapper<Individual> {
    @Override
    public Individual mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Individual individual = new Individual();

        individual.setId(resultSet.getString("id"));
        individual.setHref(resultSet.getString("href"));
        individual.setAristocraticTitle(resultSet.getString("aristocratic_title"));
        individual.setBirthDate(resultSet.getTimestamp("birth_date"));
        individual.setCountryOfBirth(resultSet.getString("country_of_birth"));
        individual.setDeathDate(resultSet.getTimestamp("death_date"));
        individual.setFamilyName(resultSet.getString("family_name"));
        individual.setFamilyNamePrefix(resultSet.getString("family_name_prefix"));
        individual.setFormattedName(resultSet.getString("formatted_name"));
        individual.setFullName(resultSet.getString("full_name"));
        individual.setGender(resultSet.getString("gender"));
        individual.setGeneration(resultSet.getString("generation"));
        individual.setGivenName(resultSet.getString("given_name"));
        individual.setLegalName(resultSet.getString("legal_name"));
        individual.setLocation(resultSet.getString("location"));
        individual.setMaritalStatus(resultSet.getString("marital_status"));
        individual.setMiddleName(resultSet.getString("middle_name"));
        individual.setNationality(resultSet.getString("nationality"));
        individual.setPlaceOfBirth(resultSet.getString("place_of_birth"));
        individual.setPreferredGivenName(resultSet.getString("preferred_given_name"));
        individual.setTitle(resultSet.getString("title"));
        individual.setStatus(resultSet.getString("status"));

        individual.setBaseType(resultSet.getString("base_type"));
        individual.setSchemaLocation(resultSet.getString("schema_location"));
        individual.setType(resultSet.getString("type"));

        return individual;
    }
}
