package com.tmf.partymgmtservices.api.individual.services.impl;

import com.tmf.partymgmtservices.api.filter.SessionRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SessionDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public SessionUserDetails loadUserByUsername(HttpServletRequest request) throws UsernameNotFoundException {
        SessionUserDetails sessionUserDetails = new SessionUserDetails();
        SessionRequest sessionRequest = new SessionRequest();
        sessionRequest.setLoginName(request.getHeader("login-name"));
        sessionRequest.setEmail(request.getHeader("email"));
        sessionRequest.setCorrelationId(request.getHeader("correlation-id"));
        sessionRequest.setxCorrelationId(request.getHeader("x-correlation-id"));
        sessionRequest.setFullName(request.getHeader("full-name"));
        sessionUserDetails.setSessionRequest(sessionRequest);
        return sessionUserDetails;
    }
}
