package com.kentoes.caterapi.config.imageUtil;

import org.apache.commons.io.FileUtils;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.util.Base64;

public final class ImageUrl implements ImageSource {
    private final URL url;

    public ImageUrl(URL url) {
        this.url = url;
    }

    @Override
    public File asFile() throws IOException {
        File imageFile = Files.createTempFile("image_url", ".tmp").toFile();

        FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());

        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        return imageFile;
    }

    @Override
    public String asString() throws IOException {
        File file = asFile();
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
