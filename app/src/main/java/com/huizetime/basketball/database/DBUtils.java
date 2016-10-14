package com.huizetime.basketball.database;

import java.util.Date;

/**
 * Created by water_fairy on 2016/10/14.
 */

public class DBUtils {
    //测试
    public static void insertWatchDB() {
        WatchDB watchDB = new WatchDB();
        watchDB.setWatchId(1);
        watchDB.setaTeamId(110);
        watchDB.setbTeamId(111);
        watchDB.setState(watchDB.STATE_NO_LOG);
        watchDB.setSegment(1);
        watchDB.setTime(new Date().getTime());
        watchDB.setWatchName("亚洲春季赛-半决赛");
        watchDB.setSegmentTime(12 * 60);//12秒
        watchDB.save();
        insertTeamDB();
    }

    public static void insertTeamDB() {
        TeamDB aTeamDB = new TeamDB();
        aTeamDB.setFoulTimes(4);
        aTeamDB.setStopTimes(3);
        aTeamDB.setPosition(TeamDB.LEFT);
        aTeamDB.setScore(99);
        aTeamDB.setTeamId(110);
        aTeamDB.setTeamName("中国梦想之队");
        aTeamDB.setTeamShortName("梦之队");
        aTeamDB.setWatchId(1);
        aTeamDB.save();

        TeamDB bTeamDB = new TeamDB();
        bTeamDB.setFoulTimes(1);
        bTeamDB.setStopTimes(2);
        bTeamDB.setPosition(TeamDB.LEFT);
        bTeamDB.setScore(98);
        bTeamDB.setTeamId(111);
        bTeamDB.setTeamName("中国未来星之队");
        bTeamDB.setTeamShortName("星之队");
        bTeamDB.setWatchId(1);
        bTeamDB.save();

    }
}
