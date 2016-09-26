package com.huizetime.basketball.database;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

/**
 * Created by water_fairy on 2016/9/23.
 */
public class EventDB extends SugarRecord {
    //针对boolean
    public static final int YES = 1;
    public static final int NO = 0;
    //全局类型(wholeType)
    public static final int WATCH_START = 1;//比赛开始 有跳球
    public static final int SEGMENT_START = 2;//小节开始
    public static final int PAUSE = 3;//暂停(A队暂停,B队暂停,teamId=0,裁判暂停或其他不由两队发起的暂停)
    public static final int START = 4;//开始
    public static final int CHANGE = 5;//换人
    public static final int SEGMENT_END = 6;//小节结束
    public static final int WATCH_END = 7;//小节结束
    public static final int WAIVER = 8;//弃权


    //事件类型(eventType)
    public static final int TIAO_QIU = 1;//跳球
    public static final int TOU_LAN = 2;//投篮
    public static final int QIANG_DUAN = 3;//抢断
    public static final int FAN_GUI = 4;//犯规
    public static final int WEI_LI = 5;//违例
    public static final int PENALTY_SHORT = 6;//罚球(teamId,球员id, 是要投球的队伍)

    //事件结果类型(eventResult)
    //--跳球成功
    public static final int TIAO_SHE = 21;//跳射
    public static final int GUAN_LAN = 22;//灌篮
    public static final int SHSANG_LAN = 23;//上篮
    public static final int THREE_FEN_SUCCESS = 24;//3分球

    //--跳球失败
    public static final int QIAN_LAN_BAN = 25;//前场篮板
    public static final int HOU_LAN_BAN = 26;//后场篮板
    public static final int GAI_MAO = 27;//盖帽
    public static final int BU_LAN = 28;//补篮(投中 有分值)
    public static final int JIE_WAI = 29;//界外

    //--抢断
    public static final int YUN_QIU = 31;//运球抢断
    public static final int CHUAN_QIU = 32;//传球抢断

    //--犯规
    public static final int TWO_FEN_FOUL = 41;//2分犯规
    public static final int THREE_FEN_FOUL = 42;//3分犯规
    public static final int JI_SHU_FOUL = 43;//技术犯规
    public static final int JIN_GONG_FOUL = 44;//进攻犯规
    public static final int FANG_SHOU_FOUL = 45;//防守犯规
    public static final int OTHER_FOUL = 46;//其他犯规

    //--违例
    public static final int TWO_YUN_QIU = 51;//两次传球
    public static final int ZOU_QIU = 52;//走步
    public static final int SERVE = 53;//发球
    public static final int TWENTY_FOUR_SECOND = 54;//24秒
    public static final int GAN_RAO_QIU = 55;//干扰球
    public static final int OTHER_WEI_LI = 56;//其它违例

    //--罚球类型(foulType)
    public static final int PENALTY_SHORT_ONE = 61;//罚一球
    public static final int PENALTY_SHORT_TWO = 62;//罚两球
    public static final int PENALTY_SHORT_THREE = 63;//罚三球
    public static final int ONE_AND_ONE = 64;//1&1罚球

    //--罚球结果(foulResult)
    public static final int PENALTY_SHORT_FAILURE = 600;//罚球失败
    public static final int PENALTY_SHORT_SUCCESS = 601;//罚球成功
    public static final int PENALTY_SHORT_QIAN_LAN_BAN = 602;//罚球前篮板
    public static final int PENALTY_SHORT_HOU_LAN_BAN = 603;//罚球后篮板
    public static final int PENALTY_SHORT_BU_LAN = 604;//罚球补篮

    @Column(unique = false, name = "matchId")
    private int matchId;//比赛id
    @Column(unique = false, name = "currentTime")
    private long currentTime;//当前事件时间
    @Column(unique = false, name = "segment")
    private int segment;//小节(1,2,3...  -1,-2...)
    @Column(unique = false, name = "segmentTime")
    private int segmentTime;//小节时间(倒计时)
    @Column(unique = false, name = "wholeType")
    private int wholeType;//全局类型
    @Column(unique = false, name = "eventType")
    private int eventType;//事件类型
    @Column(unique = false, name = "teamId")
    private int teamId;//触达事件的队伍id
    @Column(unique = false, name = "playerId")
    private int playerId;//触发事件的队员id
    @Column(unique = false, name = "hasAssist")
    private int hasAssist;//是否有助攻0没有,1有
    @Column(unique = false, name = "assistId")
    private int assistId;//助攻人员id
    @Column(unique = false, name = "adverseId")
    private int adverseId;//对方人员id(预留)
    @Column(unique = false, name = "eventResult")
    private int eventResult;//事件结果
    @Column(unique = false, name = "foulType")
    private int foulType;//罚球类型(0,不罚球.事件结果出现犯规时 使用该字段)
    @Column(unique = false, name = "foulTimes")
    private int foulTimes;//当前罚球次数(1,2...)
    @Column(unique = false, name = "foulResult")
    private int foulResult;//当前罚球结果
    @Column(unique = false, name = "score")
    private int score;//本事件得分
    @Column(unique = false, name = "distance")
    private int distance;//投篮距离
    @Column(unique = false, name = "changeScore")
    private int changeScore;//是否有比分调整 0没有,1有
    @Column(unique = false, name = "aScore")
    private int aScore;//此时A队得分
    @Column(unique = false, name = "bScore")
    private int bScore;//此时B队得分
    @Column(unique = false, name = "info")
    private String info;//说明字段


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public int getSegment() {
        return segment;
    }

    public void setSegment(int segment) {
        this.segment = segment;
    }

    public int getSegmentTime() {
        return segmentTime;
    }

    public void setSegmentTime(int segmentTime) {
        this.segmentTime = segmentTime;
    }

    public int getWholeType() {
        return wholeType;
    }

    public void setWholeType(int wholeType) {
        this.wholeType = wholeType;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
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

    public int getHasAssist() {
        return hasAssist;
    }

    public void setHasAssist(int hasAssist) {
        this.hasAssist = hasAssist;
    }

    public int getAssistId() {
        return assistId;
    }

    public void setAssistId(int assistId) {
        this.assistId = assistId;
    }

    public int getAdverseId() {
        return adverseId;
    }

    public void setAdverseId(int adverseId) {
        this.adverseId = adverseId;
    }

    public int getEventResult() {
        return eventResult;
    }

    public void setEventResult(int eventResult) {
        this.eventResult = eventResult;
    }

    public int getFoulType() {
        return foulType;
    }

    public void setFoulType(int foulType) {
        this.foulType = foulType;
    }

    public int getFoulTimes() {
        return foulTimes;
    }

    public void setFoulTimes(int foulTimes) {
        this.foulTimes = foulTimes;
    }

    public int getFoulResult() {
        return foulResult;
    }

    public void setFoulResult(int foulResult) {
        this.foulResult = foulResult;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getChangeScore() {
        return changeScore;
    }

    public void setChangeScore(int changeScore) {
        this.changeScore = changeScore;
    }

    public int getaScore() {
        return aScore;
    }

    public void setaScore(int aScore) {
        this.aScore = aScore;
    }

    public int getbScore() {
        return bScore;
    }

    public void setbScore(int bScore) {
        this.bScore = bScore;
    }
}
