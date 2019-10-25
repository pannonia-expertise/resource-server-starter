package com.pannoniaexpertise.resourceserver.repository;

import com.pannoniaexpertise.resourceserver.model.Permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionsRepository extends JpaRepository<com.pannoniaexpertise.resourceserver.model.Permission, Long> {

    @Query("SELECT a FROM Permission AS a " +
            "JOIN a.role AS b " +
            "JOIN a.operation AS c " +
            "WHERE b.name = :role AND c.name = :operation")
    List<Permission> findByRoleAndOperation(@Param("role") final String roleName,
                                            @Param("operation") final String operation);
}
