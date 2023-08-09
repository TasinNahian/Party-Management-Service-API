package com.tmf.partymgmtservices.api.individual.domain.request;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;
import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;

import java.io.Serializable;

public class CreditRatingRequest extends BaseBundled implements Serializable {
        private String id;
        private String creditAgencyName;
        private String creditAgencyType;
        private String ratingReference;
        private Integer ratingScore;
        private ValidFor validFor;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getCreditAgencyName() {
                return creditAgencyName;
        }

        public void setCreditAgencyName(String creditAgencyName) {
                this.creditAgencyName = creditAgencyName;
        }

        public String getCreditAgencyType() {
                return creditAgencyType;
        }

        public void setCreditAgencyType(String creditAgencyType) {
                this.creditAgencyType = creditAgencyType;
        }

        public String getRatingReference() {
                return ratingReference;
        }

        public void setRatingReference(String ratingReference) {
                this.ratingReference = ratingReference;
        }

        public Integer getRatingScore() {
                return ratingScore;
        }

        public void setRatingScore(Integer ratingScore) {
                this.ratingScore = ratingScore;
        }

        public ValidFor getValidFor() {
                return validFor;
        }

        public void setValidFor(ValidFor validFor) {
                this.validFor = validFor;
        }
}
