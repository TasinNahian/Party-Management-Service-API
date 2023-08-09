package com.tmf.partymgmtservices.api.filter;

import com.tmf.partymgmtservices.api.individual.services.impl.SessionDetailService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class RequestFilter extends OncePerRequestFilter {
    @Autowired
    private SessionDetailService sessionDetailService;
    private static final String CORRELATION_ID="correlation-id";
    private static final String XCORRELATION_ID="x-correlation-id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getRequestURI().endsWith("/healthCheck")) {
            if (StringUtils.hasLength(request.getHeader("login-name")) && StringUtils.hasLength(request.getHeader("full-name")) && StringUtils.hasLength(request.getHeader("email")) && StringUtils.hasLength(request.getHeader(CORRELATION_ID)) && StringUtils.hasLength(request.getHeader(XCORRELATION_ID))) {
                UserDetails userDetails = sessionDetailService.loadUserByUsername(request);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                
                MDC.clear();
                //fill the mdc but first clear it.
                MDC.put(CORRELATION_ID, request.getHeader(CORRELATION_ID));
    			MDC.put(XCORRELATION_ID, request.getHeader(XCORRELATION_ID));

            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        }
        filterChain.doFilter(request, response);
    }
}
