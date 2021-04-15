package com.stage.innovatieve_parkeergarage.Controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ReserveringControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getReserveringenGebruiker() throws Exception {
        String uri = "/reserveringen/90YovTJe5pOrNLXtTgx28w==/N9MXWfxo8Re%2FuD8nj13qHA==/xIas%2B9sSuoSyH%2B5Wis3p610yZb1%2B4rIVlj13LKx1qo%2FS9NPQ8fByMr5DIj%2FF0R%2Fj";
        MvcResult mvcResult = mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
    }
}