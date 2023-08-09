package com.tmf.partymgmtservices.api.organization.mapper;

import com.tmf.partymgmtservices.api.organization.domain.model.Organization;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizationMapper implements RowMapper<Organization> {
    @Override
    public Organization mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Organization organization = new Organization();

        organization.setId(resultSet.getString("id"));
        organization.setHeadOffice(resultSet.getBoolean("is_head_office"));
        organization.setLegalEntity(resultSet.getBoolean("is_legal_entity"));
        organization.setName(resultSet.getString("name"));
        organization.setNameType(resultSet.getString("name_type"));
        organization.setOrganizationType(resultSet.getString("organization_type"));
        organization.setTradingName(resultSet.getString("trading_name"));
        organization.setStatus(resultSet.getString("status"));
        organization.setBaseType(resultSet.getString("base_type"));
        organization.setSchemaLocation(resultSet.getString("schema_location"));
        organization.setType(resultSet.getString("type"));
        organization.setOrganizationParentRelationshipId(resultSet.getString("organization_parent_relationship_id"));
        organization.setExistsDuringId(resultSet.getString("exists_during_id"));


        return organization;
    }
}
