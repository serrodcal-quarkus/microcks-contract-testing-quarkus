package org.serrodcal.microcks.repository;

import java.util.List;

import org.serrodcal.microcks.entity.Employee;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

    public Uni<List<Employee>> findByDeptId(Long deptId)  {
        return find("deptId", deptId).list();
    }

}