package com.fengwenyi.api.example.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Erwin Feng
 * @since 2021-04-01
 */
public class Utils {

    public static byte[] getByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int rc;
        while((rc=is.read(buff, 0, 1024))>0) {
            baos.write(buff, 0, rc);
        }
        return baos.toByteArray();
    }

}
