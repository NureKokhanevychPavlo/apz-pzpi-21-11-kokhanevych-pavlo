package com.pet.hotel.businessLogic.services;

import com.pet.hotel.businessLogic.domain.interfaces.DatabaseService;
import com.pet.hotel.businessLogic.utils.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private DatabaseManager databaseManager;

    @Override
    @Async
    public boolean doBackupDatabase(byte[] backupPathHash) {
        String backupPathDecoded = decodeBase64(backupPathHash);
        DatabaseManager databaseManager = new DatabaseManager();
        return databaseManager.backupSuccessful(backupPathDecoded);
    }

    @Override
    @Async
    public boolean restoreDatabase(byte[] backupPathHash) {
        String backupPathDecoded = decodeBase64(backupPathHash);
        DatabaseManager databaseManager = new DatabaseManager();
        return databaseManager.restoreSuccessful(backupPathDecoded);
    }

    private String decodeBase64(byte[] encodedBytes) {
        return new String(Base64.getDecoder().decode(encodedBytes), StandardCharsets.UTF_8);
    }
}
