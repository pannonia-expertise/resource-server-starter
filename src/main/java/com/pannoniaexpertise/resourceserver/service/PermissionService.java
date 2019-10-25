package com.pannoniaexpertise.resourceserver.service;

public interface PermissionService {

    boolean checkPermission(final String userRole, final String resource, final String operation);
}
