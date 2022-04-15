package com.example.secbuyservice.util;

import java.util.UUID;

public class IdGenerate {

    public static String generateId() {
        String replaceUUID = UUID.randomUUID().toString().replace("-", "");
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        return replaceUUID + currentTimeMillis;
    }

}
