package com.tmf.partymgmtservices.api.individual.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class ExternalReference extends BaseBundled implements Serializable {
        private String id;
        private String externalReferenceType;
        private String name;

        @JsonIgnore
        private String individualId;
        @JsonIgnore
        private String organizationId;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getExternalReferenceType() {
                return externalReferenceType;
        }

        public void setExternalReferenceType(String externalReferenceType) {
                this.externalReferenceType = externalReferenceType;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
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
