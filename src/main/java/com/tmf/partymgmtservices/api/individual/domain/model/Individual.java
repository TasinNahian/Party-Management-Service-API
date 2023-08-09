package com.tmf.partymgmtservices.api.individual.domain.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Individual extends BaseBundled implements Serializable {
    private String id;
    private String aristocraticTitle;
    private Timestamp birthDate;
    private String countryOfBirth;
    private Timestamp deathDate;
    private String familyName;
    private String familyNamePrefix;
    private String formattedName;
    private String fullName;
    private String gender;
    private String generation;
    private String givenName;
    private String legalName;
    private String location;
    private String maritalStatus;
    private String middleName;
    private String nationality;
    private String placeOfBirth;
    private String preferredGivenName;
    private String title;
    private String status;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    private String href;

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

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public Timestamp getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Timestamp deathDate) {
        this.deathDate = deathDate;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
