package com.huizetime.basketball.presenter;

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

    void disconnect();

    void onConnectError();

    void onDisconnect();

    void onConnectSuccess();

    void onConnecting();

    void setBlueToothAddress();

    void shutDownConnect();
}
