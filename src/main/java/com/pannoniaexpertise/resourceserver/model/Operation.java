package com.pannoniaexpertise.resourceserver.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "operation")
public class Operation implements Serializable {

    @Id
    private Long id;

    @NotEmpty
    private String name;
}
