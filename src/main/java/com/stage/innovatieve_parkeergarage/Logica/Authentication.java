package com.stage.innovatieve_parkeergarage.Logica;

import java.util.UUID;

public class Authentication {

    public static String getToken(){
        String token = UUID.randomUUID().toString().toUpperCase();
        return token;
    }
}
