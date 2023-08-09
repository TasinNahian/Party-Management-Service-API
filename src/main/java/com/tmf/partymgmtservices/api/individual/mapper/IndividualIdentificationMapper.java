package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.IndividualOrganizationIdentification;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IndividualIdentificationMapper implements RowMapper<IndividualOrganizationIdentification> {
    @Override
    public IndividualOrganizationIdentification mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        IndividualOrganizationIdentification individualOrganizationIdentification = new IndividualOrganizationIdentification();

        individualOrganizationIdentification.setIdentificationId(resultSet.getString("identification_id"));
        individualOrganizationIdentification.setIdentificationType(resultSet.getString("identification_type"));
        individualOrganizationIdentification.setIssuingAuthority(resultSet.getString("issuing_authority"));
        individualOrganizationIdentification.setIssuingDate(resultSet.getTimestamp("issuing_date"));
        individualOrganizationIdentification.setValidForId(resultSet.getLong("valid_for_id"));

        individualOrganizationIdentification.setBaseType(resultSet.getString("base_type"));
        individualOrganizationIdentification.setSchemaLocation(resultSet.getString("schema_location"));
        individualOrganizationIdentification.setType(resultSet.getString("type"));

        individualOrganizationIdentification.setAttachmentId(resultSet.getString("attachment_ref_id"));
        individualOrganizationIdentification.setOrganizationId(resultSet.getString("organization_id"));
        individualOrganizationIdentification.setIndividualId(resultSet.getString("individual_id"));


        return individualOrganizationIdentification;
    }
}
