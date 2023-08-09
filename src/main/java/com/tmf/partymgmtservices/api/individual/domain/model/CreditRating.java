package com.tmf.partymgmtservices.api.individual.domain.model;

import java.io.Serializable;

public class CreditRating extends BaseBundled implements Serializable {
        private String id;
        private String creditAgencyName;
        private String creditAgencyType;
        private String ratingReference;
        private Integer ratingScore;
        private Long validForId;
        private String organizationId;
        private String individualId;

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

        public Long getValidForId() {
                return validForId;
        }

        public void setValidForId(Long validForId) {
                this.validForId = validForId;
        }

        public String getOrganizationId() {
                return organizationId;
        }

        public void setOrganizationId(String organizationId) {
                this.organizationId = organizationId;
        }

        public String getIndividualId() {
                return individualId;
        }

        public void setIndividualId(String individualId) {
                this.individualId = individualId;
        }
}
