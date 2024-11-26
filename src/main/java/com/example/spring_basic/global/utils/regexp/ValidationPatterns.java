package com.example.spring_basic.global.utils.regexp;

public class ValidationPatterns {
    public static final String PASSWORD_REGEXP = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,15}$";
    public static final String LOGIN_ID_REGEXP = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{4,10}$";
}
