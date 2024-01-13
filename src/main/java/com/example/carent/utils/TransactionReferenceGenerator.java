package com.example.carent.utils;

import java.util.UUID;

public class TransactionReferenceGenerator {

        public static String generateReference(){
            String randomString= UUID.randomUUID().toString().substring(0, 8);
            return"CAR-"+randomString;

    }

}
