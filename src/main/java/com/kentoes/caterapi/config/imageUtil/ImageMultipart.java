package com.kentoes.caterapi.config.imageUtil;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public final class ImageMultipart implements ImageSource {
    private final MultipartFile multipartFile;

    public ImageMultipart(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public File asFile() throws IOException {
        File imageFile = Files.createTempFile("image_upload", ".tmp").toFile();
        multipartFile.transferTo(imageFile);
        return imageFile;
    }

    @Override
    public String asString() throws IOException {
        File file = asFile();
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
