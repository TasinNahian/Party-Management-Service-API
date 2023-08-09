package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.MediumCharacteristic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MediumCharacteristicMapper implements RowMapper<MediumCharacteristic> {
    @Override
    public MediumCharacteristic mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        MediumCharacteristic mediumCharacteristic = new MediumCharacteristic();

        mediumCharacteristic.setId(resultSet.getString("id"));
        mediumCharacteristic.setCity(resultSet.getString("city"));
        mediumCharacteristic.setContactType(resultSet.getString("contact_type"));
        mediumCharacteristic.setCountry(resultSet.getString("country"));
        mediumCharacteristic.setEmailAddress(resultSet.getString("email_address"));
        mediumCharacteristic.setFaxNumber(resultSet.getString("fax_number"));
        mediumCharacteristic.setPhoneNumber(resultSet.getString("phone_number"));
        mediumCharacteristic.setPostCode(resultSet.getString("post_code"));
        mediumCharacteristic.setSocialNetworkId(resultSet.getString("social_network_id"));
        mediumCharacteristic.setStateOrProvince(resultSet.getString("state_or_province"));
        mediumCharacteristic.setStreet1(resultSet.getString("street1"));
        mediumCharacteristic.setStreet2(resultSet.getString("street2"));

        mediumCharacteristic.setBaseType(resultSet.getString("base_type"));
        mediumCharacteristic.setSchemaLocation(resultSet.getString("schema_location"));
        mediumCharacteristic.setType(resultSet.getString("type"));

        return mediumCharacteristic;
    }
}
