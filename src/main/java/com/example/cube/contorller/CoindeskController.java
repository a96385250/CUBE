package com.example.cube.contorller;

import com.example.cube.dao.BpiDao;
import com.example.cube.dao.CurrencyDao;
import com.example.cube.model.CurrentInfoData;
import com.example.cube.model.Time;
import com.example.cube.service.BpiService;
import com.example.cube.service.CoindeskService;
import com.example.cube.service.CurrencyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.*;

@Controller
@Slf4j
public class CoindeskController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private BpiService bpiService;

    @Autowired
    private CoindeskService coindeskService;

    @Autowired
    private PersonsDao personsDao;

    @Autowired
    private CurrencyDao currencyDao;

    @Autowired
    private BpiDao bpiDao;


    @GetMapping("coindesk")
    public String coindesk(Model model) {

        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);

        model.addAttribute("coindeskData", result);

        return "coindesk/coindeskApi";
    }

    @GetMapping("coindeskInfo")
    public String coindeskInfo(Model model) throws JsonProcessingException, ParseException {



        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);

        JSONObject jsonObject = new JSONObject(result);

        Time time = coindeskService.setTimeProcess(jsonObject);

        List<CurrentInfoData> currentInfoList = coindeskService.currentInfoData(jsonObject);

        model.addAttribute("timeData", time);
        model.addAttribute("currentInfoList", currentInfoList);

        return "coindesk/coindeskInfo";
    }



}
