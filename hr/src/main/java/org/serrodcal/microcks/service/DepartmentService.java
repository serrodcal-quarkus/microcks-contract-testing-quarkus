package org.serrodcal.microcks.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.serrodcal.microcks.domain.Department;

@Path("/department")
@RegisterRestClient(configKey="department-api")
public interface DepartmentService {

    @GET
    public Multi<Department> getDepartments();

    @GET
    @Path("/{id}")
    public Uni<Department> getDepartment(@PathParam("id") Long id);

}
