package com.tba.vehiclemanagement.controllers.resources;

import java.awt.Dimension;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name" })
public class VehicleResource extends ResourceSupport {
    
    private String name;

    private int speed;
    
    private Dimension size;

    private int latitude;

    private int longitude;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public Dimension getSize() {
        return size;
    }
    
    public void setSize(Dimension size) {
        this.size = size;
    }
    
    public int getLatitude() {
        return latitude;
    }
    
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
    
    public int getLongitude() {
        return longitude;
    }
    
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}
