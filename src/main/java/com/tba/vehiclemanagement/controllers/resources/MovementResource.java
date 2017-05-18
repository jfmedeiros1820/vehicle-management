package com.tba.vehiclemanagement.controllers.resources;

import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tba.vehiclemanagement.model.Direction;
import com.tba.vehiclemanagement.model.Vehicle;

@JsonPropertyOrder({ "creation" })
public class MovementResource extends ResourceSupport {

    private LocalDateTime creation;
    
    private Direction direction;

    private Vehicle vehicle;
    
    public LocalDateTime getCreation() {
        return creation;
    }
    
    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public Vehicle getVehicle() {
        return vehicle;
    }
    
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}
