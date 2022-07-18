package com.kentoes.caterapi.config.imageUtil;

import org.imgscalr.Scalr;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class JpgImage {
    public static final String EXTENSION = "jpg";
    private final File source;
    private final ImageFormat format;

    public JpgImage(File source, ImageFormat format) {
        this.source = source;
        this.format = format;
    }

    public void compressTo(File target) throws IOException {
        FileImageOutputStream targetOutputStream = new FileImageOutputStream(target);
        BufferedImage resizedImage = resize(source);

        ImageWriter writer = getWriter();
        ImageWriteParam writerSettings = getWriterSettings(writer);

        try {
            writer.setOutput(targetOutputStream);
            writer.write(null, new IIOImage(resizedImage, null, null), writerSettings);
        } finally {
            writer.dispose();
            targetOutputStream.close();
            resizedImage.flush();
        }
    }

    public String compressToString() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage resizedImage = resize(source);
        ImageIO.write(resizedImage, "jpg", byteArrayOutputStream);

        byte[] fileContent = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(fileContent);
    }

    private BufferedImage resize(File imageFile) throws IOException {
        BufferedImage sourceImage = ImageIO.read(imageFile);
        return Scalr.resize(sourceImage, Scalr.Mode.FIT_EXACT, format.width(), format.height());
    }

    private ImageWriter getWriter() {
        Iterator<ImageWriter> imageWriterIterator = ImageIO.getImageWritersByFormatName(EXTENSION);

        if (!imageWriterIterator.hasNext())
            throw new NoSuchElementException(
                    String.format("Couldn't find an image writer for %s format", EXTENSION));

        return imageWriterIterator.next();
    }

    private ImageWriteParam getWriterSettings(ImageWriter imageWriter) {
        ImageWriteParam imageWriteParams = imageWriter.getDefaultWriteParam();

        imageWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParams.setCompressionQuality(format.compression());

        return imageWriteParams;
    }
}
