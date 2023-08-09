package com.tmf.partymgmtservices.api.individual.domain.request;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;
import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LanguageAbilityRequest extends BaseBundled implements Serializable {
    private String id;
    @JsonProperty("isFavouriteLanguage")
    private boolean favouriteLanguage;
    private String languageCode;
    private String languageName;
    private String listeningProficiency;
    private String readingProficiency;
    private String speakingProficiency;
    private String writingProficiency;
    private ValidFor validFor;




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

    public ValidFor getValidFor() {
        return validFor;
    }

    public void setValidFor(ValidFor validFor) {
        this.validFor = validFor;
    }


}
