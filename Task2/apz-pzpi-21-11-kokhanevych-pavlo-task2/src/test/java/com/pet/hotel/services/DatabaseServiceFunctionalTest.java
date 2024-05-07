package com.pet.hotel.services;

import com.pet.hotel.businessLogic.domain.interfaces.DatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DatabaseServiceFunctionalTest {

    @Autowired
    private DatabaseService databaseService;

    @Test
    public void doBackupDatabaseTest() {
        String backupPath = "E:\\CourseWork\\Back\\hotel\\hotel\\database\\backup";
        byte[] backupPathHash = Base64.getEncoder().encode(backupPath.getBytes());
        boolean result = databaseService.doBackupDatabase(backupPathHash);
        assertTrue(result);
    }

    @Test
    public void restoreDatabaseTest() {
        String backupPath = "E:\\CourseWork\\Back\\hotel\\hotel\\database\\backup2024-05-071023.sql";
        byte[] backupPathHash = Base64.getEncoder().encode(backupPath.getBytes());
        boolean result = databaseService.restoreDatabase(backupPathHash);
        assertTrue(result);
    }
}
