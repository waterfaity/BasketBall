package com.huizetime.basketball.database;

import com.orm.SugarRecord;
import com.orm.dsl.Column;

/**
 * Created by water_fairy on 2016/9/23.
 */
public class PlayerInfoDB extends SugarRecord {

    public static final int POINT_GUARD = 1;    //组织后卫  PointGuard
    public static final int SHOOTING_GUARD = 2; //得分后卫  ShootingGuard
    public static final int SMALL_FORWARD = 3;  //小前锋   SmallForward
    public static final int POWER_FORWARD = 4;  //大前锋   PowerForward
    public static final int CENTER = 5;         //中锋    Center


    @Column(unique = false, name = "playerId")
    private int playerId;
    @Column(unique = false, name = "name")
    private String name;
    @Column(unique = false, name = "num")
    private int num;
    @Column(unique = false, name = "position")
    private int position;
    @Column(unique = false, name = "gender")
    private boolean gender;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
}
