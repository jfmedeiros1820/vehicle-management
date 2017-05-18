package com.tba.vehiclemanagement.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.tba.vehiclemanagement.model.Vehicle;

@Component
public class VehicleMovementSender {

    private static final Logger logger = LoggerFactory.getLogger(VehicleMovementSender.class);
    
    private static final String VEHICLE_MOVE_QUEUE = "queue.vehicle.move";

    @Autowired
    private JmsTemplate jmsTemplate;
    
    /**
     * Method to send the vehicle to move queue.
     *
     * @param vehicle
     *            to be sent
     * @param direction
     *            to move the vehicle
     */
    public void send(Vehicle vehicle, String direction) {
        logger.info("Starting the movement of the vehicle " + vehicle.getId());
        jmsTemplate.convertAndSend(VEHICLE_MOVE_QUEUE, vehicle.getId() + ";" + direction);
        logger.info("Message for vehicle " + vehicle.getId() + " sent");
    }

}
