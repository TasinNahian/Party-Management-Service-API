package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidForMapper implements RowMapper<ValidFor> {
    @Override
    public ValidFor mapRow(ResultSet rs, int rowNum) throws SQLException {
        ValidFor validFor = new ValidFor();
        validFor.setId(rs.getLong("id"));
        validFor.setStartDateTime(rs.getTimestamp("start_datetime"));
        validFor.setEndDateTime(rs.getTimestamp("end_datetime"));

        return validFor;
    }
}
