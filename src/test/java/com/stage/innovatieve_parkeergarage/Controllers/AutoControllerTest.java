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
class AutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllAutos() throws Exception {
        String uri = "http://localhost:8080/allauto/get/N9MXWfxo8Re%2FuD8nj13qHA==/UQlL0Cnvg6oyj7J4gh8KoKHywPhIsiSYiY4wvo4jlJfQwIjRrWQfCP8fmbmlTXQl";
        MvcResult mvcResult = mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
    }

    @Test
    void getAutoId() throws Exception {
        String uri = "http://localhost:8080/reserveringen/N9MXWfxo8Re%2FuD8nj13qHA==/4PlSfJj3PXz%2FLR%2Bqahuc7A==/nLdiqyrWM%2BkxxasxXscPF0Qt3sovUmjJ9S83NFcaU6LltU7mS4jaRXd0oRooDuSM";
        MvcResult mvcResult = mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
    }
}