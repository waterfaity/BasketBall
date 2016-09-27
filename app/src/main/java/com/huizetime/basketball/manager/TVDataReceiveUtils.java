package com.huizetime.basketball.manager;

/**
 * Created by water_fairy on 2016/9/27.
 */
public class TVDataReceiveUtils {

    public static String receive(BTDataTrans btDataTrans, byte[] bytes, int len) {

        boolean trans = btDataTrans.trans(bytes, len);
        String json = null;
        if (trans) {
            byte[] result = btDataTrans.getResult();
            btDataTrans.clear();
            json = new String(result);
            json = json.replace("start-", "");
            json = json.replace("---end", "");

        }
        return json;
    }
}
