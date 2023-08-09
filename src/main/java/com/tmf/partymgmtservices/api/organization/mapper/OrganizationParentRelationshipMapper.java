package com.tmf.partymgmtservices.api.organization.mapper;

import com.tmf.partymgmtservices.api.organization.domain.model.OrganizationParentRelationship;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizationParentRelationshipMapper implements RowMapper<OrganizationParentRelationship> {
    @Override
    public OrganizationParentRelationship mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrganizationParentRelationship organizationParentRelationship = new OrganizationParentRelationship();

        organizationParentRelationship.setId(resultSet.getString("id"));
        organizationParentRelationship.setRelationshipType(resultSet.getString("relationship_type"));
        organizationParentRelationship.setBaseType(resultSet.getString("base_type"));
        organizationParentRelationship.setSchemaLocation(resultSet.getString("schema_location"));
        organizationParentRelationship.setType(resultSet.getString("type"));
        organizationParentRelationship.setOrganizationRefId(resultSet.getString("organization_ref_id"));

        return organizationParentRelationship;
    }
}
