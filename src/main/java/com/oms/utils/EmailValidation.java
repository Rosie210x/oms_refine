package com.oms.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailValidation {

    public void validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
    }
}
