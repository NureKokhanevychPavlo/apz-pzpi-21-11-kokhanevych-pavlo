package com.pet.hotel.businessLogic.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class DatabaseManager {


    /** Uses "mysqldump" command for backup */
    public boolean backupSuccessful(String backupPath) {
        if (backupPath == null || backupPath.isEmpty()) return false;
        Random random = new Random();
        int randomNumber = random.nextInt(10000) + 1;

        ProcessBuilder processBuilder = new ProcessBuilder(
                "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump",
                "-u", Constants.DATABASE_USERNAME,
                "-p" + Constants.DATABASE_PASSWORD,
                "--add-drop-table",
                "--databases", "hotel",
                "-r", backupPath + LocalDate.now() + randomNumber + ".sql"
        );

        return createProcessSuccessful(processBuilder);
    }

    /** Uses "mysql" command for restoring */
    public boolean restoreSuccessful(String backupPath) {
        if (backupPath == null || backupPath.isEmpty()) return false;

        ProcessBuilder processBuilder = new ProcessBuilder(
                "mysql",
                "-u" + Constants.DATABASE_USERNAME,
                "-p" + Constants.DATABASE_PASSWORD,
                "hotel",
                "-e", "source " + backupPath
        );
        return createProcessSuccessful(processBuilder);
    }

    private boolean createProcessSuccessful(ProcessBuilder processBuilder) {
        try {
            Process process = processBuilder.start();
            int processComplete = process.waitFor();
            return processComplete == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
