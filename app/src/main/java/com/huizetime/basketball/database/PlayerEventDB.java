package com.huizetime.basketball.database;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

/**
 * Created by water_fairy on 2016/9/23.
 */
public class PlayerEventDB extends SugarRecord {
    //boolean
    public static final int YES = 1;
    public static final int NO = 0;
    //事件类型(type)
    //--进球类
    public static final int PENALTY_SHORT = 11;//罚球 (得1分)
    public static final int TWO_SCORE = 12;//2分球
    public static final int THREE_SCORE = 13;//3分球
    //--无关乎进球
    public static final int QIAN_LAN_BAN = 21;//前场篮板
    public static final int HOU_LAN_BAN = 22;//后场篮板
    public static final int ZHU_GONG = 23;//助攻
    public static final int SHI_WU = 24;//失误
    public static final int QIANG_DUAN = 25;//抢断
    public static final int FAN_GUI = 26;//犯规
    public static final int GAI_MAO = 27;//盖帽


    //    private int id;//数据库id
    @Column(unique = false, name = "segment")
    private int segment;//小节 (1,2... -1,-2..)
    @Column(unique = false, name = "time")
    private int time;//小节时间(倒计时,剩余时间 单位 : 秒)
    @Column(unique = false, name = "matchId")
    private int matchId;//赛事id
    @Column(unique = false, name = "teamId")
    private int teamId;//队伍id
    @Column(unique = false, name = "playerId")
    private int playerId;//队员id
    @Column(unique = false, name = "type")
    private int type;//事件类型
    @Column(unique = false, name = "success")
    private int success;//这对投球命中与否(0未中,1中球)
    @Column(unique = false, name = "sameId")
    private int sameId;//上场,下场 对应的id(1,2...,每小节从1开始记录)
    @Column(unique = false, name = "first")
    private int first;//跳球  赛事比赛开始  只有一次,(0不是跳球人员,1是跳球人员)
    @Column(unique = false, name = "score")
    private int score;//只做比分调整时才有数据(1,2,..  -1,-2)有正负之分

    public int getSegment() {
        return segment;
    }

    public void setSegment(int segment) {
        this.segment = segment;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getSameId() {
        return sameId;
    }

    public void setSameId(int sameId) {
        this.sameId = sameId;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
