package com.huizetime.basketball.database;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

/**
 * Created by water_fairy on 2016/9/29.
 */

public class TeamDB extends SugarRecord {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    @Column(unique = false, name = "watchId")
    private int watchId;//关联赛事
    @Column(unique = false, name = "teamId")
    private int teamId;//队伍id
    @Column(unique = false, name = "teamName")
    private String teamName;//名称
    @Column(unique = false, name = "teamShortName")
    private String teamShortName;//简称
    @Column(unique = false, name = "logo")
    private String logo;//logo
    @Column(unique = false, name = "score")
    private int score;//得分
    @Column(unique = false, name = "stopTimes")
    private int stopTimes;//暂停次数
    @Column(unique = false, name = "foulTimes")
    private int foulTimes;//犯规次数
    @Column(unique = false, name = "position")
    private int position;//队伍位置(左,右)

    public int getWatchId() {
        return watchId;
    }

    public void setWatchId(int watchId) {
        this.watchId = watchId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamShortName() {
        return teamShortName;
    }

    public void setTeamShortName(String teamShortName) {
        this.teamShortName = teamShortName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStopTimes() {
        return stopTimes;
    }

    public void setStopTimes(int stopTimes) {
        this.stopTimes = stopTimes;
    }

    public int getFoulTimes() {
        return foulTimes;
    }

    public void setFoulTimes(int foulTimes) {
        this.foulTimes = foulTimes;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
