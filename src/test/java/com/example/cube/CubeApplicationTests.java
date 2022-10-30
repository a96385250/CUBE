package com.example.cube;

import com.example.cube.contorller.CurrencyController;
import com.example.cube.dto.Bpi;
import com.example.cube.service.BpiService;
import com.example.cube.service.CurrencyService;
import javafx.application.Application;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest()
class CubeApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private CurrencyController currencyController;

    @MockBean
    private CurrencyService currencyService;

    private List<String> currencyList;

    private List<Bpi> bpiList;

    @MockBean
    private BpiService bpiService;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.currencyController).build();
        currencyList.add("EUR");
        currencyList.add("GBP");
        currencyList.add("USD");

        bpiList.add(Bpi.builder()
                .id(1)
                .currencyID(1)
                .code("USD")
                .rate("1.2")
                .description("A")
                .ratefloat(new BigDecimal("1.2")).build());
    }

    @Test
    void contextLoads() throws Exception {


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/search").accept(MediaType.APPLICATION_JSON)).andReturn();
    }

}
