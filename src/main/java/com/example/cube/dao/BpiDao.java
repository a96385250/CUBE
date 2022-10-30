package com.example.cube.dao;

import com.example.cube.dto.Bpi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface BpiDao extends JpaRepository<Bpi, Integer>{

    List<Bpi> findByCode(String code);

    @Modifying
    @Transactional
    @Query(value = "UPDATE BPI SET RATE=:rate, DESCRIPTION=:desc,  RATEFLOAT=:rateFloat WHERE ID=:id", nativeQuery = true)
    void updateById(@Param("rate") String rate, @Param("desc") String desc, @Param("rateFloat") BigDecimal rateFloat, @Param("id") int id);



}
