package com.huizetime.basketball.presenter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.huizetime.basketball.activity.ChangePasswordActivity;
import com.huizetime.basketball.activity.LoginActivity;
import com.huizetime.basketball.activity.WatchLoggingActivity;
import com.huizetime.basketball.activity.WatchInfoActivity;
import com.huizetime.basketball.activity.WatchListActivity;
import com.huizetime.basketball.bean.WatchBean;
import com.huizetime.basketball.listener.OnBelClickListener;
import com.huizetime.basketball.model.WatchListModel;
import com.huizetime.basketball.model.WatchListModelSimple;
import com.huizetime.basketball.popup.PopupUserMenu;
import com.huizetime.basketball.utils.AnimationUtils;
import com.huizetime.basketball.utils.DialogUtils;
import com.huizetime.basketball.utils.ToastUtils;
import com.huizetime.basketball.view.WatchListView;

import java.util.ArrayList;
import java.util.List;

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
    private boolean mHasNext = true;
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
        //测试
        onSuccess(null);
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

        //测试
        onSuccess(null);
    }

    @Override
    public void getData(int page, int type) {
        mType = type;
        mModel.getData(page);
    }

    @Override
    public void onSuccess(Object object) {
        List<WatchBean> list = new ArrayList<>();
        WatchBean watchBean1 = new WatchBean();
        watchBean1.setId(1);
        watchBean1.setType(WatchBean.TYPE_LOGGING);
        WatchBean watchBean2 = new WatchBean();
        watchBean2.setId(2);
        watchBean2.setType(WatchBean.TYPE_CHECK);
        list.add(watchBean1);
        list.add(watchBean2);

        switch (mType) {
            case TYPE_NORMAL:
                mView.onRefresh(list);
                break;
            case TYPE_REFRESH:
                mView.onRefreshSuccess();
                mView.onRefresh(list);
                break;
            case TYPE_LOAD_MORE:
                mView.onLoadMoreSuccess();
                mView.onLoadMore(list);
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
    public void onItemClick(final int watchId, int type) {
        if (type == WatchBean.TYPE_CHECK) {
            mView.jumpToActivityFromItem(watchId, WatchInfoActivity.class);
        } else {
            if (!mView.isConnect()) {
                DialogUtils.show2(mActivity, "未连接电视记分板,记录结果不能在计分板显示,是否继续?", new OnBelClickListener() {
                    @Override
                    public void onBelClick(boolean bel) {
                        if (bel) {
                            mView.jumpToActivityFromItem(watchId, WatchLoggingActivity.class);
                        }
                    }
                });
            } else {
                mView.jumpToActivityFromItem(watchId, WatchLoggingActivity.class);
            }


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
                    final Class finalAClass = aClass;
                    if (mView.isConnect()) {
                        DialogUtils.show2(mActivity, "与电视记分板的连接将断开,是否继续?", new OnBelClickListener() {
                            @Override
                            public void onBelClick(boolean bel) {
                                if (bel) {
                                    mModel.shutDownConnect();
                                    mView.jumpToActivityFromMenu(finalAClass);
                                }
                            }
                        });
                    } else {
                        mView.jumpToActivityFromMenu(aClass);
                    }
                } else {
                    aClass = ChangePasswordActivity.class;
                    mView.jumpToActivityFromMenu(aClass);
                }
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
    public void shutDownConnect() {
        DialogUtils.show2(mActivity, "是否断开与电视记分板的连接?", new OnBelClickListener() {
            @Override
            public void onBelClick(boolean bel) {
                if (bel) {
                    mModel.shutDownConnect();
                }
            }
        });
    }

    @Override
    public void onSearchClick() {
        RelativeLayout searchBar = mView.getSearchBar();
        RelativeLayout searchLayout = mView.getSearchLayout();
        searchBar.setVisibility(View.VISIBLE);
        searchLayout.setVisibility(View.VISIBLE);
        searchBar.startAnimation(AnimationUtils.getSearchBarAnim(true));
        searchLayout.startAnimation(AnimationUtils.getSearchBarAnim(true));
        mView.setSearchBarVisibility(true);
    }

    @Override
    public void onSearchBackClick() {
        RelativeLayout searchBar = mView.getSearchBar();
        RelativeLayout searchLayout = mView.getSearchLayout();
        searchBar.startAnimation(AnimationUtils.getSearchBarAnim(false));
        searchLayout.startAnimation(AnimationUtils.getSearchBarAnim(false));
        searchBar.setVisibility(View.GONE);
        searchLayout.setVisibility(View.GONE);
        mView.setSearchBarVisibility(false);
        mView.initSearchLayout();
    }

    @Override
    public void quit() {
        DialogUtils.show2(mActivity, "是否退出?", new OnBelClickListener() {
            @Override
            public void onBelClick(boolean bel) {
                if (bel) {
                    if (mView.isConnect()) {
                        mModel.shutDownConnect();
                    }
                    mView.back();
                }
            }
        });
    }

    @Override
    public void onSearch() {
        if (!TextUtils.isEmpty(mView.getSearchContent())) {
            mModel.search(mView.getSearchContent());
        } else {
            show("请输入搜索内容!");
        }
    }


    @Override
    public void onConnectError() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mView.stopLoading();
            }
        });
    }

    @Override
    public void onDisconnect() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mView.setConnect(false);
            }
        });

    }

    @Override
    public void onConnectSuccess() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mView.setConnect(true);
                mView.stopLoading();
            }
        });
    }

    @Override
    public void onConnecting() {

    }

    @Override
    public void setBlueToothAddress() {

    }

    public void show(final String str) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show(str);
            }

        });

    }

    @Override
    public void searchResult(List<WatchBean> list) {
        mView.OnSearchResult(list);
    }
}
