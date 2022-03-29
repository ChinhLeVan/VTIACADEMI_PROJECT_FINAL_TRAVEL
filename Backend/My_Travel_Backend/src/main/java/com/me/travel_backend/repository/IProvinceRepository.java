package com.me.travel_backend.repository;

import com.me.travel_backend.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface IProvinceRepository extends JpaRepository<Province, Integer>, JpaSpecificationExecutor<Province> {

    @Modifying
    @Transactional
    @Query(value = "SELECT id "
            + "FROM Province "
            +"WHERE nameProvince "
            +"LIKE  %:nameProvince%" ,nativeQuery = true)
    int getIdByNameProvince(String nameProvince);

    Province findByNameProvince(String nameProvince);
}
