package com.pannoniaexpertise.resourceserver.service;

import com.pannoniaexpertise.resourceserver.exception.ResourceServerBadRequestException;
import com.pannoniaexpertise.resourceserver.model.Permission;
import com.pannoniaexpertise.resourceserver.repository.PermissionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService {

    private final PermissionsRepository repository;

    /**
     * Map of cached permissions. Indexed by resource name.
     * < resourceName, permissionsList >
     */
    private Map<String, List<Permission>> permissions;

    public PermissionServiceImpl(final PermissionsRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        cachePermissions();
    }

    /**
     * Load all permissions from repository and store them locally for faster access.
     */
    private void cachePermissions() {
        permissions = new HashMap<>();

        final List<Permission> all = repository.findAll();
        for (final Permission permission : all) {
            final String resourceName = permission.getResource().getName();
            List<Permission> list = permissions.computeIfAbsent(resourceName, k -> new ArrayList<>());
            list.add(permission);
        }

        log.info("Cached {} permissions for {} resources. ", all.size(), permissions.size());
    }

    @Override
    public boolean checkPermission(final String userRole, final String resource, final String operation) {
        final List<Permission> list = permissions.get(resource);
        if (list == null) {
            throw new ResourceServerBadRequestException("Resource '" + resource + "' not found");
        }
        for (final Permission permission : list) {
            if (permission.getRole().getName().compareTo(userRole) == 0 &&
                    permission.getOperation().getName().compareTo(operation) == 0) {

                return true;
            }
        }
        return false;
    }
}
