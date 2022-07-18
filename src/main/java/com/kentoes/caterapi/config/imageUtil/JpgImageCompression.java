package com.kentoes.caterapi.config.imageUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class JpgImageCompression implements ImageCompression {
    private String imagePathAsString;

    @Value("${images.path}")
    public void setImagePathAsString(String imagePathAsString) {
        this.imagePathAsString = System.getProperty("user.dir") + imagePathAsString;
    }

    @Override
    public void compress(ImageSource imageSource, String imageName) {
        try {
            Path imagesPath = Files.createDirectories(Paths.get(imagePathAsString));
            String compressedImageFileName = imageName + "." + JpgImage.EXTENSION;
            File compressedImageFile = imagesPath.resolve(compressedImageFileName).toFile();
            File imageSourceFile = imageSource.asFile();
            new JpgImage(imageSourceFile, new SquareCompressed(200)).compressTo(compressedImageFile);
            imageSourceFile.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
