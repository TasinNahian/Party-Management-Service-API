package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.TaxExemptionCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxExemptionCertificateMapper implements RowMapper<TaxExemptionCertificate> {
    @Override
    public TaxExemptionCertificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        TaxExemptionCertificate taxExemptionCertificate = new TaxExemptionCertificate();

        taxExemptionCertificate.setId(resultSet.getString("id"));
        taxExemptionCertificate.setValidForId(resultSet.getLong("valid_for_id"));

        taxExemptionCertificate.setBaseType(resultSet.getString("base_type"));
        taxExemptionCertificate.setSchemaLocation(resultSet.getString("schema_location"));
        taxExemptionCertificate.setType(resultSet.getString("type"));

        taxExemptionCertificate.setAttachmentId(resultSet.getString("attachment_ref_id"));
        taxExemptionCertificate.setIndividualId(resultSet.getString("individual_id"));
        taxExemptionCertificate.setOrganizationId(resultSet.getString("organization_id"));


        return taxExemptionCertificate;
    }
}
