package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.ContactMedium;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMediumMapper implements RowMapper<ContactMedium> {
    @Override
    public ContactMedium mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ContactMedium contactMedium = new ContactMedium();
        contactMedium.setId(resultSet.getString("id"));
        contactMedium.setMediumType(resultSet.getString("medium_type"));
        contactMedium.setPreferred(resultSet.getBoolean("preferred"));
        contactMedium.setValidForId(resultSet.getLong("valid_for_id"));

        contactMedium.setBaseType(resultSet.getString("base_type"));
        contactMedium.setSchemaLocation(resultSet.getString("schema_location"));
        contactMedium.setType(resultSet.getString("type"));

        contactMedium.setOrganizationId(resultSet.getString("organization_id"));
        contactMedium.setCharacteristicId(resultSet.getString("medium_characteristic_id"));
        contactMedium.setIndividualId(resultSet.getString("individual_id"));
        return contactMedium;
    }
}
