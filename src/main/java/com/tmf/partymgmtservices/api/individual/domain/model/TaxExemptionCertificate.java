package com.tmf.partymgmtservices.api.individual.domain.model;

import java.io.Serializable;

public class TaxExemptionCertificate extends BaseBundled implements Serializable {
     private String id;
     private String attachmentId;
     private Long validForId;
     private String individualId;
     private String organizationId;

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
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
