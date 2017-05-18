package com.tba.vehiclemanagement.controllers;

import java.util.List;
import java.util.concurrent.Future;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import com.tba.vehiclemanagement.controllers.assembler.VehicleResourceAssembler;
import com.tba.vehiclemanagement.controllers.exceptions.ResourceNotFoundExpection;
import com.tba.vehiclemanagement.controllers.resources.VehicleResource;
import com.tba.vehiclemanagement.model.Vehicle;
import com.tba.vehiclemanagement.service.VehicleService;

@Path("/vehicle")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehicleController {

    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);
    
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleResourceAssembler assembler;

    /**
     * Create a Vehicle
     *
     * @param vehicle
     *            - Vahicle payload
     * @return - Link to Vahicle created
     */
    @POST
    public Response create(Vehicle vehicle, @Context UriInfo uriInfo) {
        logger.info("create: {}", vehicle);
        Long id = vehicleService.create(vehicle);
        logger.debug("vehicle create with id = ", id);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(id));
        return Response.created(builder.build()).build();
    }

    /**
     * Create Vehicles asynchronous
     *
     * @param vehicles
     *            - Vahicles payload
     * @return - Return all vehicles saved
     */
    @POST
    @Async
    @Path("/vehicles")
    public Future<List<Vehicle>> createVehicles(List<Vehicle> vehicles) {
        logger.info("createVehicles: {}", vehicles);
        vehicleService.createVehicles(vehicles);
        return new AsyncResult<>(vehicles);
    }
    
    /**
     * Get all the vehicles created.
     *
     * @return all vehicles
     */
    @GET
    public List<VehicleResource> getAllVehicles() {
        logger.info("getAllVehicles: {}");
        List<Vehicle> vehicles = vehicleService.getAll();
        List<VehicleResource> resources = assembler.toResources(vehicles);
        return resources;
    }
    
    @GET
    @Path("{id : \\d+}")
    public Response getVehicleById(@PathParam("id") Long id) {
        logger.info("getVehicleById : {}", id);
        Vehicle vehicle = vehicleService.getVehicle(id);
        if (vehicle == null) {
            throw new ResourceNotFoundExpection(String.format("Vehicle ID: %d, Not Found.", id));
        }
        VehicleResource resource = assembler.toResource(vehicle);
        return Response.ok(resource).build();
    }
}
