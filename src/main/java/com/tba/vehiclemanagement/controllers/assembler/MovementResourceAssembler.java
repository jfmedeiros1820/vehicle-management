package com.tba.vehiclemanagement.controllers.assembler;

import org.hibernate.Hibernate;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.tba.vehiclemanagement.controllers.MovementController;
import com.tba.vehiclemanagement.controllers.resources.MovementResource;
import com.tba.vehiclemanagement.model.Movement;

@Component
public class MovementResourceAssembler extends ResourceAssemblerSupport<Movement, MovementResource> {
    
    public MovementResourceAssembler() {
        super(MovementController.class, MovementResource.class);
    }

    @Override
    public MovementResource toResource(Movement movement) {
        MovementResource resource = createResourceWithId(movement.getId(), movement);
        resource.setCreation(movement.getCreation());
        resource.setDirection(movement.getDirection());
        if (Hibernate.isInitialized(movement.getVehicle())) {
            resource.setVehicle(movement.getVehicle());
        }
        return resource;
    }
    
}
