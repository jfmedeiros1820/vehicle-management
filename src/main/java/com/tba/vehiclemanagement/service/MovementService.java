package com.tba.vehiclemanagement.service;

import com.tba.vehiclemanagement.model.Movement;

public interface MovementService {
    
    /**
     * Method to move the vehicle from a direction to another.
     *
     * @param id
     *            of the vehicle
     * @param direction
     *            to move the vehicle
     */
    public void move(Long id, String direction);

    /**
     * Method to find the last movement of a vehicle.
     *
     * @param id
     *            of the vehicle.
     *
     * @return the last movement.
     */
    public Movement findLastMovement(Long id);
}
