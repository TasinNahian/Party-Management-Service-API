package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.Disability;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DisabilityMapper implements RowMapper<Disability> {
    @Override
    public Disability mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Disability disability = new Disability();

        disability.setId(resultSet.getString("id"));
        disability.setDisabilityCode(resultSet.getString("disability_code"));
        disability.setValidForId(resultSet.getLong("valid_for_id"));
        disability.setDisabilityName(resultSet.getString("disability_name"));

        disability.setBaseType(resultSet.getString("base_type"));
        disability.setSchemaLocation(resultSet.getString("schema_location"));
        disability.setType(resultSet.getString("type"));

        disability.setType(resultSet.getString("individual_id"));

        return disability;
    }
}
