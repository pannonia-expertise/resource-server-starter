package com.pannoniaexpertise.resourceserver.security;

import com.pannoniaexpertise.resourceserver.exception.ResourceServerSecurityException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetails;

public class ResourceServerUsernameProvider {

    /**
     * Returns username from currently active security context.
     *
     * @return username
     */
    public static String getUsername() {
        final SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            throw new ResourceServerSecurityException("Security context not found");
        }
        final Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            throw new ResourceServerSecurityException("Authentication not found in security context");
        }
        final Object principal = authentication.getPrincipal();
        if (principal == null) {
            throw new ResourceServerSecurityException("User not found in SecurityContext");
        }

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else if (principal instanceof ClientDetails) {
            return ((ClientDetails) principal).getClientId();
        }

        return (String) principal;
    }
}
