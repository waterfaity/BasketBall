package com.huizetime.basketball.manager;

import android.util.Log;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by water_fairy on 2016/9/23.
 *
 */
public class BTDataTrans {
    private static final String TAG = "BTDataTrans";
    int len = 6;
    int maxLen = 1024 * 1024 * 10;
    byte[] start = new byte[]{'s', 't', 'a', 'r', 't', '-'};
    byte[] end = new byte[]{'-', '-', '-', 'e', 'n', 'd'};
    //开始标记:   start-
    //结束标记:   --end;
    private ByteBuffer buffer;
    private int totalLen;


    public boolean trans(byte[] bytes, int hasLen) {
        if (hasLen >= 6) {

            byte[] startTemp = Arrays.copyOfRange(bytes, 0, len);
            if (Arrays.equals(start, startTemp)) {
                totalLen = 0;
                //开始接收
                buffer = ByteBuffer.allocate(maxLen);
                buffer.put(bytes, 0, hasLen);

                if (hasLen >= 12) {
                    byte[] endTemp = Arrays.copyOfRange(bytes, hasLen - len, hasLen);
                    if (Arrays.equals(end, endTemp)) {
                        totalLen = hasLen;
                        return true;
                    }
                }
                totalLen += hasLen;
            } else {
                buffer.put(bytes, 0, hasLen);
                totalLen += hasLen;
                byte[] endTemp = Arrays.copyOfRange(bytes, hasLen - len, hasLen);
                if (Arrays.equals(end, endTemp)) {
                    return true;

                } else {

                    //接收中
                }
            }
            return false;
        } else {
            Log.i(TAG, "数据接收错误");
            return false;
        }
    }


    public byte[] getResult() {
        //接收完毕
        byte[] array = buffer.array();
        buffer.clear();
        ByteBuffer wrap = ByteBuffer.wrap(array, len, array.length - len * 2);
        byte[] bytes = wrap.array();
        wrap.clear();
        return Arrays.copyOfRange(bytes, 0, totalLen);
    }


    public void clear() {
        totalLen = 0;
    }
}
