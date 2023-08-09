package com.tmf.partymgmtservices.api.individual.domain.request;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;
import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;

import java.io.Serializable;
import java.sql.Timestamp;

public class IndividualOrganizationIdentificationRequest extends BaseBundled implements Serializable {
    private String identificationId;
    private String identificationType;
    private String issuingAuthority;
    private Timestamp issuingDate;
    private AttachmentRequest attachment;
    private ValidFor validFor;

    public String getIdentificationId() {
        return identificationId;
    }

    public void setIdentificationId(String identificationId) {
        this.identificationId = identificationId;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public Timestamp getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(Timestamp issuingDate) {
        this.issuingDate = issuingDate;
    }

    public AttachmentRequest getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentRequest attachment) {
        this.attachment = attachment;
    }

    public ValidFor getValidFor() {
        return validFor;
    }

    public void setValidFor(ValidFor validFor) {
        this.validFor = validFor;
    }
}
