package com.huizetime.basketball.database;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

/**
 * Created by water_fairy on 2016/9/29.
 */

public class TeamDB extends SugarRecord {
    private final int LEFT = 0;
    private final int RIGHT = 1;

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


}
