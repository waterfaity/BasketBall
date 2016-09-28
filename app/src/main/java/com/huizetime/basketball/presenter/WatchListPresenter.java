package com.huizetime.basketball.presenter;

import android.app.Activity;

import com.huizetime.basketball.activity.ChangePasswordActivity;
import com.huizetime.basketball.activity.LoginActivity;
import com.huizetime.basketball.activity.WatchLoggingActivity;
import com.huizetime.basketball.activity.WatchInfoActivity;
import com.huizetime.basketball.activity.WatchListActivity;
import com.huizetime.basketball.bean.WatchBean;
import com.huizetime.basketball.model.WatchListModel;
import com.huizetime.basketball.model.WatchListModelSimple;
import com.huizetime.basketball.popup.PopupUserMenu;
import com.huizetime.basketball.utils.ToastUtils;
import com.huizetime.basketball.view.WatchListView;

import java.util.ArrayList;

/**
 * Created by water_fairy on 2016/9/28.
 */
public class WatchListPresenter implements WatchListPresenterListener {
    private final int TYPE_NORMAL = 0;
    private final int TYPE_REFRESH = 1;
    private final int TYPE_LOAD_MORE = 2;
    private WatchListView mView;
    private Activity mActivity;
    private WatchListModel mModel;
    private boolean mHasNext;
    private int mType;

    public WatchListPresenter(WatchListActivity activity) {
        mView = activity;
        mActivity = activity;
        mModel = new WatchListModelSimple(this, activity);
        mModel.initData();
    }

    @Override
    public void refresh() {
        mView.setPage(1);
        getData(1, TYPE_REFRESH);
    }

    @Override
    public void loadMore() {
        if (mHasNext) {
            int page = mView.getPage() + 1;
            mView.setPage(page);
            getData(page, TYPE_LOAD_MORE);
        } else {
            ToastUtils.show("已经加载全部比赛列表");
        }
    }

    @Override
    public void getData(int page, int type) {
        mType = type;
        mModel.getData(page);
    }

    @Override
    public void onSuccess(Object object) {

        switch (mType) {
            case TYPE_NORMAL:
                mView.onRefresh(new ArrayList<WatchBean>());
                break;
            case TYPE_REFRESH:
                mView.onRefreshSuccess();
                mView.onRefresh(new ArrayList<WatchBean>());
                break;
            case TYPE_LOAD_MORE:
                mView.onLoadMoreSuccess();
                mView.onLoadMore(new ArrayList<WatchBean>());
                break;
        }
    }

    @Override
    public void onError(int code) {
        switch (mType) {
            case TYPE_NORMAL:
                break;
            case TYPE_LOAD_MORE:
                mView.setPage(mView.getPage() - 1);
                mView.onLoadMoreFailed();
                break;
            case TYPE_REFRESH:
                mView.onRefreshFailed();
                break;
        }
    }

    @Override
    public void onItemClick(int watchId, int type) {
        if (type == WatchBean.TYPE_CHECK) {
            mView.jumpToActivityFromItem(watchId, WatchInfoActivity.class);
        } else {
            mView.jumpToActivityFromItem(watchId, WatchLoggingActivity.class);
        }

    }

    @Override
    public void onUserMenuClick() {
        final PopupUserMenu menu = new PopupUserMenu(mActivity);
        menu.setOnMenuClickListener(new PopupUserMenu.OnUserMenuClickListener() {
            @Override
            public void onMenuClick(int position) {
                Class aClass = null;
                if (position == PopupUserMenu.MENU_QUIT) {
                    aClass = LoginActivity.class;
                } else {
                    aClass = ChangePasswordActivity.class;
                }
                mView.jumpToActivityFromMenu(aClass);
                menu.dismiss();
            }
        });
        menu.showPopupWindow();

    }

    @Override
    public void connect() {
        mModel.connect();
    }

    @Override
    public void disconnect() {

    }

    @Override
    public void onConnectError() {

    }

    @Override
    public void onDisconnect() {
    }

    @Override
    public void onConnectSuccess() {

    }

    @Override
    public void onConnecting() {

    }

    @Override
    public void setBlueToothAddress() {

    }
}
