package com.kentoes.caterapi.controllers.entahlah;

import com.kentoes.caterapi.config.imageUtil.ImageCompression;
import com.kentoes.caterapi.config.imageUtil.ImageMultipart;
import com.kentoes.caterapi.config.imageUtil.ImageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
@RequestMapping("/entahlah/upload")
public class ImageUploadController {
    private final ImageCompression imageCompression;

    @Autowired
    public ImageUploadController(ImageCompression imageCompression) {
        this.imageCompression = imageCompression;
    }

    @PostMapping("/file/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void uploadJpgImageFile(@PathVariable("name") String name,
                                   @RequestPart(value = "file") MultipartFile multipartFile) {
        imageCompression.compress(new ImageMultipart(multipartFile), name);
    }

    @PostMapping("/url/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void uploadJpgImageUrl(@PathVariable("name") String name,
                                  @RequestBody String urlAsString) {
        URL url;
        try {
            url = new URL(urlAsString);
            url.toURI();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        imageCompression.compress(new ImageUrl(url), name);
    }
}
