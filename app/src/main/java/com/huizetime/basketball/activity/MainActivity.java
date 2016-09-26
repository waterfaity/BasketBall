package com.huizetime.basketball.activity;

import android.bluetooth.BluetoothDevice;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.huizetime.basketball.R;
import com.huizetime.basketball.application.MyApp;
import com.huizetime.basketball.bean.tv.TVData;
import com.huizetime.basketball.database.EventDB;
import com.huizetime.basketball.manager.BTManager;
import com.huizetime.basketball.manager.TVDataManager;
import com.huizetime.basketball.presenter.MainPresenter;
import com.huizetime.basketball.presenter.MainPresenterListener;
import com.huizetime.basketball.utils.PermissionUtils;
import com.huizetime.basketball.utils.QRCodeUtil;
import com.huizetime.basketball.view.MainView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenterListener mPresenter;
    private static final String TAG = "main";
    private BTManager btManager;
    //    private String address = "40:C6:2A:9B:0D:42";//魅族
    private String address = "24:0A:64:6F:E0:BE";//一体机
//    private String address = "C0:EE:FB:D2:87:0C";//一加3
//    private String address = "98:E7:F5:B0:86:54";//华为
//    private String address = "20:A6:80:A6:3C:6B";//华为平板

//    OnePlus 3-C0:EE:FB:D2:87:0C


//    Slate 21-    24:0A:64:6F:E0:BE
//    OnePlus 3-   C0:EE:FB:D2:87:0C
//    meizu        40:C6:2A:9B:0D:42
//    SCL-TL00-    98:E7:F5:B0:86:54

    TVDataManager tvDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        findView();
        initView();
        setData();


        createQRCode();
        initBTManager();


        EventDB eventDB = new EventDB();
        eventDB.setInfo("测试sugarOrm使用");
        eventDB.setCurrentTime(new Date().getTime());
        eventDB.save();


    }

    /**
     * 初始化数据
     * 1.权限
     */
    private void initData() {
        mPresenter = new MainPresenter(this);
        mPresenter.initData();
    }

    private void initView() {

    }

    private void findView() {

    }

    private void setData() {

    }


    private void createQRCode() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        QRCodeUtil.createQRImage("http://www.baidu.com", 400, bitmap, "/sdcard/jj.jpg");
    }

    private void initBTManager() {

        btManager = MyApp.getApp().getBTManager();
        BluetoothDevice device = btManager.getBTAdapter().getRemoteDevice(address);
        btManager.setAsUser(device, new BTManager.OnConnectListener() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "user: 连接成功");
            }

            @Override
            public void onWaitingConnectTo() {

            }

            @Override
            public void onConnecting() {
                Log.i(TAG, "user: 连接中...");
            }

            @Override
            public void onRead(byte[] bytes, int len) {
                Log.i(TAG, "user 收到: " + new String(bytes, 0, len));
            }

            @Override
            public void onWrite(byte[] bytes) {
                Log.i(TAG, "user 发送: " + new String(bytes));
            }

            @Override
            public void onFailed() {
                Log.i(TAG, "user 连接失败");
            }

            @Override
            public void onDisconnect() {
                Log.i(TAG, "user: 连接断开");
            }
        });
        tvDataManager = new TVDataManager(btManager);
    }

    public void onClick(View view) {
        if (btManager != null && btManager.isUserConnected()) {
            tvDataManager.sendImg(TVData.TYPE_A_LOGO, "/sdcard/jj.jpg");
        } else {
            Log.i(TAG, "onClick: 未连接");
        }
    }

    public void close(View view) {
        if (btManager != null && btManager.isUserConnected()) {
            mPresenter.close();
        } else {
            Log.i(TAG, "close: 已断开");
        }


    }

    public void connect(View view) {


        if (btManager != null && btManager.isUserConnected()) {
            Log.i(TAG, "close: 已连接,无需重置");
        } else {
            mPresenter.connect();
        }
    }


}
