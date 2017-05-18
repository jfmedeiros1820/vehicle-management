package com.tba.vehiclemanagement.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tba.vehiclemanagement.model.Movement;
import com.tba.vehiclemanagement.model.Vehicle;
import com.tba.vehiclemanagement.repository.MovementRepository;
import com.tba.vehiclemanagement.service.MovementService;
import com.tba.vehiclemanagement.service.VehicleService;

@Service
@Transactional
public class MovementServiceImpl implements MovementService {
    
    private static final Logger logger = LoggerFactory.getLogger(MovementService.class);

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public void move(Long id, String direction) {
        moveVehicle(id, direction);
        logger.info("Vehicle moved");
    }

    /**
     * Method to move the vehicle according to the id and direction.
     *
     * @param id
     *            to retrieve the vehicle
     * @param direction
     *            to move the vehicle
     */
    private void moveVehicle(Long id, String direction) {
        logger.info("The vehicle will be moved");
        Vehicle vehicle = vehicleService.getVehicle(id);
        vehicle.move(direction);
        vehicleService.save(vehicle);
    }

    @Override
    public Movement findLastMovement(Long id) {
        Pageable pageable = new PageRequest(0, 1, Sort.Direction.DESC, "creation");
        Page<Movement> movements = movementRepository.findLastMovementByVehicle(id, pageable);
        if (CollectionUtils.isEmpty(movements.getContent())) {
            logger.info("The vehicle was never moved");
            return null;
        }
        logger.info("Returning the movement of the vehicle");
        return movements.getContent().get(0);
    }
}
