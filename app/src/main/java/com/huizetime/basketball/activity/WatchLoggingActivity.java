package com.huizetime.basketball.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huizetime.basketball.R;
import com.huizetime.basketball.application.MyApp;
import com.huizetime.basketball.bean.EventBean;
import com.huizetime.basketball.fragment.prepare.HomePrepareFragment;
import com.huizetime.basketball.presenter.MainPresenter;
import com.huizetime.basketball.presenter.MainPresenterListener;
import com.huizetime.basketball.utils.ConstantUtils;
import com.huizetime.basketball.utils.ToastUtils;
import com.huizetime.basketball.utils.ToolbarUtils;
import com.huizetime.basketball.view.MainView;
import com.huizetime.basketball.widget.BigNumView;
import com.huizetime.basketball.widget.CourtView;
import com.huizetime.basketball.widget.ScoreView;

public class WatchLoggingActivity extends AppCompatActivity implements MainView, CourtView.OnPointClickListener {

    private MainPresenterListener mPresenter;
    private static final String TAG = "main";
    private int[] numRes = new int[]{};

    private BigNumView mBNScore1, mBNScore2;//得分
    private int mWidth;//屏幕宽
//view

    private TextView mAName;//A队名字
    private TextView mBName;//B队名字
    private TextView mSegment;//小节
    private TextView mTime;//时间
    private ScoreView mAScore;//a得分
    private ScoreView mBScore;//b得分
    private ImageView mALogo;//aLogo
    private ImageView mBLogo;//blogo
    private TextView mATingText;//A队 暂停
    private TextView mAHuanText;//A队 换人
    private TextView mAFanguiTimes;//A队 犯规次数
    private TextView mAZantingTimes;//A队 暂定次数
    private TextView mBTingText;//B队 暂停
    private TextView mBHuanText;//B队 换人
    private TextView mBFanguiTimes;//B队 犯规次数
    private TextView mBZantingTimes;//B队 暂停次数

    private ImageView mLogoEvent1;//事件1logo
    private TextView mAScoreEvent1;//事件1第一个分值
    private TextView mBScoreEvent1; //事件1第二个分值
    private TextView mSegmentEvent1; //事件1小节
    private TextView mTimeEvent1;   //事件1时间
    private TextView mInfoEvent1;   //事件1详细信息

    private ImageView mLogoEvent2;  //事件2logo
    private TextView mAScoreEvent2;//事件2第一个分值
    private TextView mBScoreEvent2; //事件2第二个分值
    private TextView mSegmentEvent2; //事件2小节
    private TextView mTimeEvent2;//事件2时间
    private TextView mInfoEvent2;//事件2详细信息

