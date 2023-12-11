package org.serrodcal.microcks.service;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.serrodcal.microcks.domain.Employee;

import java.util.Set;

@Path("/employee")
@RegisterRestClient
public interface EmployeeService {

    @GET
    public Uni<Set<Employee>> getEmployees();

    @GET
    @Path("/{id}")
    public Uni<Employee> getEmployee(@PathParam("id") Long id);

    @GET
    @Path("/department/{deptId}")
    public Uni<Set<Employee>> getEmployeesByDept(@PathParam("deptId") Long deptId);

    @POST
    public Uni<Employee> createEmployee(Employee employee);

    @PUT
    public Uni<Integer> updateEmployee(Employee employee);

    @DELETE
    public Uni<Boolean> deleteEmployee(@PathParam("id") Long id);

}
