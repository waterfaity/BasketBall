package com.huizetime.basketball.utils;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import org.w3c.dom.NameList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by water_fairy on 2016/9/23.
 */
public class Base64Utils {

    private static final String TAG = "base64";

    public static String encodeToString(String path) {
        String content = "";
        File file = new File(path);

        try {
            return encodeToString(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String encodeToString(byte[] bytes) {

        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static String encodeToString(InputStream inputStream) {
        int fileSize = 1024 * 1024 * 10;
        try {
            fileSize = inputStream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteBuffer buffer = ByteBuffer.allocate(fileSize);//10M大小

        byte[] bytes = new byte[1024 * 512];
        int len = 0;
        try {
            while ((len = inputStream.read(bytes)) != -1) {
                buffer.put(bytes, 0, len);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        byte[] array = buffer.array();
        buffer.clear();
        Log.i(TAG, "fileSize: "+fileSize);
        return encodeToString(array);
    }

    public static byte[] decodeToByte(String content) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        return Base64.decode(content.getBytes(), Base64.DEFAULT);
    }

    public static File decodeToFile(String content, String path) {

        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();

                byte[] bytes = decodeToByte(content);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(bytes);
                fos.flush();
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }


}
