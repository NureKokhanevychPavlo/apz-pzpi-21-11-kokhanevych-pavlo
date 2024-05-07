package com.pet.hotel.services.utils;


import com.pet.hotel.businessLogic.utils.EmailSender;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailSenderTest {

    @Test
    void testSendEmail() {
        // Arrange
        EmailSender emailSender = new EmailSender();
        String emailTo = "pavlo.kokhanevych@nure.ua";
        String body = "Test message";

        // Act & Assert
        assertDoesNotThrow(() -> emailSender.sendEmail(emailTo, body));
    }
}
