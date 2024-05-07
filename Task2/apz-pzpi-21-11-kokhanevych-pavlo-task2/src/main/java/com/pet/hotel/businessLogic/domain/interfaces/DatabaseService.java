package com.pet.hotel.businessLogic.domain.interfaces;

public interface DatabaseService {

    boolean doBackupDatabase(byte[] backupPathHash);

    boolean restoreDatabase(byte[] backupPathHash);
}
