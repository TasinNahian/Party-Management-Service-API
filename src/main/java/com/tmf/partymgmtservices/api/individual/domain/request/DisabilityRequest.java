package com.tmf.partymgmtservices.api.individual.domain.request;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;
import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;

import java.io.Serializable;

public class DisabilityRequest extends BaseBundled implements Serializable {
     private String id;
     private String disabilityCode;
     private String disabilityName;
     private ValidFor validFor;

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getDisabilityCode() {
          return disabilityCode;
     }

     public void setDisabilityCode(String disabilityCode) {
          this.disabilityCode = disabilityCode;
     }

     public String getDisabilityName() {
          return disabilityName;
     }

     public void setDisabilityName(String disabilityName) {
          this.disabilityName = disabilityName;
     }

     public ValidFor getValidFor() {
          return validFor;
     }

     public void setValidFor(ValidFor validFor) {
          this.validFor = validFor;
     }
}
