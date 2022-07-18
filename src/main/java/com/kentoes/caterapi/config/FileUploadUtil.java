package com.kentoes.caterapi.config;

import com.kentoes.caterapi.config.imageUtil.ImageFormat;
import org.apache.commons.io.FileUtils;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

public class FileUploadUtil {
    private static String BASE_PATH = System.getProperty("user.dir") + "/foto_cater/";

    private static ImageFormat format = null;

    public static boolean saveFile(Integer periode, String fileName, MultipartFile multipartFile) {
        boolean stat = false;
        File dir = new File(BASE_PATH + periode);
        if (!dir.exists()) dir.mkdir();
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = dir.toPath().resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            stat = true;
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException("Could not save image file: " + fileName, e);
        }
        return stat;
    }

    public static void rename(Integer periode, String oldName, String newName) {
        File oldFile = new File(BASE_PATH + periode + oldName);
        File newFile = new File(BASE_PATH + periode + newName);
        if (oldFile.renameTo(newFile)) {
            System.out.println("file rename to " + newFile.getName());
        }
    }

    public static void deleteFile(Integer periode, String fileName) {
        File fileFoto = new File(BASE_PATH + periode + fileName);
        if (fileFoto.delete())
            System.out.println(fileFoto.getName() + " file has been deleted!");
    }

    public static String imageToString(File file) {
        String result = null;
        try {
//            byte[] fileContent = FileUtils.readFileToByteArray(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BufferedImage resizedImage = resize(file);
            ImageIO.write(resizedImage, "jpg", byteArrayOutputStream);

            byte[] fileContent = byteArrayOutputStream.toByteArray();
            result = Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static BufferedImage resize(File imageFile) throws IOException {
        BufferedImage sourceImage = ImageIO.read(imageFile);
        return Scalr.resize(sourceImage, Scalr.Mode.FIT_EXACT, sourceImage.getWidth() / 2, sourceImage.getHeight() / 2);
    }

    public static void stringToImage(Integer periode, String fileName, String imgString) {
        byte[] decodedBytes = Base64.getDecoder().decode(imgString);
        File dir = new File(BASE_PATH + periode);
        if (!dir.exists()) dir.mkdir();
        Path filePath = dir.toPath().resolve(fileName);
        try {
            FileUtils.writeByteArrayToFile(new File(String.valueOf(filePath)), decodedBytes);
            System.out.println(fileName + " has been uploaded!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
