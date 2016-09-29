package com.huizetime.basketball.presenter;

import com.huizetime.basketball.bean.WatchBean;

import java.util.List;
import java.util.Objects;

/**
 * Created by water_fairy on 2016/9/28.
 */
public interface WatchListPresenterListener {
    void refresh();

    void loadMore();

    void getData(int page, int type);

    void onSuccess(Object object);

    void onError(int code);

    void onItemClick(int watchId, int type);

    void onUserMenuClick();

    void connect();

    void onConnectError();

    void onDisconnect();

    void onConnectSuccess();

    void onConnecting();

    void setBlueToothAddress();

    void shutDownConnect();

    void onSearchClick();

    void onSearchBackClick();

    void quit();

    void onSearch();

    void searchResult(List<WatchBean> list);
}
