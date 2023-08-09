package com.tmf.partymgmtservices.repository.db.repo.impl;

import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;
import com.tmf.partymgmtservices.api.organization.domain.model.*;
import com.tmf.partymgmtservices.api.util.Defs;
import com.tmf.partymgmtservices.repository.db.model.OrganizationDaoWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class OrganizationDaoDaoWriteRepositoryImpl extends JdbcDaoSupport implements OrganizationDaoWriteRepository {
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    private static final String INSERT_VALID_FOR_SQL = "INSERT INTO valid_for_pm(\n" +
            "\tid, start_datetime, end_datetime)\n" +
            "\tVALUES (?, ?, ?)";
    private static final String INSERT_OTHER_NAME_ORGANIZATION = "INSERT INTO public.other_name_organization(\n" +
            "\tid, name, name_type, trading_name, valid_for_id, base_type, schema_location, type, organization_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_ORGANIZATION_SQL = "INSERT INTO organization(\n" +
            "\tid, is_head_office, is_legal_entity, name, name_type, organization_type, trading_name, status, base_type, schema_location, type, organization_parent_relationship_id, exists_during_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_ORGANIZATION_REF = "INSERT INTO public.organization_ref(\n" +
            "\tid, href, name, base_type, schema_location, type, referred_type)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_ORGANIZATION_CHILD_RELATIONSHIP_SQL = "INSERT INTO organization_child_relationship(\n" +
            "\tid, relationship_type, base_type, schema_location, type, organization_id, organization_ref_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_ORGANIZATION_PARENT_RELATIONSHIP_SQL = "INSERT INTO organization_parent_relationship(\n" +
            "\tid, relationship_type, base_type, schema_location, type, organization_ref_id)\n" +
            "\tVALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_FROM_ORGANIZATION_BY_ID = "DELETE FROM ORGANIZATION WHERE ID = '%s'";

    private static final String DELETE_FROM_CHILD_RELATIONSHIP_BY_ORGANIZATION_ID = "DELETE FROM ORGANIZATION_CHILD_RELATIONSHIP WHERE ORGANIZATION_ID = '%s' ";
    private static final String DELETE_FROM_PARENT_RELATIONSHIP_BY_ID = "DELETE FROM ORGANIZATION_PARENT_RELATIONSHIP WHERE ID = '%s' ";

    @Override
    public Boolean saveOrganization(Organization organization) {
        return Objects.requireNonNull(getJdbcTemplate()).execute(INSERT_ORGANIZATION_SQL, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                preparedStatement.setString(1, organization.getId());
                preparedStatement.setBoolean(2, organization.isHeadOffice());
                preparedStatement.setBoolean(3, organization.isLegalEntity());
                preparedStatement.setString(4, organization.getName());
                preparedStatement.setString(5, organization.getNameType());
                preparedStatement.setString(6, organization.getOrganizationType());
                preparedStatement.setString(7, organization.getTradingName());
                preparedStatement.setString(8, organization.getStatus());
                preparedStatement.setString(9, organization.getBaseType());
                preparedStatement.setString(10, organization.getSchemaLocation());
                preparedStatement.setString(11, organization.getType());
                preparedStatement.setString(12, organization.getOrganizationParentRelationshipId());
                preparedStatement.setString(13, organization.getExistsDuringId());
                return preparedStatement.execute();
            }
        });
    }
    @Override
    public Long getNextSequenceValue(String sequenceName) {
        String sql = "SELECT nextval('%s')";
        final SqlRowSet sqlRowSet = Objects.requireNonNull(getJdbcTemplate()).queryForRowSet(String.format(sql, sequenceName));
        sqlRowSet.next();
        return sqlRowSet.getLong(1);
    }

    @Override
    public Long saveValidFor(ValidFor validFor) {
        Long id = getNextSequenceValue("valid_for_id_sequence");

        int success = Objects.requireNonNull(getJdbcTemplate()).execute(INSERT_VALID_FOR_SQL, new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                preparedStatement.setLong(1, id);
                preparedStatement.setTimestamp(2, validFor.getStartDateTime());
                preparedStatement.setTimestamp(3, validFor.getEndDateTime());
                return preparedStatement.executeUpdate();
            }
        });
        return success > 0 ? id : -1L;
    }
    @Override
    public String saveOrganizationRef(OrganizationRef organizationRef) {
        String id = String.valueOf(getNextSequenceValue(Defs.ORGANIZATION_REF_ID_SEQUENCE));

        int success = Objects.requireNonNull(getJdbcTemplate()).execute(INSERT_ORGANIZATION_REF, new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, organizationRef.getHref());
                preparedStatement.setString(3, organizationRef.getName());
                preparedStatement.setString(4, organizationRef.getBaseType());
                preparedStatement.setString(5, organizationRef.getSchemaLocation());
                preparedStatement.setString(6, organizationRef.getType());
                preparedStatement.setString(7, organizationRef.getReferredType());

                return preparedStatement.executeUpdate();
            }
        });
        return success > 0 ? id : "-1";
    }

    @Override
    public String saveOrganizationParentRelationship(OrganizationParentRelationship parentRelationship){

        int success =  Objects.requireNonNull(getJdbcTemplate()).execute(INSERT_ORGANIZATION_PARENT_RELATIONSHIP_SQL, new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement preparedStatement)
                    throws SQLException, DataAccessException {

                preparedStatement.setString(1, parentRelationship.getId());
                preparedStatement.setString(2, parentRelationship.getRelationshipType());
                preparedStatement.setString(3, parentRelationship.getBaseType());
                preparedStatement.setString(4, parentRelationship.getSchemaLocation());
                preparedStatement.setString(5, parentRelationship.getType());
                preparedStatement.setString(6, parentRelationship.getOrganizationRefId());

                return preparedStatement.executeUpdate();
            }
        });
        return success > 0 ? parentRelationship.getId() : "-1";
    }

    @Override
    public int[] saveOtherNameOrganization(List<OtherNameOrganization> otherNameOrganizationList){
        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_OTHER_NAME_ORGANIZATION, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                OtherNameOrganization otherNameOrganization = otherNameOrganizationList.get(i);
                preparedStatement.setString(1, otherNameOrganization.getId());
                preparedStatement.setString(2, otherNameOrganization.getName());
                preparedStatement.setString(3, otherNameOrganization.getNameType());
                preparedStatement.setString(4, otherNameOrganization.getTradingName());
                preparedStatement.setLong(5, otherNameOrganization.getValidForId());
                preparedStatement.setString(6, otherNameOrganization.getBaseType());
                preparedStatement.setString(7, otherNameOrganization.getSchemaLocation());
                preparedStatement.setString(8, otherNameOrganization.getType());
                preparedStatement.setString(9, otherNameOrganization.getOrganizationId()!=null? otherNameOrganization.getOrganizationId() : null );
            }
            @Override
            public int getBatchSize() {
                return otherNameOrganizationList.size();
            }
        });
    }

    @Override
    public int[] saveOrganizationChildRelationshipRequest(List<OrganizationChildRelationship> organizationChildRelationshipList) {
        return Objects.requireNonNull(getJdbcTemplate()).batchUpdate(INSERT_ORGANIZATION_CHILD_RELATIONSHIP_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                OrganizationChildRelationship organizationChildRelationship = organizationChildRelationshipList.get(i);
                preparedStatement.setString(1, organizationChildRelationship.getId());
                preparedStatement.setString(2, organizationChildRelationship.getRelationshipType());
                preparedStatement.setString(3, organizationChildRelationship.getBaseType());
                preparedStatement.setString(4, organizationChildRelationship.getSchemaLocation());
                preparedStatement.setString(5, organizationChildRelationship.getType());
                preparedStatement.setString(6, organizationChildRelationship.getOrganizationId()!=null? organizationChildRelationship.getOrganizationId() : null );
                preparedStatement.setString(7, organizationChildRelationship.getOrganizationRefId());
            }

            @Override
            public int getBatchSize() {
                return organizationChildRelationshipList.size();
            }
        });
    }

    @Override
    public Boolean deleteChildRelationshipByOrganizationId(String organizationId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_CHILD_RELATIONSHIP_BY_ORGANIZATION_ID, organizationId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteParentRelationshipByOrganizationId(String organizationId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_PARENT_RELATIONSHIP_BY_ID, organizationId));
        return success > 0 ? true : false;
    }

    @Override
    public Boolean updateOrganizationById(String id, Map<String, String> map){
        String updateSql = "UPDATE ORGANIZATION SET ";
        for (Map.Entry<String, String> entry : map.entrySet()){
            updateSql += entry.getKey()+" = "+entry.getValue()+", ";
        }
        updateSql = updateSql.substring(0, updateSql.length()-2);
        updateSql += " WHERE ID = '"+id+"'";
        int success = Objects.requireNonNull(getJdbcTemplate()).update(updateSql);
        return success > 0 ? true : false;
    }

    @Override
    public Boolean deleteOrganizationById(String organizationId){
        int success = Objects.requireNonNull(getJdbcTemplate()).update(String.format(DELETE_FROM_ORGANIZATION_BY_ID, organizationId));
        return success > 0 ? true : false;
    }
}
