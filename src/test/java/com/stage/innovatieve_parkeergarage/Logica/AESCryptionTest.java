package com.stage.innovatieve_parkeergarage.Logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AESCryptionTest {

    @Test
    void decryptTest(){
        String decryption = AESCryption.decrypt("C/yFiFu3oNVUUVkolXdy8g==");
        assertEquals("Theather",decryption);
    }

    @Test
    void encryptTest(){
        String encryption = AESCryption.encrypt("Theather");
        assertEquals("C/yFiFu3oNVUUVkolXdy8g==",encryption);
    }

}