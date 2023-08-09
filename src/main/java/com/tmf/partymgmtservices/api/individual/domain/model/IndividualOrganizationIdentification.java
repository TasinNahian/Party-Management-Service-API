package com.tmf.partymgmtservices.api.individual.domain.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class IndividualOrganizationIdentification extends BaseBundled implements Serializable {
    private String identificationId;
    private String identificationType;
    private String issuingAuthority;
    private Timestamp issuingDate;
    private String attachmentId;
    private Long validForId;

    private String individualId;
    private String organizationId;

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

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Long getValidForId() {
        return validForId;
    }

    public void setValidForId(Long validForId) {
        this.validForId = validForId;
    }

    public String getIndividualId() {
        return individualId;
    }

    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}
