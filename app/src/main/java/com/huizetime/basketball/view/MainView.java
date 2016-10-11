package com.huizetime.basketball.view;

import com.huizetime.basketball.bean.EventBean;

import java.io.File;

/**
 * Created by water_fairy on 2016/9/26.
 */
public interface MainView {

    void back();

    /**
     * 设置队伍名称
     *
     * @param aName
     * @param bName
     */
    void setTeamName(String aName, String bName);

    /**
     * 设置队伍得分
     *
     * @param aScore
     * @param bScore
     */
    void setScore(int aScore, int bScore);

    /**
     * 设置小节
     *
     * @param segment
     */
    void setSegment(String segment);

    /**
     * 设置时间
     *
     * @param time
     */
    void setTime(String time);

    /**
     * 设置logo
     *
     * @param aLogo
     * @param bLogo
     */
    void setLogo(String aLogo, String bLogo);

    /**
     * 设置按钮状态
     *
     * @param resId
     * @param btName
     */
    void setButtonState(int resId, String btName);

    /**
     * 设置 暂停/换人
     *
     * @param which
     * @param state
     */
    void setATeamEvent(int which, int state);

    /**
     * 设置A队犯规次数
     *
     * @param times
     */
    void setATeamFoul(int times);

    /**
     * 设置A队暂停次数
     *
     * @param times
     */
    void setATeamStop(int times);

    /**
     * 设置B队犯规次数
     *
     * @param times
     */

    void setBTeamFoul(int times);

    /**
     * 设置B队暂停次数
     *
     * @param times
     */
    void setBTeamStop(int times);

    /**
     * 设置事件1
     *
     * @param event1
     */
    void setEvent1(EventBean event1);

    void setEvent2(EventBean event2);

    /**
     * 设置事件2
     *
     * @param event2
     */
    void setEvent(EventBean event2);

}
