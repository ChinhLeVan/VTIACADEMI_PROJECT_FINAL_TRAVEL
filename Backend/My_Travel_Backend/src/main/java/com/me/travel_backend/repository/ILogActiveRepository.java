package com.me.travel_backend.repository;

import com.me.travel_backend.entity.LogActive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ILogActiveRepository extends JpaRepository<LogActive, Integer>, JpaSpecificationExecutor<LogActive> {

}
