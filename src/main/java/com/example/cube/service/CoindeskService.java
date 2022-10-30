package com.example.cube.service;

import com.example.cube.dao.CurrencyDao;
import com.example.cube.dto.Bpi;
import com.example.cube.dto.Currency;
import com.example.cube.model.CurrentInfo;
import com.example.cube.model.CurrentInfoData;
import com.example.cube.model.Time;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CoindeskService {

    @Autowired
    CurrencyDao currencyDao;

    public Time setTimeProcess(JSONObject jsonObject) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        Time time = objectMapper.readValue(jsonObject.get("time").toString(), Time.class);

        OffsetDateTime odt = OffsetDateTime.parse(time.getUpdatedISO());
        time.setUpdatedISO(odt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        time.setUpdateduk(time.getUpdateduk().replace("at",""));

        return time;
    }

    public List<CurrentInfoData> currentInfoData(JSONObject jsonObject){

        List<CurrentInfoData> currentInfoList = new ArrayList<>();

        Map<String,String> currencyMapData = currencyDao.findAll().stream()
                .collect(Collectors.toMap(Currency :: getCurrency, param -> param.getCurrencyCH()));


        Map<String, CurrentInfo> mapObj = new Gson().fromJson(
                jsonObject.get("bpi").toString(), new TypeToken<HashMap<String, CurrentInfo>>() {}.getType());

        mapObj.forEach((key,value)->{
            CurrentInfoData currentInfoData = CurrentInfoData.builder()
                    .code(value.getCode())
                    .codeCH(currencyMapData.get(value.getCode()))
                    .rate(value.getRate())
                    .rateFloat(value.getRate_float())
                    .build();
            currentInfoList.add(currentInfoData);
        });

        return currentInfoList;
    }
}
