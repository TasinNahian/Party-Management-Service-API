package com.tmf.partymgmtservices.api.individual.services.impl;

import com.tmf.partymgmtservices.api.filter.SessionRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class SessionUserDetails implements UserDetails {
    private static final long serialVersionUID = 5217383252556592960L;
    private SessionRequest sessionRequest;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public SessionRequest getSessionRequest() {
        return sessionRequest;
    }

    public void setSessionRequest(SessionRequest sessionRequest) {
        this.sessionRequest = sessionRequest;
    }
}
