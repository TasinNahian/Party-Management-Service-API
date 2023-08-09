package com.tmf.partymgmtservices.api.individual.domain.request;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;
import com.tmf.partymgmtservices.api.individual.domain.model.TaxDefinition;
import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;

import java.io.Serializable;
import java.util.List;

public class TaxExemptionCertificateRequest extends BaseBundled implements Serializable {
     private String id;
     private AttachmentRequest attachment;
     private List<TaxDefinition> taxDefinition;
     private ValidFor validFor;

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public AttachmentRequest getAttachment() {
          return attachment;
     }

     public void setAttachment(AttachmentRequest attachment) {
          this.attachment = attachment;
     }

     public List<TaxDefinition> getTaxDefinition() {
          return taxDefinition;
     }

     public void setTaxDefinition(List<TaxDefinition> taxDefinition) {
          this.taxDefinition = taxDefinition;
     }

     public ValidFor getValidFor() {
          return validFor;
     }

     public void setValidFor(ValidFor validFor) {
          this.validFor = validFor;
     }
}
