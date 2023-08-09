package com.tmf.partymgmtservices.api.individual.domain.request;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;
import com.tmf.partymgmtservices.api.individual.domain.model.MediumCharacteristic;
import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;

import java.io.Serializable;

public class ContactMediumRequest extends BaseBundled implements Serializable {
    private String id;
    private String mediumType;
    private Boolean preferred;
    private MediumCharacteristic characteristic;
    private ValidFor validFor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediumType() {
        return mediumType;
    }

    public void setMediumType(String mediumType) {
        this.mediumType = mediumType;
    }

    public Boolean getPreferred() {
        return preferred;
    }

    public void setPreferred(Boolean preferred) {
        this.preferred = preferred;
    }

    public MediumCharacteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(MediumCharacteristic characteristic) {
        this.characteristic = characteristic;
    }

    public ValidFor getValidFor() {
        return validFor;
    }

    public void setValidFor(ValidFor validFor) {
        this.validFor = validFor;
    }
}
