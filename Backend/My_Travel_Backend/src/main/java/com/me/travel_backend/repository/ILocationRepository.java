package com.me.travel_backend.repository;

import com.me.travel_backend.entity.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ILocationRepository extends JpaRepository<Location, Integer>, JpaSpecificationExecutor<Location> {

    Location findByLocation(String locationName);
}
