package com.stage.innovatieve_parkeergarage.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutoTest {

    Auto auto = new Auto(1,"280-BB-1"); //Arrange

    @Test
    void getAuto_Id() {
        int id = auto.getAuto_Id(); //Act
        assertEquals(1,id); //Assert
    }

    @Test
    void getAuto_Kenteken() {
        String kenteken = auto.getAuto_Kenteken(); //Act
        assertEquals("280-BB-1",kenteken);
    }
}