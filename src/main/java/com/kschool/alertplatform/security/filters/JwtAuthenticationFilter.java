package com.kschool.alertplatform.security.filters;


import com.kschool.alertplatform.security.domain.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Enumeration;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String JWT_HEADER_STRING = "alert-platform-id-token";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
        }

        String jwt = httpServletRequest.getHeader(JWT_HEADER_STRING);
        if (jwt != null) {
            try {
                String payload = jwt.split("\\.")[1];
                byte[] decodedPayload = Base64.getUrlDecoder().decode(payload);
				ObjectMapper objectMapper = new ObjectMapper()
						.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

				User user = objectMapper.readValue(decodedPayload, User.class);
                SecurityContextHolder.getContext().setAuthentication(user);

            } catch (Exception ex) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
