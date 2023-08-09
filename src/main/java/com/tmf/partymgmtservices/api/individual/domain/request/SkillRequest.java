package com.tmf.partymgmtservices.api.individual.domain.request;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;
import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;

import java.io.Serializable;

public class SkillRequest extends BaseBundled implements Serializable {
    private String id;
    private String comment;
    private String evaluatedLevel;
    private String skillCode;
    private String skillName;
    private ValidFor validFor;

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

    public ValidFor getValidFor() {
        return validFor;
    }

    public void setValidFor(ValidFor validFor) {
        this.validFor = validFor;
    }
}
