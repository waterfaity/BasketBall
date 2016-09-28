package com.huizetime.basketball.bean;

/**
 * Created by water_fairy on 2016/9/28.
 */

public class WatchBean {
    public static final int TYPE_LOGGING = 0;
    public static final int TYPE_CHECK = 1;

    private int type;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
