package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.RelatedParty;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RelatedPartyMapper implements RowMapper<RelatedParty> {
    @Override
    public RelatedParty mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        RelatedParty relatedParty = new RelatedParty();
        relatedParty.setId(resultSet.getString("id"));
        relatedParty.setHref(resultSet.getString("href"));
        relatedParty.setName(resultSet.getString("name"));
        relatedParty.setRole(resultSet.getString("role"));

        relatedParty.setBaseType(resultSet.getString("base_type"));
        relatedParty.setSchemaLocation(resultSet.getString("schema_location"));
        relatedParty.setType(resultSet.getString("type"));
        relatedParty.setReferredType(resultSet.getString("referred_type"));

        relatedParty.setIndividualId(resultSet.getString("individual_id"));
        relatedParty.setOrganizationId(resultSet.getString("organization_id"));
        return relatedParty;    }
}
