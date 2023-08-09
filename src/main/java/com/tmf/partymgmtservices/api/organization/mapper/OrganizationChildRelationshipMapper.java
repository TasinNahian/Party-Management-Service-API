package com.tmf.partymgmtservices.api.organization.mapper;

import com.tmf.partymgmtservices.api.organization.domain.model.OrganizationChildRelationship;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizationChildRelationshipMapper implements RowMapper<OrganizationChildRelationship> {
    @Override
    public OrganizationChildRelationship mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrganizationChildRelationship organizationChildRelationship = new OrganizationChildRelationship();

        organizationChildRelationship.setId(resultSet.getString("id"));
        organizationChildRelationship.setRelationshipType(resultSet.getString("relationship_type"));
        organizationChildRelationship.setBaseType(resultSet.getString("base_type"));
        organizationChildRelationship.setSchemaLocation(resultSet.getString("schema_location"));
        organizationChildRelationship.setType(resultSet.getString("type"));
        organizationChildRelationship.setOrganizationId(resultSet.getString("organization_id"));
        organizationChildRelationship.setOrganizationRefId(resultSet.getString("organization_ref_id"));

        return organizationChildRelationship;
    }
}
