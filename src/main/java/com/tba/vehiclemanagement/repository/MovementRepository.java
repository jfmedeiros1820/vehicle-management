package com.tba.vehiclemanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tba.vehiclemanagement.model.Movement;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("SELECT m FROM Movement m WHERE m.vehicle.id = :id")
    public Page<Movement> findLastMovementByVehicle(@Param("id") Long id, Pageable pageable);
    
}
