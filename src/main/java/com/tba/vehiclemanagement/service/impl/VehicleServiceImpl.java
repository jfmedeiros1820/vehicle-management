package com.tba.vehiclemanagement.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.tba.vehiclemanagement.messaging.VehicleConfirmationSender;
import com.tba.vehiclemanagement.model.Vehicle;
import com.tba.vehiclemanagement.repository.VehicleRepository;
import com.tba.vehiclemanagement.service.VehicleService;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);

    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private VehicleConfirmationSender vehicleConfirmationSender;
    
    @Override
    public Long create(Vehicle vehicle) {
        if (isVehicleValid(vehicle)) {
            logger.debug("Vehicle " + vehicle.getName() + "is valid");
            return vehicleRepository.save(vehicle).getId();
        } else {
            logger.error("The vehicle is not valid");
            throw new BadRequestException("Vehicle is not valid");
        }
    }

    /**
     * Validation to check if the vehicle is valid to be persisted.
     *
     * @param vehicle
     *            to be validated
     *
     * @return true if vehicle is valid and false if it is not.
     */
    private boolean isVehicleValid(Vehicle vehicle) {
        if (StringUtils.isEmpty(vehicle.getName())) {
            return false;
        }
        return true;
    }
    
    @Override
    public void createVehicles(List<Vehicle> vehicles) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        vehicles.stream().forEach(vehicle -> {
            executor.submit(() -> {
                logger.info("Initiating thread to create the vehicle " + vehicle.getName());
                this.create(vehicle);
                vehicleConfirmationSender.send(vehicle);
                logger.info("Finalizing thread to create the vehicle " + vehicle.getName());
            });
        });
        executor.shutdown();
    }
    
    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }
    
    @Override
    public Vehicle getVehicle(Long id) {
        return vehicleRepository.findOneById(id);
    }

    @Override
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

}
