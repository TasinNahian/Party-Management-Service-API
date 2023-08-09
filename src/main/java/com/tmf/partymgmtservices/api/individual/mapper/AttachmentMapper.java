package com.tmf.partymgmtservices.api.individual.mapper;

import com.tmf.partymgmtservices.api.individual.domain.model.Attachment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttachmentMapper implements RowMapper<Attachment> {
    @Override
    public Attachment mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Attachment attachment = new Attachment();

        attachment.setId(resultSet.getString("id"));
        attachment.setHref(resultSet.getString("href"));
        attachment.setAttachmentType(resultSet.getString("attachment_type"));
        attachment.setContent(resultSet.getString("content"));
        attachment.setDescription((resultSet.getString("description")));
        attachment.setRef((resultSet.getBoolean("is_ref")));
        attachment.setMimeType(resultSet.getString("mime_type"));
        attachment.setName(resultSet.getString("name"));
        attachment.setUrl(resultSet.getString("url"));

        attachment.setSizeId(resultSet.getString("size_pm_id"));
        attachment.setValidForId(resultSet.getLong("valid_for_id"));

        attachment.setBaseType(resultSet.getString("base_type"));
        attachment.setSchemaLocation(resultSet.getString("schema_location"));
        attachment.setType(resultSet.getString("type"));
        attachment.setReferredType(resultSet.getString("referred_type"));
        return attachment;
    }
}
