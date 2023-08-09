package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.TaxDefinition;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxDefinitionMapper implements RowMapper<TaxDefinition> {
    @Override
    public TaxDefinition mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        TaxDefinition taxDefinition = new TaxDefinition();

        taxDefinition.setId(resultSet.getString("id"));
        taxDefinition.setName(resultSet.getString("name"));
        taxDefinition.setTaxType(resultSet.getString("tax_type"));

        taxDefinition.setBaseType(resultSet.getString("base_type"));
        taxDefinition.setSchemaLocation(resultSet.getString("schema_location"));
        taxDefinition.setType(resultSet.getString("type"));

        taxDefinition.setReferredType(resultSet.getString("referred_type"));
        taxDefinition.setTaxExemptionCertificateId(resultSet.getString("tax_exemption_certificate_id"));

        return taxDefinition;
    }
}
