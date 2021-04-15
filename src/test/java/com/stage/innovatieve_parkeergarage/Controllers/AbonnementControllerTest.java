package com.stage.innovatieve_parkeergarage.Controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AbonnementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void abonnementParkeergarageTest() throws Exception {
        String uri = "/abonnement/90YovTJe5pOrNLXtTgx28w==/N9MXWfxo8Re%2FuD8nj13qHA==/aJ4QRCjxKZxYMxIdJLhD8bYjfMvfk%2F64FGHx8K3O4tHMaZq9uCs0i%2FMi8QSiQ7mX";
        MvcResult mvcResult = mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
    }

    @Test
    void abonnementGebruikerTest() throws Exception {
        String uri = "/abonnement/get/N9MXWfxo8Re%2FuD8nj13qHA==/N9MXWfxo8Re%2FuD8nj13qHA==/aJ4QRCjxKZxYMxIdJLhD8bYjfMvfk%2F64FGHx8K3O4tHMaZq9uCs0i%2FMi8QSiQ7mX";
        MvcResult mvcResult = mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
    }

}