package com.pet.hotel.businessLogic.utils;

import java.util.regex.Pattern;

public class Validation {

    public static boolean isEmailCorrect(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pattern.matcher(email).matches();
    }

    public static boolean isPasswordCorrect(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";

        Pattern pattern = Pattern.compile(passwordRegex);
        if (password == null)
            return false;
        return pattern.matcher(password).matches();
    }
}
