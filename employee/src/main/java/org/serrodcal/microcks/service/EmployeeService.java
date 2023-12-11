package org.serrodcal.microcks.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.serrodcal.microcks.entity.Employee;
import org.serrodcal.microcks.repository.EmployeeRepository;

@ApplicationScoped
public class EmployeeService {

    @Inject
    EmployeeRepository employeeRepository;

    @CircuitBreaker(requestVolumeThreshold = 4)
    @WithSession
    public Uni<List<Employee>> getEmployees() { return employeeRepository.findAll().list(); }

    @CircuitBreaker(requestVolumeThreshold = 4)
    @WithSession
    public Uni<Employee> getEmployee(Long id) { return employeeRepository.findById(id); }

    @CircuitBreaker(requestVolumeThreshold = 4)
    @WithSession
    public Uni<List<Employee>> getEmployeesByDept(Long deptId) { return employeeRepository.findByDeptId(deptId); }

    @CircuitBreaker(requestVolumeThreshold = 4)
    @WithTransaction
    public Uni<Employee> createEmployee(Employee employee) { return employeeRepository.persistAndFlush(employee); }

    @CircuitBreaker(requestVolumeThreshold = 4)
    @WithTransaction
    public Uni<Integer> updateEmployee(Employee employee) { 
        return employeeRepository.update(
            "name = :name, deptId = :deptId", 
            Parameters.with("name", employee.name).and("deptId", employee.deptId)
        ); 
    }

    @CircuitBreaker(requestVolumeThreshold = 4)
    @WithTransaction
    public Uni<Boolean> deleteEmployee(Long id) { return employeeRepository.deleteById(id); }

}
