package com.example.cube.contorller;

import com.example.cube.dto.Bpi;
import com.example.cube.service.BpiService;
import com.example.cube.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private BpiService bpiService;



    @GetMapping("currency")
    public String currency(Model model) {

        List<String> currencyList = currencyService.getCurrencyData();
        List<Bpi> bpiList = bpiService.getBpiData("all");

        model.addAttribute("currencyList", currencyList);
        model.addAttribute("bpiSize", bpiList.size());

        return "currency/main";
    }

    @GetMapping("search")
    public String search(HttpServletRequest request, Model model) {

        String currency = request.getParameter("currency");

        List<String> currencyList = currencyService.getCurrencyData();
        List<Bpi> bpiList = bpiService.getBpiData(currency);

        model.addAttribute("currencyData", bpiList);
        model.addAttribute("currencyList", currencyList);
        model.addAttribute("bpiSize", bpiList.size());

        return "currency/main";
    }


    @GetMapping("createData")
    public String createData(HttpServletRequest request, Model model) {

        List<Bpi> bpiList = bpiService.getBpiData("all");

        List<String> createCurrencyData = currencyService.createCurrencyList(bpiList);

        model.addAttribute("type", "create");
        model.addAttribute("data", Bpi.builder().build());
        model.addAttribute("currencyData", createCurrencyData);

        return "currency/create";
    }

    @GetMapping("updateData")
    public String updateData(HttpServletRequest request, Model model) {

        List<String> curencyDataList = new ArrayList<>();
        String id = request.getParameter("id");
        Bpi bpi = bpiService.getBpiData(Integer.parseInt(id));

        model.addAttribute("type", "update");
        model.addAttribute("data", bpi);
        model.addAttribute("currencyData", curencyDataList);

        return "currency/create";
    }

    @PostMapping("create")
    public String create(HttpServletRequest request) {

        bpiService.createProcess(request);

        return "redirect:currency";
    }


    @PostMapping("update-{id}")
    public String update(@PathVariable String id, HttpServletRequest request) {

        bpiService.updateProcess(request,Integer.parseInt(id));

        return "redirect:currency";
    }

    @PostMapping("delete-{id}")
    public String delete(@PathVariable String id, HttpServletRequest request, Model model) {

        bpiService.deleteProcess(Integer.parseInt(id));

        return "redirect:currency";
    }


}
