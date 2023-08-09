package com.tmf.partymgmtservices.repository.db.repo.impl;


import com.tmf.partymgmtservices.api.individual.domain.model.IndividualOrganizationIdentification;
import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;
import com.tmf.partymgmtservices.api.individual.mapper.IndividualIdentificationMapper;
import com.tmf.partymgmtservices.api.individual.mapper.ValidForMapper;
import com.tmf.partymgmtservices.api.organization.domain.model.*;
import com.tmf.partymgmtservices.api.organization.mapper.*;
import com.tmf.partymgmtservices.repository.db.model.OrganizationDaoReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

@Repository
public class OrganizationDaoReadRepositoryImpl extends JdbcDaoSupport implements OrganizationDaoReadRepository {
    private static final String SELECT_ORGANIZATION_REF_SQL = "SELECT * FROM ORGANIZATION_REF WHERE ID='%s'";
    private static final String SELECT_ORGANIZATION_SQL = "SELECT * FROM ORGANIZATION WHERE ID='%s'";
    private static final String SELECT_OTHER_NAME_ORGANIZATION_SQL = "SELECT * FROM OTHER_NAME_ORGANIZATION WHERE ORGANIZATION_ID='%s'";
    private static final String SELECT_VALID_FOR_PM_SQL = "SELECT * FROM VALID_FOR_PM WHERE ID='%s'";
    private static final String SELECT_ORGANIZATION_WITH_LIMIT_OFFSET_SQL = "SELECT ID FROM ORGANIZATION ORDER BY ID";
    private static final String SELECT_ORGANIZATION_PARENT_RELATIONSHIP_SQL = "SELECT * FROM ORGANIZATION_PARENT_RELATIONSHIP WHERE ID='%s'";;
    private static final String SELECT_ORGANIZATION_CHILD_RELATIONSHIP_SQL = "SELECT * FROM ORGANIZATION_CHILD_RELATIONSHIP WHERE ORGANIZATION_ID='%s'";;
    private static final String SELECT_ORGANIZATION_IDENTIFICATION_SQL = "SELECT * FROM INDIVIDUAL_ORGANIZATION_IDENTIFICATION WHERE ORGANIZATION_ID='%s'";

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public Organization getOrganization(String organizationId) {
        List<Organization> organizationList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_ORGANIZATION_SQL, organizationId), new OrganizationMapper());
        return organizationList.isEmpty() ? null : organizationList.get(0);
    }

    @Override
    public ValidFor getValidFor(String individualId) {
        List<ValidFor> validForList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_VALID_FOR_PM_SQL, individualId), new ValidForMapper());
        return validForList.isEmpty() ? null : validForList.get(0);
    }

    @Override
    public List<OtherNameOrganization> getOtherNameOrganization(String organizationId) {
        List<OtherNameOrganization> otherNameOrganizationList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_OTHER_NAME_ORGANIZATION_SQL, organizationId), new OtherNameOrganizationMapper());
        return otherNameOrganizationList.isEmpty() ? null : otherNameOrganizationList;
    }
    @Override
    public List<String> getPaginatedOrganization(Integer limit, Integer offset){

        return Objects.requireNonNull(getJdbcTemplate()).queryForList(
                SELECT_ORGANIZATION_WITH_LIMIT_OFFSET_SQL +" LIMIT "+limit+ " OFFSET "+offset, String.class);

    }
    @Override
    public OrganizationParentRelationship getOrganizationParentRelationship(String id){
        List<OrganizationParentRelationship> attachmentList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_ORGANIZATION_PARENT_RELATIONSHIP_SQL, id), new OrganizationParentRelationshipMapper());
        return attachmentList.isEmpty() ? null : attachmentList.get(0);
    }

    @Override
    public OrganizationRef getOrganizationRef(String id){
        List<OrganizationRef> attachmentList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_ORGANIZATION_REF_SQL, id), new OrganizationRefMapper());
        return attachmentList.isEmpty() ? null : attachmentList.get(0);
    }
    @Override
    public List<OrganizationChildRelationship> getOrganizationChildRelationship(String id) {
        List<OrganizationChildRelationship> childRelationshipList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_ORGANIZATION_CHILD_RELATIONSHIP_SQL, id), new OrganizationChildRelationshipMapper());
        return childRelationshipList.isEmpty() ? null : childRelationshipList;
    }

    @Override
    public List<IndividualOrganizationIdentification> getOrganizationIdentification(String organizationId) {
        List<IndividualOrganizationIdentification> individualOrganizationIdentificationList = Objects.requireNonNull(getJdbcTemplate()).query(
                String.format(SELECT_ORGANIZATION_IDENTIFICATION_SQL, organizationId), new IndividualIdentificationMapper());
        return individualOrganizationIdentificationList.isEmpty() ? null : individualOrganizationIdentificationList;
    }
}
