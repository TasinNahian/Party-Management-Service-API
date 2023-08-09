package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.Skill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillsMapper implements RowMapper<Skill> {
    @Override
    public Skill mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Skill skill = new Skill();

        skill.setId(resultSet.getString("id"));
        skill.setComment(resultSet.getString("comment"));
        skill.setEvaluatedLevel(resultSet.getString("evaluated_level"));
        skill.setSkillCode(resultSet.getString("skill_code"));
        skill.setSkillName(resultSet.getString("skill_name"));
        skill.setValidForId(resultSet.getLong("valid_for_id"));

        skill.setBaseType(resultSet.getString("base_type"));
        skill.setSchemaLocation(resultSet.getString("schema_location"));
        skill.setType(resultSet.getString("type"));

        skill.setIndividualId(resultSet.getString("individual_id"));

        return skill;
    }
}
