package com.kentoes.caterapi.config.imageUtil;

import java.io.File;
import java.io.IOException;

public interface ImageSource {
    File asFile() throws IOException;

    String asString() throws IOException;
}
