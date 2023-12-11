package org.serrodcal.microcks.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.serrodcal.microcks.entity.Department;
import org.serrodcal.microcks.repository.DepartmentRepository;


@ApplicationScoped
public class DepartmentService {

    @Inject
    DepartmentRepository departmentRepository;

    @CircuitBreaker(requestVolumeThreshold = 4)
    @WithSession
    public Uni<List<Department>> getDepartments() { return departmentRepository.findAll().list(); }

    @CircuitBreaker(requestVolumeThreshold = 4)
    @WithSession
    public Uni<Department> getDepartment(Long id) { return departmentRepository.findById(id); }

    @CircuitBreaker(requestVolumeThreshold = 4)
    @WithTransaction
    public Uni<Department> createDepartment(Department department) { return departmentRepository.persistAndFlush(department); }

    @CircuitBreaker(requestVolumeThreshold = 4)
    @WithTransaction
    public Uni<Integer> updateDepartment(Department department) { 
        return departmentRepository.update(
            "name = :name", 
            Parameters.with("name", department.name)
        ); 
    }

    @CircuitBreaker(requestVolumeThreshold = 4)
    @WithTransaction
    public Uni<Boolean> deleteDepartment(Long id) { return departmentRepository.deleteById(id); }

}
