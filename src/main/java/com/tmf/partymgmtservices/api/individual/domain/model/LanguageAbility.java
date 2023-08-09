package com.tmf.partymgmtservices.api.individual.domain.model;

import java.io.Serializable;

public class LanguageAbility extends BaseBundled implements Serializable {
    private String id;
    private boolean favouriteLanguage;
    private String languageCode;
    private String languageName;
    private String listeningProficiency;
    private String readingProficiency;
    private String speakingProficiency;
    private String writingProficiency;
    private Long validForId;

    private String individualId;
    private String organizationId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFavouriteLanguage() {
        return favouriteLanguage;
    }

    public void setFavouriteLanguage(boolean favouriteLanguage) {
        this.favouriteLanguage = favouriteLanguage;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getListeningProficiency() {
        return listeningProficiency;
    }

    public void setListeningProficiency(String listeningProficiency) {
        this.listeningProficiency = listeningProficiency;
    }

    public String getReadingProficiency() {
        return readingProficiency;
    }

    public void setReadingProficiency(String readingProficiency) {
        this.readingProficiency = readingProficiency;
    }

    public String getSpeakingProficiency() {
        return speakingProficiency;
    }

    public void setSpeakingProficiency(String speakingProficiency) {
        this.speakingProficiency = speakingProficiency;
    }

    public String getWritingProficiency() {
        return writingProficiency;
    }

    public void setWritingProficiency(String writingProficiency) {
        this.writingProficiency = writingProficiency;
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
