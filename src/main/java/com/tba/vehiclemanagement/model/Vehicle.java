package com.tba.vehiclemanagement.model;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vehicle implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private static final String RIGHT = "Right";
    private static final String LEFT = "Left";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @NotEmpty
    private String name;
    
    private int speed;

    private Dimension size;
    
    @JsonIgnore
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Movement> movements;
    
    private int latitude;
    
    private int longitude;

    public Vehicle() {
        this.size = new Dimension(3, 4);
        this.latitude = 0;
        this.longitude = 0;
        generateInitialMovement();
    }

    /**
     * Method to initialize the initial movement of the vehicle.
     */
    private void generateInitialMovement() {
        Movement movement = new Movement(Direction.NORTH, this);
        this.movements = new ArrayList<Movement>();
        this.movements.add(movement);
    }

    /**
     * Includes a new movement to the car. If it's the first move we also start
     * the vehicle speed.
     *
     * @param direction
     *            to be added.
     */
    public void move(String direction) {
        if (this.speed == 0) {
            accelerate();
        }
        if (RIGHT.equalsIgnoreCase(direction)) {
            turnRight();
        } else if (LEFT.equalsIgnoreCase(direction)) {
            turnLeft();
        }
    }

    /**
     * Method to move the vehicle to the left.
     */
    private void turnLeft() {
        getMovements()
                .sort((m1, m2) -> m2.getCreation().compareTo(m1.getCreation()));
        switch (getMovements().get(0).getDirection()) {
        case NORTH:
            Movement movementWest = new Movement(Direction.WEST, this);
            this.movements.add(movementWest);
            break;
        case WEST:
            Movement movementSouth = new Movement(Direction.SOUTH, this);
            this.movements.add(movementSouth);
            break;
        case SOUTH:
            Movement movementEast = new Movement(Direction.EAST, this);
            this.movements.add(movementEast);
            break;
        case EAST:
            Movement movementNorth = new Movement(Direction.NORTH, this);
            this.movements.add(movementNorth);
            break;
        }
    }
    
    /**
     * Method to move the vehicle to the right.
     */
    private void turnRight() {
        getMovements()
                .sort((m1, m2) -> m2.getCreation().compareTo(m1.getCreation()));
        switch (getMovements().get(0).getDirection()) {
        case NORTH:
            Movement movementEast = new Movement(Direction.EAST, this);
            this.movements.add(movementEast);
            break;
        case WEST:
            Movement movementNorth = new Movement(Direction.NORTH, this);
            this.movements.add(movementNorth);
            break;
        case SOUTH:
            Movement movementWest = new Movement(Direction.WEST, this);
            this.movements.add(movementWest);
            break;
        case EAST:
            Movement movementSouth = new Movement(Direction.SOUTH, this);
            this.movements.add(movementSouth);
            break;
        }
    }
    
    /**
     * Function to accelerate the vehicle.
     */
    public void accelerate() {
        this.speed = this.speed + 5;
    }

    /**
     * Function to break the vehicle.
     */
    public void brake() {
        this.speed = this.speed - 5;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public List<Movement> getMovements() {
        return movements;
    }
    
    public void setMovements(List<Movement> movements) {
        this.movements = movements;
    }

}
