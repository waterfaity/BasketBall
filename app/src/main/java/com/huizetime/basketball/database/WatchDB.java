package com.huizetime.basketball.database;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

/**
 * Created by water_fairy on 2016/9/29.
 */

public class WatchDB extends SugarRecord {
    public static final int STATE_NO_LOG = 0;
    public static final int STATE_PAUSE = 1;
    public static final int STATE_LOGGING = 2;
    public static final int STATE_END = 3;
    //比赛结果
    public static final int RESULT_A = 0;
    public static final int RESULT_B = 1;

    public static final int NORMAL = 0;//正常
    public static final int ABANDON = 1;//弃权

    @Column(unique = false, name = "watchId")
    private int watchId;//赛事id
    @Column(unique = false, name = "watchName")
    private String watchName;//赛事名字
    @Column(unique = false, name = "aTeamId")
    private int aTeamId;//A队名字
    @Column(unique = false, name = "bTeamId")
    private int bTeamId;//B队名字
    @Column(unique = false, name = "time")
    private long time;//比赛时间
    @Column(unique = false, name = "segmentTime")
    private int segmentTime;//小节倒计时
    @Column(unique = false, name = "segment")
    private int segment;//小节
    @Column(unique = false, name = "state")
    private int state;//赛事状态(未开始,暂停,录入中,结束)
    @Column(unique = false, name = "resultType")
    private int resultType;//结果类型
    @Column(unique = false, name = "result")
    private int result;//赛事结果

    public int getWatchId() {
        return watchId;
    }

    public void setWatchId(int watchId) {
        this.watchId = watchId;
    }

    public String getWatchName() {
        return watchName;
    }

    public void setWatchName(String watchName) {
        this.watchName = watchName;
    }

    public int getaTeamId() {
        return aTeamId;
    }

    public void setaTeamId(int aTeamId) {
        this.aTeamId = aTeamId;
    }

    public int getbTeamId() {
        return bTeamId;
    }

    public void setbTeamId(int bTeamId) {
        this.bTeamId = bTeamId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getSegmentTime() {
        return segmentTime;
    }

    public void setSegmentTime(int segmentTime) {
        this.segmentTime = segmentTime;
    }

    public int getSegment() {
        return segment;
    }

    public void setSegment(int segment) {
        this.segment = segment;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getResultType() {
        return resultType;
    }

    public void setResultType(int resultType) {
        this.resultType = resultType;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
