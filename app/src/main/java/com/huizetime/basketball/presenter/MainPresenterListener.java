package com.huizetime.basketball.presenter;

/**
 * Created by water_fairy on 2016/9/26.
 */
public interface MainPresenterListener extends BasePresenter{
    void initData();

    void connect();

    void setWatchInfo();

    void sendImg(int type, String path);

    void sendSign();

    void sendScore();

    void change();

    void close();

}
