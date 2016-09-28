package com.huizetime.basketball.manager;

import android.bluetooth.BluetoothDevice;

import com.huizetime.basketball.application.MyApp;

/**
 * Created by water_fairy on 2016/9/26.
 */
public class ConnectManager {
    private static final ConnectManager connectManager = new ConnectManager();
    private BTManager btManager;
    //先设置监听,再设置 用户端/服务器
    private UserConnectListener userListener;
    private ServerConnectListener serverListener;

    private ConnectManager() {
    }

    public static ConnectManager getInstance() {

        return connectManager;
    }

    private void initBTManager() {
        if (btManager == null) {
            btManager = MyApp.getApp().getBTManager();
        }
    }

    //设置用户端
    public void setAsUser(BluetoothDevice device) {
        initBTManager();
        btManager.setAsUser(device, onConnectListener);
    }

    //设置服务器
    public void setAsServer() {
        initBTManager();
        btManager.setAsServer(onConnectListener);
    }

    //设置用户端监听
    public void setUserListener(UserConnectListener userListener) {
        this.userListener = userListener;
    }

    //设置服务器监听
    public void setServerListener(ServerConnectListener serverListener) {
        this.serverListener = serverListener;
    }


    private BTManager.OnConnectListener onConnectListener = new BTManager.OnConnectListener() {
        @Override
        public void onSuccess() {
            if (userListener != null) {
                userListener.onConnectSuccess();
            }
            if (serverListener != null) {
                serverListener.onConnectSuccess();
            }
        }

        @Override
        public void onWaitingConnectTo() {
            if (serverListener != null) {
                serverListener.onWaitingConnect();
            }
        }

        @Override
        public void onConnecting() {
            if (userListener != null) {
                userListener.onConnecting();
            }
        }

        @Override
        public void onRead(byte[] bytes, int len) {
            if (serverListener != null) {
                serverListener.onRead(bytes, len);
            }
            if (userListener != null) {
                userListener.onRead(bytes, len);
            }
        }

        @Override
        public void onWrite(byte[] bytes) {
            if (userListener != null) {
                userListener.onWrite(bytes);
            }
        }

        @Override
        public void onFailed() {
            if (userListener != null) {
                userListener.onConnectError();
            }
            if (serverListener != null) {
                serverListener.onOpenServerError();
            }
        }

        @Override
        public void onDisconnect() {
            if (userListener != null) {
                userListener.onDisconnect();
            }
            if (serverListener != null) {
                serverListener.onDisconnect();
            }
        }
    };

    public void close() {
        btManager.close();
    }

    //用户端监听
    public interface UserConnectListener {
        /**
         * 连接成功
         */
        void onConnectSuccess();

        /**
         * 连接中...
         */
        void onConnecting();

        /**
         * 连接断开
         */

        void onDisconnect();

        /**
         * 连接失败
         */

        void onConnectError();

        /**
         * 输入监听
         *
         * @param bytes
         */

        void onWrite(byte[] bytes);

        /**
         * 读取数据 :主要用于返回结果
         *
         * @param bytes
         * @param len
         */
        void onRead(byte[] bytes, int len);
    }

    //服务器监听
    public interface ServerConnectListener {
        /**
         * 连接成功
         */
        void onConnectSuccess();

        /**
         * 等待连接中...
         */
        void onWaitingConnect();

        /**
         * 连接断开
         */

        void onDisconnect();

        /**
         * 服务器开启失败
         */
        void onOpenServerError();

        /**
         * 读取监听
         * (有待优化)
         *
         * @param bytes
         * @param len
         */

        void onRead(byte[] bytes, int len);
    }
    public boolean isUserConnect(){
        return btManager.isUserConnected();
    }
    public boolean isServerConnect(){
        return btManager.isServerConnected();
    }
}
