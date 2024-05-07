package com.pet.hotel.businessLogic.utils;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PhotoParser {

    private static final String ACCESS_TOKEN = "";


    private final MultipartFile file;

    private String dropboxFilePath;

    public PhotoParser(MultipartFile multipartFile) {
        this.file = multipartFile;
    }

    public void savePhoto() {
        if (file == null || file.isEmpty() || file.getOriginalFilename() == null) return;

        DbxRequestConfig config = DbxRequestConfig.newBuilder("Hotel for animal").build();
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            outputStream.write(file.getBytes());
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            String fileName = file.getOriginalFilename();

            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
            FileMetadata metadata = client.files().uploadBuilder("/" + fileName)
                    .uploadAndFinish(inputStream);

            dropboxFilePath = metadata.getPathDisplay();
        } catch (IOException | DbxException e) {
            e.printStackTrace();
        }
    }

    public String getDropboxFilePath() {
        return dropboxFilePath;
    }

    public static byte[] pullPhoto(String dropboxFilePath) {
        if (dropboxFilePath != null && !dropboxFilePath.isEmpty()) {
            DbxRequestConfig config = DbxRequestConfig.newBuilder("Hotel for animal").build();
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
                client.files().download(dropboxFilePath).download(outputStream);
                return outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DbxException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
