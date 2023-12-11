package org.serrodcal.microcks.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.serrodcal.microcks.domain.Department;
import org.serrodcal.microcks.domain.Employee;

@ApplicationScoped
public class HRService {

    @Inject
    @RestClient
    EmployeeService employeeService;

    @Inject
    @RestClient
    DepartmentService departmentService;

    @CircuitBreaker(requestVolumeThreshold = 4)
    public Uni<Integer> assignEmployeeToDept(Long employeeId, Long deptId) {
        Uni<Department> department = this.departmentService.getDepartment(deptId);
        Uni<Employee> employee = this.employeeService.getEmployee(employeeId);

        return Uni.combine().all().unis(department, employee).asTuple().onItem().transformToUni(t -> {
            Employee empl = t.getItem2();
            empl.deptId = t.getItem1().id;
            return this.employeeService.updateEmployee(empl);
        });
    }

    @CircuitBreaker(requestVolumeThreshold = 4)
    public Uni<Integer> unassignEmployee(Long id) {
        return this.employeeService.getEmployee(id).onItem().transformToUni(empl -> {
            empl.deptId = null;
            return this.employeeService.updateEmployee(empl);
        });
    }

}
