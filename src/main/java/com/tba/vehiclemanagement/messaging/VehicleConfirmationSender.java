package com.tba.vehiclemanagement.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.tba.vehiclemanagement.model.Vehicle;

@Component
public class VehicleConfirmationSender {
    
    private static final Logger logger = LoggerFactory.getLogger(VehicleConfirmationSender.class);
    
    private static final String VEHICLE_CONFIRMATION_QUEUE = "queue.vehicle.confirmation";
    
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Method to send the vehicle to the queue vehicle.
     *
     * @param vehicle
     *            to be sent
     */
    public void send(Vehicle vehicle) {
        logger.info("Starting the send of the vehicle " + vehicle.getId());
        jmsTemplate.convertAndSend(VEHICLE_CONFIRMATION_QUEUE, vehicle);
        logger.info("Message for vehicle " + vehicle.getId() + " sent");
    }
}
