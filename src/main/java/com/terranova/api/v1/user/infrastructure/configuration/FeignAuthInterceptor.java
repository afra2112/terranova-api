package com.terranova.api.v1.user.infrastructure.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignAuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) return;

        Object credentials = authentication.getCredentials();

        if (credentials instanceof String token){
            template.header("Authorization", "Bearer " + token);
        }
    }
}
