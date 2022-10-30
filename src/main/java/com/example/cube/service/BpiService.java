package com.example.cube.service;

import com.example.cube.dao.BpiDao;
import com.example.cube.dao.CurrencyDao;
import com.example.cube.dto.Bpi;
import com.example.cube.dto.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BpiService {

    @Autowired
    CurrencyDao currencyDao;

    @Autowired
    BpiDao bpiDao;

    public List<Bpi> getBpiData(String currency){

        List<Bpi> bpiList = new ArrayList<>();

        if(currency.equals("all")){
            bpiList = bpiDao.findAll();
        }else {
            bpiList = bpiDao.findByCode(currency);
        }

        return bpiList;
    }

    @Transactional
    public void createProcess(HttpServletRequest request){

        String currency = request.getParameter("currency");
        String rate = request.getParameter("rate");
        String desc = request.getParameter("desc");
        String ratefloat = request.getParameter("ratefloat");

        Currency currencyData = currencyDao.findByCurrency(currency);

        Bpi bpi = Bpi.builder()
                .currencyID(currencyData.getId())
                .code(currency)
                .rate(rate)
                .description(desc)
                .ratefloat(new BigDecimal(ratefloat)).build();

        bpiDao.save(bpi);
    }

    public Bpi getBpiData(int id){
        return bpiDao.findById(id).get();
    }

    @Transactional
    public void updateProcess(HttpServletRequest request, int id){
        String rate = request.getParameter("rate");
        String desc = request.getParameter("desc");
        String ratefloat = request.getParameter("ratefloat");

        bpiDao.updateById(rate,desc, new BigDecimal(ratefloat),id);
    }

    public void deleteProcess(int id){
        bpiDao.deleteById(id);
    }

}
