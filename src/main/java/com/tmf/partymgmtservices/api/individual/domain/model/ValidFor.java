package com.tmf.partymgmtservices.api.individual.domain.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ValidFor implements Serializable {
    private Long id;
    private Timestamp endDateTime;
    private Timestamp startDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }
}
