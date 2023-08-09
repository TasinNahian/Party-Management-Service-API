package com.tmf.partymgmtservices.api.util;


import com.tmf.partymgmtservices.api.exceptions.RequestException;
import com.tmf.partymgmtservices.api.filter.SessionRequest;
import com.tmf.partymgmtservices.api.individual.services.impl.SessionUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionRequestUtil {

    public static SessionRequest getSessionRequest() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof SessionUserDetails) {
            return ((SessionUserDetails) principal).getSessionRequest();
        } else {
            throw new RequestException("Unable to find Session Request Details");
        }
    }
}
