package org.serrodcal.microcks.resource;

import io.quarkus.vertx.web.Param;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.quarkus.vertx.web.Route.HttpMethod;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;
import org.serrodcal.microcks.service.HRService;

@ApplicationScoped
@RouteBase(path = "api/v1")
public class HRResource {

    private static final Logger logger = Logger.getLogger(HRResource.class);

    @Inject
    HRService hrService;

    @Route(path = "hr/employee/:employeeId/assign/department/:deptId", methods = HttpMethod.POST)
    @Timeout(1000)
    Uni<Integer> assignEmployeeToDept(@Param Long employeeId, @Param Long deptId) {
        logger.info("assignEmployeeToDept with [employeeId:" + employeeId + ", dept:" + deptId + "]");
        return this.hrService.assignEmployeeToDept(employeeId, deptId);
    }

    @Route(path = "hr/employee/:employeeId/unassign", methods = HttpMethod.DELETE)
    @Timeout(1000)
    Uni<Integer> unassignEmployee(@Param Long id) {
        logger.info("unassignEmployeeToDept wit [id:" + id + "]");
        return this.hrService.unassignEmployee(id);
    }

}
