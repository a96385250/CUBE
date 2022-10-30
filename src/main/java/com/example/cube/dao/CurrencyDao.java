package com.example.cube.dao;

import com.example.cube.dto.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyDao extends JpaRepository<Currency, Integer>{

    Currency findByCurrency(String currency);

}
