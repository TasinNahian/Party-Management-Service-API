package com.tmf.partymgmtservices.api.individual.domain.model;

import java.io.Serializable;

public class OtherNameIndividual extends BaseBundled implements Serializable {
    private String id;
    private String aristocraticTitle;
    private String familyName;
    private String familyNamePrefix;
    private String formattedName;
    private String fullName;
    private String generation;
    private String givenName;
    private String legalName;
    private String middleName;
    private String preferredGivenName;
    private String title;
    private Long validForId;
    private String individualId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAristocraticTitle() {
        return aristocraticTitle;
    }

    public void setAristocraticTitle(String aristocraticTitle) {
        this.aristocraticTitle = aristocraticTitle;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyNamePrefix() {
        return familyNamePrefix;
    }

    public void setFamilyNamePrefix(String familyNamePrefix) {
        this.familyNamePrefix = familyNamePrefix;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPreferredGivenName() {
        return preferredGivenName;
    }

    public void setPreferredGivenName(String preferredGivenName) {
        this.preferredGivenName = preferredGivenName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
