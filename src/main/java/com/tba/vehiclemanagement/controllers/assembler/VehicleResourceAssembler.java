package com.tba.vehiclemanagement.controllers.assembler;

import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.tba.vehiclemanagement.controllers.VehicleController;
import com.tba.vehiclemanagement.controllers.resources.VehicleResource;
import com.tba.vehiclemanagement.model.Vehicle;

@Component
public class VehicleResourceAssembler extends ResourceAssemblerSupport<Vehicle, VehicleResource> {

    private static final String CONTROLLER_URI = "vehicle/";

    public VehicleResourceAssembler() {
        super(VehicleController.class, VehicleResource.class);
    }
    
    @Override
    public VehicleResource toResource(Vehicle vehicle) {
        VehicleResource resource = createResourceWithId(CONTROLLER_URI + vehicle.getId(), vehicle);
        resource.setName(vehicle.getName());
        resource.setSize(vehicle.getSize());
        resource.setSpeed(vehicle.getSpeed());
        resource.setLatitude(vehicle.getLatitude());
        resource.setLongitude(vehicle.getLongitude());
        resource.add(JaxRsLinkBuilder.linkTo(VehicleController.class).withSelfRel());
        return resource;
    }

}
