package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.CreditRating;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditRatingMapper implements RowMapper<CreditRating> {
    @Override
    public CreditRating mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CreditRating creditRating = new CreditRating();

        creditRating.setId(resultSet.getString("id"));
        creditRating.setCreditAgencyName(resultSet.getString("credit_agency_name"));
        creditRating.setCreditAgencyType(resultSet.getString("credit_agency_type"));
        creditRating.setRatingReference(resultSet.getString("rating_reference"));
        creditRating.setRatingScore(resultSet.getInt("rating_score"));
        creditRating.setValidForId(resultSet.getLong("valid_for_id"));

        creditRating.setBaseType(resultSet.getString("base_type"));
        creditRating.setSchemaLocation(resultSet.getString("schema_location"));
        creditRating.setType(resultSet.getString("type"));

        creditRating.setOrganizationId(resultSet.getString("organization_id"));
        creditRating.setIndividualId(resultSet.getString("individual_id"));

        return creditRating;
    }
}
