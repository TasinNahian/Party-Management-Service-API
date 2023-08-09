package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.ExternalReference;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExternalReferenceMapper implements RowMapper<ExternalReference> {
    @Override
    public ExternalReference mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ExternalReference externalReference = new ExternalReference();

        externalReference.setId(resultSet.getString("id"));
        externalReference.setExternalReferenceType(resultSet.getString("external_reference_type"));
        externalReference.setName(resultSet.getString("name"));

        externalReference.setBaseType(resultSet.getString("base_type"));
        externalReference.setSchemaLocation(resultSet.getString("schema_location"));
        externalReference.setType(resultSet.getString("type"));

        externalReference.setIndividualId(resultSet.getString("individual_id"));
        externalReference.setOrganizationId(resultSet.getString("organization_id"));

        return externalReference;
    }
}
