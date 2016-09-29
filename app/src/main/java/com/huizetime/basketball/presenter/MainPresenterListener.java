package com.huizetime.basketball.presenter;

import com.huizetime.basketball.bean.tv.TVScoreBean;
import com.huizetime.basketball.bean.tv.TVSignBean;

/**
 * Created by water_fairy on 2016/9/26.
 */
public interface MainPresenterListener extends BasePresenter{
    void initData();


    void setWatchInfo();

    void sendImg();

    void sendSign();

    void sendScore();

    void change(int which, int type, int data);


}
