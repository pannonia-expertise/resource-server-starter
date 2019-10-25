package com.pannoniaexpertise.resourceserver.model;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "permission")
public class Permission implements Serializable {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "operation_id", nullable = false)
    private Operation operation;
}