    private CourtView mCourtView;//球场
    private RelativeLayout mCourtContent;//球场中心部分显示 暂停/休息/...
    private TextView mButton;//按钮 暂停,开始...
    //事件1/2
    private EventBean mEventBean1, mEventBean2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mWidth = displayMetrics.widthPixels;
        Log.i(TAG, "onCreate:heightPixels " + displayMetrics.heightPixels);
        Log.i(TAG, "onCreate:widthPixels " + displayMetrics.widthPixels);
        initData();
        findView();
        initView();
        setData();
        ToolbarUtils.init(this, R.string.watch_log, mPresenter);
    }


    private void assignViews() {
        mAName = (TextView) findViewById(R.id.a_name);
        mBName = (TextView) findViewById(R.id.b_name);
        mSegment = (TextView) findViewById(R.id.segment);
        mTime = (TextView) findViewById(R.id.time);
        mAScore = (ScoreView) findViewById(R.id.a_score);
        mBScore = (ScoreView) findViewById(R.id.b_score);
        mALogo = (ImageView) findViewById(R.id.a_logo);
        mATingText = (TextView) findViewById(R.id.a_ting_text);
        mAHuanText = (TextView) findViewById(R.id.a_huan_text);
        mAFanguiTimes = (TextView) findViewById(R.id.a_fangui_times);
        mAZantingTimes = (TextView) findViewById(R.id.a_zanting_times);
        mBLogo = (ImageView) findViewById(R.id.b_logo);
        mBTingText = (TextView) findViewById(R.id.b_ting_text);
        mBHuanText = (TextView) findViewById(R.id.b_huan_text);
        mBFanguiTimes = (TextView) findViewById(R.id.b_fangui_times);
        mBZantingTimes = (TextView) findViewById(R.id.b_zanting_times);
        mLogoEvent1 = (ImageView) findViewById(R.id.logo_event1);
        mAScoreEvent1 = (TextView) findViewById(R.id.a_score_event1);
        mBScoreEvent1 = (TextView) findViewById(R.id.b_score_event1);
        mSegmentEvent1 = (TextView) findViewById(R.id.segment_event1);
        mTimeEvent1 = (TextView) findViewById(R.id.time_event1);
        mInfoEvent1 = (TextView) findViewById(R.id.info_event1);
        mLogoEvent2 = (ImageView) findViewById(R.id.logo_event2);
        mAScoreEvent2 = (TextView) findViewById(R.id.a_score_event2);
        mBScoreEvent2 = (TextView) findViewById(R.id.b_score_event2);
        mSegmentEvent2 = (TextView) findViewById(R.id.segment_event2);
        mTimeEvent2 = (TextView) findViewById(R.id.time_event2);
        mInfoEvent2 = (TextView) findViewById(R.id.info_event2);
        mCourtView = (CourtView) findViewById(R.id.court_view);
        mCourtContent = (RelativeLayout) findViewById(R.id.court_content);
        mButton = (TextView) findViewById(R.id.button);
    }

    private void initData() {

        mPresenter = new MainPresenter(this);
        mPresenter.initData();
    }

    private void initView() {
//        //设置球场  比例: 15/28
        mCourtView.getLayoutParams().height = (int) (mWidth * 15 / (float) 28);
        mCourtView.setOnPointListener(this);
        mCourtView.setWidth(28f);

        HomePrepareFragment firstFragment = new HomePrepareFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, firstFragment).attach(firstFragment).commit();
    }

    private void findView() {
        assignViews();
    }

    private void setData() {
        int tvPage = MyApp.getApp().getTVCurrentPage();

        switch (tvPage) {
            case MyApp.PAGE_CONNECT:
                //未录入 未签到

                break;
            case MyApp.PAGE_SIGN:
                //签到

                break;
            case MyApp.PAGE_EVENT:
                //录入

                break;
        }
    }


    public void onClick(View view) {

    }


    @Override
    public void onPointClick(String left, String right, int score) {
        String content = "left:" + left + " right" + right + " score:" + score;
        ToastUtils.show(content);
        Log.i(TAG, "onPointClick: " + content);
        mAScore.setScore(score * 111);
        mBScore.setScore(score * 111);


    }

    @Override
    public void back() {
        finish();
    }

    /**
     * 设置队伍名称
     *
     * @param aName
     * @param bName
     */
    @Override
    public void setTeamName(String aName, String bName) {
        mAName.setText(aName);
        mBName.setText(bName);
    }

    /**
     * 设置队伍得分
     *
     * @param aScore
     * @param bScore
     */
    @Override
    public void setScore(int aScore, int bScore) {
        mAScore.setScore(aScore);
        mBScore.setScore(bScore);
    }

    /**
     * 设置小节
     *
     * @param segment
     */
    @Override
    public void setSegment(String segment) {
        mSegment.setText(segment);
    }

    /**
     * 设置时间
     *
     * @param time
     */
    @Override
    public void setTime(String time) {
        mTime.setText(time);
    }

    /**
     * 设置logo
     *
     * @param aLogo
     * @param bLogo
     */
    @Override
    public void setLogo(String aLogo, String bLogo) {
        mALogo.setImageBitmap(BitmapFactory.decodeFile(aLogo));
        mBLogo.setImageBitmap(BitmapFactory.decodeFile(bLogo));
    }

    /**
     * 设置按钮状态
     *
     * @param resId
     * @param btName
     */
    @Override
    public void setButtonState(int resId, String btName) {
        mButton.setBackgroundResource(resId);
        mButton.setText(btName);
    }

    /**
     * 设置 暂停/换人
     *
     * @param which
     * @param state
     */
    @Override
    public void setATeamEvent(int which, int state) {
        mATingText.setVisibility(View.GONE);
        mBTingText.setVisibility(View.GONE);
        mAHuanText.setVisibility(View.GONE);
        mBHuanText.setVisibility(View.GONE);
        if (which == ConstantUtils.LEFT) {
            if (state == ConstantUtils.TING) {
                mATingText.setVisibility(View.VISIBLE);
            } else {
                mAHuanText.setVisibility(View.VISIBLE);
            }
        } else {
            if (state == ConstantUtils.TING) {
                mBTingText.setVisibility(View.VISIBLE);
            } else {
                mBHuanText.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 设置A队犯规次数
     *
     * @param times
     */
    @Override
    public void setATeamFoul(int times) {
        mAFanguiTimes.setText(times + "");
    }

    /**
     * 设置A队暂停次数
     *
     * @param times
     */
    @Override
    public void setATeamStop(int times) {
        mAZantingTimes.setText(times + "");
    }

    /**
     * 设置B队犯规次数
     *
     * @param times
     */
    @Override
    public void setBTeamFoul(int times) {
        mBFanguiTimes.setText(times + "");
    }

    /**
     * 设置B队暂停次数
     *
     * @param times
     */
    @Override
    public void setBTeamStop(int times) {
        mBZantingTimes.setText(times + "");
    }


    /**
     * 设置事件1
     *
     * @param event1
     */
    @Override
    public void setEvent1(EventBean event1) {
        mLogoEvent1.setImageBitmap(BitmapFactory.decodeFile(event1.getLogo()));
        mAScoreEvent1.setText(event1.getFirstScore() + "");
        mBScoreEvent1.setText(event1.getSecondScore() + "");
        mSegmentEvent1.setText(event1.getSegment() + "");
        mTimeEvent1.setText(event1.getTime());
        mInfoEvent1.setText(event1.getInfo());
    }

    /**
     * 设置事件2
     *
     * @param event2
     */
    @Override
    public void setEvent2(EventBean event2) {
        mLogoEvent2.setImageBitmap(BitmapFactory.decodeFile(event2.getLogo()));
        mAScoreEvent2.setText(event2.getFirstScore() + "");
        mBScoreEvent2.setText(event2.getSecondScore() + "");
        mSegmentEvent2.setText(event2.getSegment() + "");
        mTimeEvent2.setText(event2.getTime());
        mInfoEvent2.setText(event2.getInfo());
    }

    /**
     * 设置事件
     *
     * @param event
     */
    @Override
    public void setEvent(EventBean event) {
        if (mEventBean1 == null) {
            //事件1为空,设置参数
            mEventBean1 = event;
            setEvent1(mEventBean1);
            return;
        }
        if (mEventBean2 == null) {
            //事件2为空,设置参数
            mEventBean2 = event;
            setEvent2(mEventBean2);
            return;
        }
        //事件2不为空,将事件2的值给事件1,事件2赋新值,设置参数
        mEventBean1 = mEventBean2;
        mEventBean2 = event;
        setEvent1(mEventBean1);
        setEvent2(mEventBean2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mPresenter.back();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    public MainPresenterListener getPresenter() {
        return mPresenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, Menu.FIRST, 1, R.string.sign)
                .setIcon(R.drawable.ic_account_box_black_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, Menu.FIRST + 1, 2, R.string.watch_info)
                .setIcon(R.drawable.ic_backup_black_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == Menu.FIRST) {
            toSign();
        } else {
            toWatchInfo();
        }
        return false;
    }

    private void toWatchInfo() {
        Intent intent = new Intent(this, WatchInfoActivity.class);
        startActivity(intent);
    }

    private void toSign() {
        Intent intent = new Intent(this, SignActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

}
