package com.tmf.partymgmtservices.api.organization.mapper;

import com.tmf.partymgmtservices.api.organization.domain.model.OtherNameOrganization;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OtherNameOrganizationMapper implements RowMapper<OtherNameOrganization> {
    @Override
    public OtherNameOrganization mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OtherNameOrganization otherNameIndividual = new OtherNameOrganization();

        otherNameIndividual.setId(resultSet.getString("id"));
        otherNameIndividual.setName(resultSet.getString("name"));
        otherNameIndividual.setNameType(resultSet.getString("name_type"));
        otherNameIndividual.setTradingName(resultSet.getString("trading_name"));
        otherNameIndividual.setValidForId(resultSet.getLong("valid_for_id"));
        otherNameIndividual.setBaseType(resultSet.getString("base_type"));
        otherNameIndividual.setSchemaLocation(resultSet.getString("schema_location"));
        otherNameIndividual.setType(resultSet.getString("type"));
        otherNameIndividual.setId(resultSet.getString("organization_id"));

        return otherNameIndividual;
    }
}
