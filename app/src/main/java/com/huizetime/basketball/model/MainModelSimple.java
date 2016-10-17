package com.huizetime.basketball.model;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.huizetime.basketball.application.MyApp;
import com.huizetime.basketball.bean.tv.TVScoreBean;
import com.huizetime.basketball.bean.tv.TVSignBean;
import com.huizetime.basketball.database.EventDB;
import com.huizetime.basketball.database.TeamDB;
import com.huizetime.basketball.database.WatchDB;
import com.huizetime.basketball.manager.TVDataSendManager;
import com.huizetime.basketball.presenter.MainPresenter;
import com.huizetime.basketball.utils.ConstantUtils;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by water_fairy on 2016/9/26.
 */
public class MainModelSimple implements MainModel {

    private static final String TAG = "mainModel";
    private MainPresenter mPresenter;
    private Activity mActivity;
    private TVDataSendManager mTVDataSendManager;


    public MainModelSimple(MainPresenter presenter, Activity activity) {
        this.mPresenter = presenter;
        mActivity = activity;
    }

    @Override
    public void initData() {

        //初始化数据传输管理
        mTVDataSendManager = MyApp.getApp().getTVDataSendManager();
        //初始化广播
        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(ConstantUtils.ACTION_A_SCORE);
        intentFilter.addAction(ConstantUtils.ACTION_B_SCORE);
        intentFilter.addAction(ConstantUtils.ACTION_SEGMENT);
        intentFilter.addAction(ConstantUtils.ACTION_REQUEST_STOP);
        intentFilter.addAction(ConstantUtils.ACTION_REQUEST_CHANGE_PLAYER);
        intentFilter.addAction(ConstantUtils.ACTION_EVENT);
        //准备阶段
        intentFilter.addAction(ConstantUtils.ACTION_CHANGE_AREA);
        intentFilter.addAction(ConstantUtils.ACTION_CANCELLATION);
        intentFilter.addAction(ConstantUtils.ACTION_GET_BALL);
        intentFilter.addAction(ConstantUtils.ACTION_SET_FRAGMENT);
        //比赛进行中
        mActivity.registerReceiver(mDataReceiver, intentFilter);

    }

    //由比赛信息,初始化界面
    private void initData(WatchDB watchDB) {
//        int aTeamId = watchDB.getaTeamId();
//        int bTeamId = watchDB.getbTeamId();

        List<TeamDB> teamDBs = SugarRecord.find(TeamDB.class, "watchId = ?", watchDB.getWatchId() + "");
        if (teamDBs != null && teamDBs.size() == 2) {
            TeamDB teamDB1 = teamDBs.get(0);
            TeamDB teamDB2 = teamDBs.get(1);
            if (teamDB1.getPosition() == TeamDB.LEFT) {
                mPresenter.displayData(watchDB, teamDB1, teamDB2);
            } else {
                mPresenter.displayData(watchDB, teamDB2, teamDB1);
            }
        }

    }


    @Override
    public void connect() {

    }

    @Override
    public void sendWatchInfo(int watchId, String watchName, String aTeamName, String bTeamName) {
        mTVDataSendManager.setWatchId(watchId);
        mTVDataSendManager.setWatchName(watchName);
        mTVDataSendManager.setATeamName(aTeamName);
        mTVDataSendManager.setBTeamName(bTeamName);
        mTVDataSendManager.senSignPlayer(null, null);
    }

    @Override
    public void sendImg(int type, String path) {
        mTVDataSendManager.sendImg(type, path);
    }

    @Override
    public void sendSign(TVSignBean.Entity aEntity, TVSignBean.Entity bEntity) {
        mTVDataSendManager.senSignPlayer(aEntity, bEntity);

    }

    @Override
    public void sendScoreInfo(TVScoreBean.Entity aEntity, TVScoreBean.Entity bEntity, int segment, int time) {
        mTVDataSendManager.sendInitScoreData(aEntity, bEntity, segment, time);
    }

    @Override
    public void sendChange(int which, int type, int data) {
        mTVDataSendManager.change(which, type, data);
    }

    @Override
    public void close() {
        mTVDataSendManager.close();
    }


    @Override
    public void destroy() {
        mActivity.unregisterReceiver(mDataReceiver);
    }

    @Override
    public void logIn() {
//        //初始化比赛数据 从数据库取得数据
//        List<WatchDB> watchDBs = WatchDB.find(WatchDB.class, "watchId = ?", watchId + "");
//        if (watchDBs != null && watchDBs.size() == 1) {
//            WatchDB watchDB = watchDBs.get(0);
//            initData(watchDB);
//        }
    }

    private BroadcastReceiver mDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i(TAG, "onReceive: " + action);
            switch (action) {
                case ConstantUtils.ACTION_A_SCORE:
                    //a队得分
                    mPresenter.setAScore(intent.getIntExtra("data", 0));
                    break;
                case ConstantUtils.ACTION_B_SCORE:
                    mPresenter.setBScore(intent.getIntExtra("data", 0));
                    //B队得分
                    break;
                case ConstantUtils.ACTION_REQUEST_CHANGE_PLAYER:
                    //换人
                    mPresenter.requestChangePlayer(intent.getIntExtra("data", -1));
                    break;
                case ConstantUtils.ACTION_REQUEST_STOP:
                    //暂停
                    mPresenter.requestStop(intent.getIntExtra("data", -1));
                    break;
                case ConstantUtils.ACTION_EVENT:
                    //事件
                    EventDB eventDB = (EventDB) intent.getSerializableExtra("data");

                    break;
                case ConstantUtils.ACTION_SEGMENT:
                    //小节
                    mPresenter.setSegment(intent.getIntExtra("data", 0));
                    break;

                case ConstantUtils.ACTION_TIME:
                    //时间
                    break;
                case ConstantUtils.ACTION_CHANGE_AREA:
                    //交换场地

                    break;
                case ConstantUtils.ACTION_CANCELLATION:
                    //比赛作废
                    mPresenter.setCancellation();
                    break;
                case ConstantUtils.ACTION_GET_BALL:
                    //获取球权
                    mPresenter.setGetBall(intent.getIntExtra("data", 0),//left / right
                            intent.getIntExtra("extra", 0));//球员num  获取id
                    break;
                case ConstantUtils.ACTION_SET_FRAGMENT:
                    //切换页面(4大fragment)
                    mPresenter.setFragment(intent.getIntExtra("data", 0));
                    break;
                case ConstantUtils.ACTION_STOP_TIMES:
                    //暂停次数
                    mPresenter.setStopTimes(intent.getIntExtra("data", 0),
                            intent.getIntExtra("extra", 0));
                    break;
                case ConstantUtils.ACTION_FOUL_TIMES:
                    //犯规次数
                    mPresenter.setFoulTimes(intent.getIntExtra("data", 0),
                            intent.getIntExtra("extra", 0));
                    break;
            }
        }
    };


}
