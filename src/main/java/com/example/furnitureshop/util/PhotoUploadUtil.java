package com.example.furnitureshop.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class PhotoUploadUtil {
    public static final String UPLOAD_DIR = "src/main/resources/static/image-Poster";

    public static void savePhoto(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path photoPath = uploadPath.resolve(fileName);
            Files.copy(inputStream, photoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ioe) {
            InputStream input = multipartFile.getInputStream();
            Path path = Paths.get(fileName);//check path
            OutputStream output = Files.newOutputStream(path);
            IOUtils.copy(input, output);
        }
    }
}
