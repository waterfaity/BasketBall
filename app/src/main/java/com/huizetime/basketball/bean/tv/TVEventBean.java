package com.huizetime.basketball.bean.tv;

/**
 * Created by water_fairy on 2016/9/22.
 */
public class TVEventBean {
    public final static int TEAM_A = 1;
    public final static int TEAM_B = 2;

    public final static int SEGMENT = 1;//小节(1,2...  -1,-2...,外加时间)
    public final static int TIME = 2;//时间(int)(秒计算,)
    public final static int STOP = 3;//暂停(时间暂停,非队伍)
    public final static int START = 4;//继续(int传递时间)
    public final static int SCORE = 5;//得分(int)
    public final static int STOP_TIMES = 6;//暂停次数(int)
    public final static int FOUL_TIMES = 7;//犯规次数(int)
    public final static int REQUEST_STOP = 8;//叫停(先暂停,显示 停 图标)
    public final static int REQUEST_EXCHANGE = 9;//换人(先暂停,显示 换 图标)

    /**
     * 触发队伍
     * 1,左队伍;2,右队伍
     */
    private int team;
    /**
     * 事件类型
     */
    private int type;

    /**
     * 附带数据
     */
    private int data;
    /**
     * 额外数据(小节时,外加时间)
     */
    private int extraData;


    public int getExtraData() {
        return extraData;
    }

    public void setExtraData(int extraData) {
        this.extraData = extraData;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int item) {
        this.team = item;
    }
}
