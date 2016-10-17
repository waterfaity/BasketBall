package com.huizetime.basketball.presenter;

import com.huizetime.basketball.bean.tv.TVScoreBean;
import com.huizetime.basketball.bean.tv.TVSignBean;
import com.huizetime.basketball.database.TeamDB;
import com.huizetime.basketball.database.WatchDB;

/**
 * Created by water_fairy on 2016/9/26.
 */
public interface MainPresenterListener extends BasePresenter {
    void initData();


    void sendWatchInfo();

    void sendImg();

    void sendSign();

    void sendScore();

    void change(int which, int type, int data);


    void destroy();

    /**
     * 初始化 展示数据
     *
     * @param watchDB
     * @param aTeam
     * @param bTeam
     */
    void displayData(WatchDB watchDB, TeamDB aTeam, TeamDB bTeam);

    /**
     * 设置Fragment
     */
    void setFragment(int segment);

    /**
     * 获取球权
     *
     * @param which     队伍 left,right
     * @param playerNum 球员num(非id)
     */
    void setGetBall(int which, int playerNum);

    /**
     * 比赛作废
     */
    void setCancellation();

    /**
     * 小节
     *
     * @param segment
     */
    void setSegment(int segment);

    /**
     * 申请暂停
     *
     * @param which left,right
     */
    void requestStop(int which);

    /**
     * 申请换人
     *
     * @param which
     */
    void requestChangePlayer(int which);

    /**
     * B队得分
     *
     * @param score
     */
    void setBScore(int score);

    /**
     * A队得分
     *
     * @param score
     */
    void setAScore(int score);

    /**
     * 暂停次数
     *
     * @param which
     * @param times
     */
    void setStopTimes(int which, int times);

    /**
     * 犯规次数
     *
     * @param which
     * @param times
     */
    void setFoulTimes(int which, int times);
}
