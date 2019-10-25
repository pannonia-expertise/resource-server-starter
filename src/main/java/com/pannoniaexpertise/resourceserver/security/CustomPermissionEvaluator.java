package com.pannoniaexpertise.resourceserver.security;

import com.pannoniaexpertise.resourceserver.exception.ResourceServerRoleException;
import com.pannoniaexpertise.resourceserver.service.PermissionService;

import lombok.AllArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private final PermissionService permissionService;

    @Override
    public boolean hasPermission(final Authentication authentication, final Object resourceObj, final Object operationObj) {
        final String resource = (String) resourceObj;
        final String operation = (String) operationObj;

        List<String> roles = getRolesFromAuthentication(authentication);
        if (roles.isEmpty()) {
            throw new ResourceServerRoleException("User does not have any roles assigned.");
        }

        for (final String role : roles) {
            if (!permissionService.checkPermission(role, resource, operation)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasPermission(final Authentication authentication, final Serializable targetId, final String targetType, final Object permission) {
        return false;
    }

    public static List<String> getRolesFromAuthentication(final Authentication auth) {
        final Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        if (authorities.size() == 0) {
            throw new ResourceServerRoleException("User does not have any roles assigned.");
        }
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
