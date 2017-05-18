package com.tba.vehiclemanagement.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.tba.vehiclemanagement.model.Vehicle;
import com.tba.vehiclemanagement.service.MovementService;
import com.tba.vehiclemanagement.service.VehicleService;

@Component
public class VehicleMovementReceiver {
    
    private static final Logger logger = LoggerFactory.getLogger(VehicleMovementReceiver.class);

    private static final String VEHICLE_MOVE_QUEUE = "queue.vehicle.move";

    @Autowired
    private VehicleService vehicleService;
    
    @Autowired
    private MovementService movementService;

    @JmsListener(destination = VEHICLE_MOVE_QUEUE)
    public void receiveMovementMessage(String data) {
        String[] messageData = data.split(";");
        Vehicle vehicleFromDB = vehicleService.getVehicle(Long.parseLong(messageData[0]));
        if (vehicleFromDB == null) {
            logger.error("Vehicle " + messageData[0] + " does not exist");
        } else {
            movementService.move(Long.parseLong(messageData[0]), messageData[1]);
            logger.info("Vehicle " + vehicleFromDB.getId() + " moved successfully!!");
        }
    }
}
