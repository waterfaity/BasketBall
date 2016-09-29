package com.huizetime.basketball.model;

/**
 * Created by water_fairy on 2016/9/28.
 */
public interface WatchListModel {
    void getData(int page);

    void initData();

    void connect();

    void shutDownConnect();

    void search(String searchContent);
}
