package com.tba.vehiclemanagement.service;

import java.util.List;

import com.tba.vehiclemanagement.model.Vehicle;

public interface VehicleService {
    
    /**
     * Function to create a new vehicle.
     *
     * @param vehicle
     *            to be created
     *
     * @return the id of the vehicle created.
     */
    public Long create(Vehicle vehicle);
    
    /**
     * Method to create threads to save several vehicles asynchronously.
     *
     * @param vehicles
     *            to be saved
     */
    public void createVehicles(List<Vehicle> vehicles);
    
    /**
     * Get all the vehicles.
     *
     * @return all vehicles
     */
    public List<Vehicle> getAll();

    /**
     * Method to get a vehicle by its id.
     *
     * @param id
     *            to retrieve the vehicle
     * @return Vehicle found
     */
    public Vehicle getVehicle(Long id);

    /**
     * Method to save the vehicle after some change.
     *
     * @param vehicle
     *            to be saved
     */
    public void save(Vehicle vehicle);
    
}
