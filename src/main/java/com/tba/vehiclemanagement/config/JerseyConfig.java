package com.tba.vehiclemanagement.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.tba.vehiclemanagement.controllers.MovementController;
import com.tba.vehiclemanagement.controllers.VehicleController;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(VehicleController.class);
        register(MovementController.class);
    }
}
