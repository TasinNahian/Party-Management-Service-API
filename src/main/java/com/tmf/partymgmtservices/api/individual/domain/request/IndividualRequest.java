package com.tmf.partymgmtservices.api.individual.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tmf.partymgmtservices.api.individual.domain.model.ExternalReference;
import com.tmf.partymgmtservices.api.individual.domain.model.Individual;
import com.tmf.partymgmtservices.api.individual.domain.model.PartyCharacteristic;
import com.tmf.partymgmtservices.api.individual.domain.model.RelatedParty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndividualRequest extends Individual {
    private List<ContactMediumRequest> contactMedium;
    private List<CreditRatingRequest> creditRating;
    private List<DisabilityRequest> disability;
    private List<ExternalReference> externalReference;
    private List<IndividualOrganizationIdentificationRequest> individualIdentification;
    private List<LanguageAbilityRequest> languageAbility;
    private List<OtherNameIndividualRequest> otherName;
    private List<PartyCharacteristic> partyCharacteristic;
    private List<RelatedParty> relatedParty;
    private List<SkillRequest> skill;
    private List<TaxExemptionCertificateRequest> taxExemptionCertificate;

    public List<CreditRatingRequest> getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(List<CreditRatingRequest> creditRating) {
        this.creditRating = creditRating;
    }

    public List<DisabilityRequest> getDisability() {
        return disability;
    }

    public void setDisability(List<DisabilityRequest> disability) {
        this.disability = disability;
    }

    public List<IndividualOrganizationIdentificationRequest> getIndividualIdentification() {
        return individualIdentification;
    }

    public void setIndividualIdentification(List<IndividualOrganizationIdentificationRequest> individualIdentification) {
        this.individualIdentification = individualIdentification;
    }

    public List<LanguageAbilityRequest> getLanguageAbility() {
        return languageAbility;
    }

    public void setLanguageAbility(List<LanguageAbilityRequest> languageAbility) {
        this.languageAbility = languageAbility;
    }

    public List<OtherNameIndividualRequest> getOtherName() {
        return otherName;
    }

    public void setOtherName(List<OtherNameIndividualRequest> otherName) {
        this.otherName = otherName;
    }

    public List<ContactMediumRequest> getContactMedium() {
        return contactMedium;
    }

    public void setContactMedium(List<ContactMediumRequest> contactMedium) {
        this.contactMedium = contactMedium;
    }



    public List<ExternalReference> getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(List<ExternalReference> externalReference) {
        this.externalReference = externalReference;
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

    public List<SkillRequest> getSkill() {
        return skill;
    }

    public void setSkill(List<SkillRequest> skill) {
        this.skill = skill;
    }

    public List<TaxExemptionCertificateRequest> getTaxExemptionCertificate() {
        return taxExemptionCertificate;
    }

    public void setTaxExemptionCertificate(List<TaxExemptionCertificateRequest> taxExemptionCertificate) {
        this.taxExemptionCertificate = taxExemptionCertificate;
    }
}
