package com.huizetime.basketball.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.primitives.UnsignedBytes;
import com.huizetime.basketball.R;
import com.huizetime.basketball.adapter.WatchListAdapter;
import com.huizetime.basketball.bean.WatchBean;
import com.huizetime.basketball.fresh.PullToRefreshLayout;
import com.huizetime.basketball.fresh.view.PullRefreshListView;
import com.huizetime.basketball.popup.PopupUserMenu;
import com.huizetime.basketball.presenter.WatchListPresenter;
import com.huizetime.basketball.presenter.WatchListPresenterListener;
import com.huizetime.basketball.view.WatchListView;

import java.util.ArrayList;
import java.util.List;

public class WatchListActivity extends AppCompatActivity implements WatchListView, PullToRefreshLayout.OnRefreshListener, WatchListAdapter.OnItemClickListener {
    private WatchListPresenterListener mPresenter;
    //fresh
    private PullToRefreshLayout mPullToRefreshLayout;
    private PullRefreshListView mListView;
    //用户
    private ImageView mIVUserIcon;
    private TextView mTVUerName;
    //列表
    private int mPage = 1;//页码
    private WatchListAdapter mAdapter;//适配器
    private List<WatchBean> mList;
    //蓝牙连接
    private boolean isConnect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

        findView();
        initView();
        initData();

    }


    private void findView() {
        mIVUserIcon = (ImageView) findViewById(R.id.user_icon);
        mTVUerName = (TextView) findViewById(R.id.user_name);
        mListView = (PullRefreshListView) findViewById(R.id.list_view);
        mPullToRefreshLayout = (PullToRefreshLayout) mListView.getParent();
        mPullToRefreshLayout.setOnRefreshListener(this);
    }

    private void initView() {

    }

    private void initData() {
        mPresenter = new WatchListPresenter(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_icon:
                mPresenter.onUserMenuClick();
                break;
            case R.id.connect:
                if (isConnect) {
                    mPresenter.shutDownConnect();
                } else {
                    mPresenter.connect();

                }
                break;
            case R.id.upload:

                break;
            case R.id.search:

                break;
        }
    }

    @Override
    public void onRefreshSuccess() {
        mPullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void onLoadMoreSuccess() {
        mPullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void onRefreshFailed() {
        mPullToRefreshLayout.refreshFinish(PullToRefreshLayout.FAIL);
    }

    @Override
    public void onLoadMore(List<WatchBean> list) {
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh(List<WatchBean> list) {
        mList = new ArrayList<>();
        mList.addAll(list);
        mAdapter = new WatchListAdapter(mList, this, this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onLoadMoreFailed() {
        mPullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.FAIL);
    }


    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        mPresenter.refresh();
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        mPresenter.loadMore();
    }

    @Override
    public void setPage(int page) {
        this.mPage = page;
    }

    @Override
    public int getPage() {
        return mPage;
    }


    @Override
    public void onItemClick(int watchId, int type) {
        mPresenter.onItemClick(watchId, type);
    }

    @Override
    public List<WatchBean> getList() {
        return mList;
    }

    @Override
    public void jumpToActivityFromItem(int watchId, Class aClass) {
        Intent intent = new Intent(this, aClass);
        intent.putExtra("watchId", watchId);
        startActivity(intent);
    }

    @Override
    public void jumpToActivityFromMenu(Class aClass) {
        startActivity(new Intent(this, aClass));
        if (aClass == LoginActivity.class) {
            finish();
        }

    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }
}