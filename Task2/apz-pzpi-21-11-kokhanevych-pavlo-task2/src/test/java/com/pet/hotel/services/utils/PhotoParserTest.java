package com.pet.hotel.services.utils;


import com.pet.hotel.businessLogic.utils.PhotoParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhotoParserTest {

    @Test
    void testSavePhoto() {
        // Arrange
        MockMultipartFile mockFile = new MockMultipartFile("test_7.jpg", "test_7.jpg", "image/jpeg", "test file contents".getBytes());

        // Act
        PhotoParser photoParser = new PhotoParser(mockFile);
        photoParser.savePhoto();

        // Assert
        assertNotNull(photoParser.getDropboxFilePath());
    }

    @Test
    void testPullPhoto() {
        // Arrange
        MockMultipartFile mockFile = new MockMultipartFile("test_push.jpg", "test_push.jpg", "image/jpeg", "test file contents".getBytes());
        PhotoParser photoParser = new PhotoParser(mockFile);
        photoParser.savePhoto();
        String dropboxFilePath = photoParser.getDropboxFilePath();

        // Act
        byte[] pulledContent = PhotoParser.pullPhoto(dropboxFilePath);

        // Assert
        assertNotNull(pulledContent);
        assertArrayEquals("test file contents".getBytes(), pulledContent);
    }
}
