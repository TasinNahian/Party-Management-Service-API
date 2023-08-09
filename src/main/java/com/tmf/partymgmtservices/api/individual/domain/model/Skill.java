package com.tmf.partymgmtservices.api.individual.domain.model;

import java.io.Serializable;

public class Skill extends BaseBundled implements Serializable {
    private String id;
    private String comment;
    private String evaluatedLevel;
    private String skillCode;
    private String skillName;
    private Long validForId;
    private String individualId;

    public String getIndividualId() {
        return individualId;
    }

    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEvaluatedLevel() {
        return evaluatedLevel;
    }

    public void setEvaluatedLevel(String evaluatedLevel) {
        this.evaluatedLevel = evaluatedLevel;
    }

    public String getSkillCode() {
        return skillCode;
    }

    public void setSkillCode(String skillCode) {
        this.skillCode = skillCode;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Long getValidForId() {
        return validForId;
    }

    public void setValidForId(Long validForId) {
        this.validForId = validForId;
    }
}
