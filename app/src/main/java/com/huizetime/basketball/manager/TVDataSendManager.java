package com.huizetime.basketball.manager;

import com.huizetime.basketball.bean.tv.TVData;
import com.huizetime.basketball.bean.tv.TVEventBean;
import com.huizetime.basketball.bean.tv.TVScoreBean;
import com.huizetime.basketball.bean.tv.TVSignBean;
import com.huizetime.basketball.utils.Base64Utils;
import com.huizetime.basketball.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by water_fairy on 2016/9/22.
 */
public class TVDataSendManager {

    private List<String> sendList;//存放发送数据列表
    private int resultCode;
    private BTManager btManager;
    private int watchId;
    private String watchName;
    private String aTeamName;
    private String bTeamName;


    public TVDataSendManager setBTeamName(String bTeamName) {
        this.bTeamName = bTeamName;
        return this;
    }


    /**
     * 发送图片
     *
     * @param type 图片类型 A队/B队/二维码
     * @param path 图片路径(网络加载请保存至本地)
     */
    public void sendImg(int type, String path) {
        TVData tvData = new TVData();
        tvData.setCode(type);
        String img = Base64Utils.encodeToString(path);
        tvData.setImg(img);
        writeData(tvData);
    }


    /**
     * 签到显示
     *
     * @param aEntity A队签到数据
     * @param bEntity B队签到数据
     */
    public void senSignPlayer(TVSignBean.Entity aEntity, TVSignBean.Entity bEntity) {
        TVData tvData = new TVData();
        tvData.setCode(TVData.TYPE_SIGN);
        TVSignBean tvSignBean = new TVSignBean();
        tvSignBean.setMatchName(watchName);
        if (aEntity == null) {
            aEntity = tvSignBean.new Entity();
        }
        if (bEntity == null) {
            bEntity = tvSignBean.new Entity();
        }
        aEntity.setTeamName(aTeamName);
        tvSignBean.setEntityA(aEntity);
        bEntity.setTeamName(bTeamName);
        tvSignBean.setEntityB(bEntity);
        tvData.setTvSignBean(tvSignBean);
        writeData(tvData);
    }


    /**
     * 初始化比分(如果是交换场地 , 重新设置队伍的名字)
     *
     * @param aEntity A队得分/犯规/暂停 数据
     * @param bEntity B队得分/犯规/暂停 数据
     * @param segment 小节
     * @param time    时间
     */
    public void sendInitScoreData(TVScoreBean.Entity aEntity, TVScoreBean.Entity bEntity, int segment, int time) {
        TVData tvData = new TVData();
        tvData.setCode(TVData.TYPE_INIT_SCORE);
        TVScoreBean tvScoreBean = new TVScoreBean();
        tvScoreBean.setSegment(segment);
        tvScoreBean.setTime(time);
        aEntity.setTeamName(aTeamName);
        bEntity.setTeamName(bTeamName);
        tvScoreBean.setTeamA(aEntity);
        tvScoreBean.setTeamB(bEntity);
        tvData.setTvScoreBean(tvScoreBean);
        writeData(tvData);
    }


    public void change(int which, int type, int data) {
        change(which, type, data, -1);
    }

    /**
     * @param which     队伍 (1,2)
     * @param type      事件类型
     * @param data      得分
     * @param extraData 附带数据
     */
    public void change(int which, int type, int data, int extraData) {

        TVData tvData = new TVData();
        tvData.setCode(TVData.TYPE_EVENT);
        TVEventBean tvScoreEventBean = new TVEventBean();
        tvScoreEventBean.setTeam(which);
        tvScoreEventBean.setType(type);
        tvScoreEventBean.setData(data);
        tvScoreEventBean.setExtraData(extraData);
        tvData.setTvScoreEventBean(tvScoreEventBean);
        writeData(tvData);
    }

    //关闭连接
    public void close() {
        TVData tvData = new TVData();
        tvData.setCode(TVData.TYPE_CLOSE);
        writeData(tvData);
        btManager.close();

    }

    //发送数据
    private void writeData(Object object) {
        String json = JsonUtils.getJson(object);
        json = "start-" + json + "---end";
        btManager.writeMsgFromUser(json.getBytes());
    }

    public int getWatchId() {
        return watchId;
    }

    public TVDataSendManager setWatchId(int watchId) {
        this.watchId = watchId;
        return this;
    }

    public String getBTeamName() {
        return bTeamName;
    }

    public String getATeamName() {
        return aTeamName;
    }

    public TVDataSendManager setATeamName(String aTeamName) {
        this.aTeamName = aTeamName;
        return this;
    }

    public String getWatchName() {
        return watchName;
    }

    public TVDataSendManager setWatchName(String watchName) {
        this.watchName = watchName;
        return this;
    }

    public TVDataSendManager(BTManager btManager) {
        this.btManager = btManager;
    }


    public void setResultCode(int result) {
        resultCode = result;
    }


}
