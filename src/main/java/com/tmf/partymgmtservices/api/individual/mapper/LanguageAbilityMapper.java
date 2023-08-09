package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.LanguageAbility;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageAbilityMapper implements RowMapper<LanguageAbility> {
    @Override
    public LanguageAbility mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        LanguageAbility languageAbility = new LanguageAbility();
        languageAbility.setId(resultSet.getString("id"));
        languageAbility.setFavouriteLanguage(resultSet.getBoolean("is_favourite_language"));
        languageAbility.setLanguageCode(resultSet.getString("language_code"));
        languageAbility.setLanguageName(resultSet.getString("language_name"));
        languageAbility.setListeningProficiency(resultSet.getString("listening_proficiency"));
        languageAbility.setReadingProficiency(resultSet.getString("reading_proficiency"));
        languageAbility.setSpeakingProficiency(resultSet.getString("speaking_proficiency"));
        languageAbility.setWritingProficiency(resultSet.getString("writing_proficiency"));
        languageAbility.setValidForId(resultSet.getLong("valid_for_id"));


        languageAbility.setBaseType(resultSet.getString("base_type"));
        languageAbility.setSchemaLocation(resultSet.getString("schema_location"));
        languageAbility.setType(resultSet.getString("type"));

        languageAbility.setType(resultSet.getString("individual_id"));

        return languageAbility;
    }
}
