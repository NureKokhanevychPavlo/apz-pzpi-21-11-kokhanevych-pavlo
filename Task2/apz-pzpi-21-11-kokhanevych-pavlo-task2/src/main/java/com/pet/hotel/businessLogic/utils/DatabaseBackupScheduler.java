package com.pet.hotel.businessLogic.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DatabaseBackupScheduler {

    @Autowired
    private DatabaseManager databaseManager;

    private static final String DEFAULT_PATH = "E:\\CourseWork\\Back\\hotel\\hotel\\database\\backup.sql";

    @Scheduled(cron = "0 0 0 * * *") // Запускається о 00:00 кожного дня
    public void performBackup() {
        databaseManager.backupSuccessful(DEFAULT_PATH);
    }
}
