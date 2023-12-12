package org.serrodcal.microcks.resource;

import io.quarkus.vertx.web.Body;
import io.quarkus.vertx.web.Param;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.quarkus.vertx.web.Route.HttpMethod;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;
import org.serrodcal.microcks.entity.Employee;
import org.serrodcal.microcks.service.EmployeeService;

@ApplicationScoped
@RouteBase(path = "api/v1", produces = "application/json")
public class EmployeeResource {

    private static final Logger logger = Logger.getLogger(EmployeeResource.class);

    @Inject
    EmployeeService employeeService;

    @Route(path = "employee", methods = HttpMethod.GET)
    @Timeout(1000)
    Uni<List<Employee>> getEmployees() {
        logger.info("getEmployees");
        return this.employeeService.getEmployees();
    }


    @Route(path = "employee/:id", methods = HttpMethod.GET)
    @Timeout(1000)
    Uni<Employee> getEmployee(@Param Long id) {
        logger.info("getEmployee with [id:" + id.toString() + "]");
        return this.employeeService.getEmployee(id);
    }

    @Route(path = "employee/department/:deptId", methods = HttpMethod.GET)
    @Timeout(1000)
    Uni<List<Employee>> getEmployeesByDept(@Param Long deptId) {
        logger.info("getEmployeesByDept with [deptId: " + deptId.toString() + "]");
        return this.employeeService.getEmployeesByDept(deptId);
    }

    @Route(path = "employee", methods = HttpMethod.POST)
    @Timeout(1000)
    Uni<Employee> createEmployee(@Body Employee employee) {
        logger.info("createEmployee with [name:" + employee.name + "]");
        return this.employeeService.createEmployee(employee);
    }

    @Route(path = "employee", methods = HttpMethod.PUT)
    @Timeout(1000)
    Uni<Integer> updateEmployee(@Body Employee employee) {
        logger.info("updateEmployee with [name:" + employee.name + "]");
        return this.employeeService.updateEmployee(employee);
    }

    @Route(path = "employee/:id", methods = HttpMethod.DELETE)
    @Timeout(1000)
    Uni<Boolean> deleteEmployee(@Param Long id) {
        logger.info("deleteEmployee wit [id:" + id.toString() + "]");
        return this.employeeService.deleteEmployee(id);
    }

}
