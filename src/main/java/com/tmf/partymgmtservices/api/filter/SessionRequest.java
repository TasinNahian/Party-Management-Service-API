package com.tmf.partymgmtservices.api.filter;

import java.io.Serializable;

public class SessionRequest implements Serializable {

    private static final long serialVersionUID = 3367113028257715352L;

    private String loginName;
    private String fullName;
    private String email;
    private String correlationId;
    private String xCorrelationId;


    public String getLoginName() {
        return loginName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getxCorrelationId() {
        return xCorrelationId;
    }

    public void setxCorrelationId(String xCorrelationId) {
        this.xCorrelationId = xCorrelationId;
    }

    @Override
    public String toString() {
        return "Session Info: {"
                + "Login Name: " + loginName + ", "
                + "Full Name: " + fullName + ", "
                + "Email: " + email + ", "
                + "Correlation ID: " + correlationId + ", "
                + "xCorrelation ID: " + xCorrelationId + "}";
    }
}
