package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.PartyCharacteristic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartyCharacteristicMapper implements RowMapper<PartyCharacteristic> {
    @Override
    public PartyCharacteristic mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        PartyCharacteristic partyCharacteristic = new PartyCharacteristic();

        partyCharacteristic.setId(resultSet.getString("id"));
        partyCharacteristic.setName(resultSet.getString("name"));
        partyCharacteristic.setValueType(resultSet.getString("value_type"));
        partyCharacteristic.setValue(resultSet.getString("value"));

        partyCharacteristic.setBaseType(resultSet.getString("base_type"));
        partyCharacteristic.setSchemaLocation(resultSet.getString("schema_location"));
        partyCharacteristic.setType(resultSet.getString("type"));

        partyCharacteristic.setIndividualId(resultSet.getString("individual_id"));
        partyCharacteristic.setOrganizationId(resultSet.getString("organization_id"));

        return partyCharacteristic;
    }
}
