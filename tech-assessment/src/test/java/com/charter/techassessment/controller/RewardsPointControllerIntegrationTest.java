package com.charter.techassessment.controller;

import com.charter.techassessment.configuration.RewardsPointControllerTestConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(RewardsPointsController.class)
@ContextConfiguration(classes = {RewardsPointControllerTestConfiguration.class})
public class RewardsPointControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void basicTest(){
        try{
            mockMvc.perform(MockMvcRequestBuilders.get("/rewards/calculate").content("{\n" +
                    "    \"transactions\":[{\"transactionId\":12345,\n" +
                    "    \"amount\":123.45,\n" +
                    "    \"transactionDate\":\"2021-11-11\"},\n" +
                    "    {\"transactionId\":12346,\n" +
                    "    \"amount\":123.46,\n" +
                    "    \"transactionDate\":\"2021-11-12\"}]\n" +
                    "}").contentType("application/json").accept("application/json")).
                    andExpect(MockMvcResultMatchers.status().isAccepted()).
                    andExpect(MockMvcResultMatchers.content().contentType("application/json")).
                    andExpect(MockMvcResultMatchers.content().json("{\n" +
                            "    \"monthlyPointsMap\": {\n" +
                            "        \"NOVEMBER\": 192\n" +
                            "    },\n" +
                            "    \"total\": 192,\n" +
                            "    \"invalidTransactions\": []\n" +
                            "}"));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void edgeCases(){
        try{
            mockMvc.perform(MockMvcRequestBuilders.get("/rewards/calculate").content("{\n" +
                            "    \"transactions\":[{\"transactionId\":1,\n" +
                            "    \"amount\":50.00,\n" +
                            "    \"transactionDate\":\"2021-10-11\"},\n" +
                            "    {\"transactionId\":2,\n" +
                            "    \"amount\":100.00,\n" +
                            "    \"transactionDate\":\"2021-11-12\"},\n" +
                            "     {\"transactionId\":3,\n" +
                            "    \"amount\":101.00,\n" +
                            "    \"transactionDate\":\"2022-01-12\"}\n" +
                            "    ]\n" +
                            "}").contentType("application/json").accept("application/json")).
                    andExpect(MockMvcResultMatchers.status().isAccepted()).
                    andExpect(MockMvcResultMatchers.content().contentType("application/json")).
                    andExpect(MockMvcResultMatchers.content().json("{\n" +
                            "    \"monthlyPointsMap\": {\n" +
                            "        \"JANUARY\": 52,\n" +
                            "        \"OCTOBER\": 0,\n" +
                            "        \"NOVEMBER\": 50\n" +
                            "    },\n" +
                            "    \"total\": 102,\n" +
                            "    \"invalidTransactions\": []\n" +
                            "}"));
        }catch (Exception e) {
            Assert.fail();
        }

    }

    @Test
    public void oneGoodOneBad(){
        try{
            mockMvc.perform(MockMvcRequestBuilders.get("/rewards/calculate").content("{\n" +
                            "    \"transactions\":[{\"transactionId\":1,\n" +
                            "    \"amount\":125.00,\n" +
                            "    \"transactionDate\":\"2023-01-11\"},\n" +
                            "    {\"transactionId\":2,\n" +
                            "    \"amount\":-100.00,\n" +
                            "    \"transactionDate\":\"2022-11-12\"}\n" +
                            "    ]\n" +
                            "}").contentType("application/json").accept("application/json")).
                    andExpect(MockMvcResultMatchers.status().isAccepted()).
                    andExpect(MockMvcResultMatchers.content().contentType("application/json")).
                    andExpect(MockMvcResultMatchers.content().json("{\n" +
                            "    \"monthlyPointsMap\": {\n" +
                            "        \"JANUARY\": 100\n" +
                            "    },\n" +
                            "    \"total\": 100,\n" +
                            "    \"invalidTransactions\": [\n" +
                            "        2\n" +
                            "    ]\n" +
                            "}"));
        }catch (Exception e) {
            Assert.fail();
        }

    }

}
