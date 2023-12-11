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
import org.serrodcal.microcks.entity.Department;
import org.serrodcal.microcks.service.DepartmentService;


@ApplicationScoped
@RouteBase(path = "api/v1", produces = "application/json")
public class DepartmentResource {

    private static final Logger logger = Logger.getLogger(DepartmentResource.class);

    @Inject
    DepartmentService departmentService;

    @Route(path = "department", methods = HttpMethod.GET)
    @Timeout(1000)
    @Retry(maxRetries = 4)
    Uni<List<Department>> getDepartments() {
        logger.info("getDepartments");
        return this.departmentService.getDepartments();
    }


    @Route(path = "department/:id", methods = HttpMethod.GET)
    @Timeout(1000)
    @Retry(maxRetries = 4)
    Uni<Department> getDepartment(@Param Long id) {
        logger.info("getDepartment with [id:" + id.toString() + "]");
        return this.departmentService.getDepartment(id);
    }

    @Route(path = "department", methods = HttpMethod.POST)
    @Timeout(1000)
    @Retry(maxRetries = 4)
    Uni<Department> createDepartment(@Body Department department) {
        logger.info("createDepartment with [name:" + department.name + "]");
        return this.departmentService.createDepartment(department);
    }

    @Route(path = "department", methods = HttpMethod.PUT)
    @Timeout(1000)
    @Retry(maxRetries = 4)
    Uni<Integer> updateDepartment(@Body Department department) {
        logger.info("updateDepartment with [name:" + department.name + "]");
        return this.departmentService.updateDepartment(department);
    }

    @Route(path = "department/:id", methods = HttpMethod.DELETE)
    @Timeout(1000)
    @Retry(maxRetries = 4)
    Uni<Boolean> deleteDepartment(@Param Long id) {
        logger.info("deleteDepartment wit [id:" + id.toString() + "]");
        return this.departmentService.deleteDepartment(id);
    }

}
