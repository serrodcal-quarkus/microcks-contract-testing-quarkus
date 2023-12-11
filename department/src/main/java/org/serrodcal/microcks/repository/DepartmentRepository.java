package org.serrodcal.microcks.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import org.serrodcal.microcks.entity.Department;

@ApplicationScoped
public class DepartmentRepository implements PanacheRepository<Department> {}
