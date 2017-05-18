package com.tba.vehiclemanagement.model;

public enum Direction {
    
    NORTH("NORTH"),
    EAST("EAST"),
    SOUTH("SOUTH"),
    WEST("WEST");

    private String direction;

    private Direction(String direction) {
        this.direction = direction;
    }
    
    public String getDirection() {
        return direction;
    }
    
    @Override
    public String toString() {
        return this.direction;
    }
}
