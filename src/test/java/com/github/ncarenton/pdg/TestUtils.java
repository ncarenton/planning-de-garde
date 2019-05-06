package com.github.ncarenton.pdg;

import com.github.ncarenton.pdg.utils.Utils;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TestUtils {

    public static InputStream getResourceAsStream(String name) throws FileNotFoundException {
        InputStream is = Utils.class.getResourceAsStream(name);
        if (is == null) {
            throw new FileNotFoundException(name + " file not found in the classpath");
        }
        return is;
    }

    public static String grabResource(String resource) throws IOException {
        return IOUtils.toString(getResourceAsStream(resource));
    }
}
