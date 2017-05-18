package com.tba.vehiclemanagement.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tba.vehiclemanagement.controllers.assembler.MovementResourceAssembler;
import com.tba.vehiclemanagement.controllers.exceptions.ResourceNotFoundExpection;
import com.tba.vehiclemanagement.controllers.resources.MovementResource;
import com.tba.vehiclemanagement.messaging.VehicleMovementSender;
import com.tba.vehiclemanagement.model.Movement;
import com.tba.vehiclemanagement.model.Vehicle;
import com.tba.vehiclemanagement.service.MovementService;
import com.tba.vehiclemanagement.service.VehicleService;

@Path("/movement")
public class MovementController {
    
    private static final Logger logger = LoggerFactory.getLogger(MovementController.class);

    @Autowired
    private MovementService movementService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private MovementResourceAssembler assembler;

    @Autowired
    private VehicleMovementSender vehicleMovementSender;

    @PUT
    @Path("/vehicle/{id : \\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response move(@PathParam("id") Long id, @QueryParam("direction") String direction) {
        logger.info("move ID: {} ", id, direction);
        Vehicle vehicle = vehicleService.getVehicle(id);
        if (vehicle == null) {
            throw new ResourceNotFoundExpection(String.format("Vehicle ID: %d, Not Found.", id));
        }
        vehicleMovementSender.send(vehicle, direction);
        return Response.ok().build();
    }

    @GET
    @Path("/vehicle/{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovementResource getLastMovementByVehicle(@PathParam("id") Long id) {
        logger.info("getLastMovementByVehicle : {} ", id);
        Vehicle vehicle = vehicleService.getVehicle(id);
        if (vehicle == null) {
            throw new ResourceNotFoundExpection(String.format("Vehicle ID: %d, Not Found.", id));
        }
        Movement movement = movementService.findLastMovement(id);
        MovementResource resource = assembler.toResource(movement);
        return resource;
    }
}
