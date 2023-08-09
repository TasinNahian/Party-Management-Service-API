package com.tmf.partymgmtservices.api.organization.mapper;

import com.tmf.partymgmtservices.api.organization.domain.model.OrganizationRef;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizationRefMapper implements RowMapper<OrganizationRef> {
    @Override
    public OrganizationRef mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrganizationRef organizationRef = new OrganizationRef();


        organizationRef.setId(resultSet.getString("id"));
        organizationRef.setHref(resultSet.getString("href"));
        organizationRef.setName(resultSet.getString("name"));

        organizationRef.setBaseType(resultSet.getString("base_type"));
        organizationRef.setSchemaLocation(resultSet.getString("schema_location"));
        organizationRef.setType(resultSet.getString("type"));

        organizationRef.setReferredType(resultSet.getString("referred_type"));


        return organizationRef;
    }
}
