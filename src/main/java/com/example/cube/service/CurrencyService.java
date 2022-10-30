package com.example.cube.service;

import com.example.cube.dao.CurrencyDao;
import com.example.cube.dto.Bpi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CurrencyService {

    @Autowired
    CurrencyDao currencyDao;

    public List<String> getCurrencyData(){

        List<String> currency = currencyDao.findAll().stream()
                .map(param -> param.getCurrency())
                .collect(Collectors.toList());

        return currency;
    }

    public List<String> createCurrencyList(List<Bpi> bpiList){
        List<String> currencyList = this.getCurrencyData();

        List<String> bpiCodeList = bpiList.stream()
                .map(param -> param.getCode())
                .collect(Collectors.toList());

        List<String> createCurrencyData = currencyList.stream()
                .filter(param -> bpiCodeList.indexOf(param) == -1)
                .collect(Collectors.toList());

        return createCurrencyData;
    }


}
