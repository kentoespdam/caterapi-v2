package com.kentoes.caterapi.config;

import org.junit.jupiter.api.Test;

import java.io.File;

class FileUploadUtilTest {
    private static String BASE_PATH = System.getProperty("user.dir") + "/foto_cater/";

    @Test
    void fileToString() {
        File file = new File(BASE_PATH + "202207/ADIPRIYO_202207_0100006_qr-kk-new.jpeg");
        String result = FileUploadUtil.imageToString(file);
        System.out.println(result);
    }
}