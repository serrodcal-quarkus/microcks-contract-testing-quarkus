package org.serrodcal.microcks.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Cacheable
public class Department extends PanacheEntity {

    @Column(nullable = false)
    public String name;

}
