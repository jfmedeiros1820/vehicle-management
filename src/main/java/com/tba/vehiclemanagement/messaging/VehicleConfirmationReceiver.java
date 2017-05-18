package com.tba.vehiclemanagement.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.tba.vehiclemanagement.model.Vehicle;
import com.tba.vehiclemanagement.service.VehicleService;

@Component
public class VehicleConfirmationReceiver {
    
    private static final Logger logger = LoggerFactory.getLogger(VehicleConfirmationReceiver.class);
    
    private static final String VEHICLE_CONFIRMATION_QUEUE = "queue.vehicle.confirmation";
    
    @Autowired
    private VehicleService vehicleService;
    
    @JmsListener(destination = VEHICLE_CONFIRMATION_QUEUE)
    public void receiveConfirmationMessage(Vehicle vehicle) {
        Vehicle vehicleFromDB = vehicleService.getVehicle(vehicle.getId());
        if (vehicleFromDB == null) {
            logger.error("Vehicle " + vehicle.getId() + "was not correctly created");
        } else {
            logger.info("Vehicle " + vehicleFromDB.getId() + " created successfully!!");
        }
    }
}
