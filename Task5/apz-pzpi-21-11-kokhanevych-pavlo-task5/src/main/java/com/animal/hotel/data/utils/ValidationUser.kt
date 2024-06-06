package com.animal.hotel.data.utils


import java.time.LocalDate
import java.time.Period
import java.util.regex.Pattern

class ValidationUser {

    companion object {

        private const val MIN_AGE: Int = 16

        fun isEmailCorrect(email: String?): Boolean {
            val pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
            if (email == null) return false
            return pattern.matcher(email).matches()
        }

        fun isPasswordCorrect(password: String?): Boolean {
            val pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$")
            if (password == null) return false
            return pattern.matcher(password).matches()
        }

        fun isOldEnough(birthDate: LocalDate?): Boolean {
            if (birthDate == null) return false
            val age = Period.between(birthDate, LocalDate.now()).years
            return age >= MIN_AGE
        }

        fun isPhoneNumberCorrect(phoneNumber: String?): Boolean {
            if (phoneNumber == null) return false
            val phoneRegex = "^\\+?[0-9. ()-]{10,15}$"
            return phoneNumber.matches(phoneRegex.toRegex())
        }

        fun isPasswordsSame(password: String?, againPassword: String?): Boolean {
            if (password == null || againPassword == null) return false
            return password == againPassword
        }
    }
}