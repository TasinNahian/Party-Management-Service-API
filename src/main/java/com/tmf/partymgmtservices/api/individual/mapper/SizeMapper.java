package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.Size;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SizeMapper implements RowMapper<Size> {
    @Override
    public Size mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Size size = new Size();
        size.setId(resultSet.getLong("id"));
        size.setAmount(resultSet.getDouble("amount"));
        size.setUnits(resultSet.getString("units"));
        return size;
    }
}
