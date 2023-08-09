package com.tmf.partymgmtservices.api.individual.domain.request;

import com.tmf.partymgmtservices.api.individual.domain.model.BaseBundled;
import com.tmf.partymgmtservices.api.individual.domain.model.Size;
import com.tmf.partymgmtservices.api.individual.domain.model.ValidFor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AttachmentRequest extends BaseBundled implements Serializable {
    private String id;
    private String href;
    private String attachmentType;
    private String content;
    private String description;
    @JsonProperty("isRef")
    private boolean ref;
    private String mimeType;
    private String name;
    private String url;
    private Size size;
    private ValidFor validFor;

    @JsonProperty("@referredType")
    private String referredType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ValidFor getValidFor() {
        return validFor;
    }

    public void setValidFor(ValidFor validFor) {
        this.validFor = validFor;
    }

    public String getReferredType() {
        return referredType;
    }

    public void setReferredType(String referredType) {
        this.referredType = referredType;
    }

    public boolean isRef() {
        return ref;
    }

    public void setRef(boolean ref) {
        this.ref = ref;
    }
}
