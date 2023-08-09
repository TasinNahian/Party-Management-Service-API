package com.tmf.partymgmtservices.api.organization.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tmf.partymgmtservices.api.individual.domain.model.*;
import com.tmf.partymgmtservices.api.individual.domain.request.ContactMediumRequest;
import com.tmf.partymgmtservices.api.individual.domain.request.CreditRatingRequest;
import com.tmf.partymgmtservices.api.individual.domain.request.IndividualOrganizationIdentificationRequest;
import com.tmf.partymgmtservices.api.individual.domain.request.TaxExemptionCertificateRequest;

import java.io.Serializable;
import java.util.List;

public class OrganizationRequest extends BaseBundled implements Serializable {
    private String id;
    @JsonProperty("isHeadOffice")
    private boolean headOffice;

    @JsonProperty("isLegalEntity")
    private boolean legalEntity;
    private String name;
    private String nameType;
    private String organizationType;
    private String tradingName;
    private String status;

    private ValidFor existsDuring;
    private OrganizationParentRelationshipRequest organizationParentRelationship;

    private List<ContactMediumRequest> contactMedium;
    private List<CreditRatingRequest> creditRating;
    private List<ExternalReference> externalReference;
    private List<OrganizationChildRelationshipRequest> organizationChildRelationship;
    private List<IndividualOrganizationIdentificationRequest> organizationIdentification;
    private List<OtherNameOrganizationRequest> otherName;
    private List<PartyCharacteristic> partyCharacteristic;
    private List<RelatedParty> relatedParty;
    private List<TaxExemptionCertificateRequest> taxExemptionCertificate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHeadOffice() {
        return headOffice;
    }

    public void setHeadOffice(boolean headOffice) {
        this.headOffice = headOffice;
    }

    public boolean isLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(boolean legalEntity) {
        this.legalEntity = legalEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ValidFor getExistsDuring() {
        return existsDuring;
    }

    public void setExistsDuring(ValidFor existsDuring) {
        this.existsDuring = existsDuring;
    }

    public List<ContactMediumRequest> getContactMedium() {
        return contactMedium;
    }

    public void setContactMedium(List<ContactMediumRequest> contactMedium) {
        this.contactMedium = contactMedium;
    }

    public List<CreditRatingRequest> getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(List<CreditRatingRequest> creditRating) {
        this.creditRating = creditRating;
    }

    public List<ExternalReference> getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(List<ExternalReference> externalReference) {
        this.externalReference = externalReference;
    }

    public List<OrganizationChildRelationshipRequest> getOrganizationChildRelationship() {
        return organizationChildRelationship;
    }

    public void setOrganizationChildRelationship(List<OrganizationChildRelationshipRequest> organizationChildRelationship) {
        this.organizationChildRelationship = organizationChildRelationship;
    }

    public List<IndividualOrganizationIdentificationRequest> getOrganizationIdentification() {
        return organizationIdentification;
    }

    public void setOrganizationIdentification(List<IndividualOrganizationIdentificationRequest> organizationIdentification) {
        this.organizationIdentification = organizationIdentification;
    }

    public OrganizationParentRelationshipRequest getOrganizationParentRelationship() {
        return organizationParentRelationship;
    }

    public void setOrganizationParentRelationship(OrganizationParentRelationshipRequest organizationParentRelationship) {
        this.organizationParentRelationship = organizationParentRelationship;
    }

    public List<OtherNameOrganizationRequest> getOtherName() {
        return otherName;
    }

    public void setOtherName(List<OtherNameOrganizationRequest> otherName) {
        this.otherName = otherName;
    }

    public List<PartyCharacteristic> getPartyCharacteristic() {
        return partyCharacteristic;
    }

    public void setPartyCharacteristic(List<PartyCharacteristic> partyCharacteristic) {
        this.partyCharacteristic = partyCharacteristic;
    }

    public List<RelatedParty> getRelatedParty() {
        return relatedParty;
    }

    public void setRelatedParty(List<RelatedParty> relatedParty) {
        this.relatedParty = relatedParty;
    }

    public List<TaxExemptionCertificateRequest> getTaxExemptionCertificate() {
        return taxExemptionCertificate;
    }

    public void setTaxExemptionCertificate(List<TaxExemptionCertificateRequest> taxExemptionCertificate) {
        this.taxExemptionCertificate = taxExemptionCertificate;
    }
}
