package com.huizetime.basketball.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huizetime.basketball.R;
import com.huizetime.basketball.adapter.WatchListAdapter;
import com.huizetime.basketball.bean.WatchBean;
import com.huizetime.basketball.database.DBUtils;
import com.huizetime.basketball.fresh.PullToRefreshLayout;
import com.huizetime.basketball.fresh.view.PullRefreshListView;
import com.huizetime.basketball.popup.PopupLoading;
import com.huizetime.basketball.presenter.WatchListPresenter;
import com.huizetime.basketball.presenter.WatchListPresenterListener;
import com.huizetime.basketball.utils.DialogUtils;
import com.huizetime.basketball.utils.ShareUtils;
import com.huizetime.basketball.view.WatchListView;

import java.util.ArrayList;
import java.util.List;

public class WatchListActivity extends AppCompatActivity implements WatchListView, PullToRefreshLayout.OnRefreshListener, WatchListAdapter.OnItemClickListener, TextWatcher {
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
    private String mAddress = "24:0A:64:6F:E0:BE";//一体机
    //连接图标
    private ImageView mIVConnect;
    //连接中
    private PopupLoading mLoading;
    //搜索bar
    private boolean isSearchBarVisibility;
    private RelativeLayout mRLSearchBar;
    private ImageView mIVSearchBack, mIVSearchButton, mIVSearchClear;
    private RelativeLayout mLLSearchLayout;
    private ListView mLVSearch;
    private LinearLayout mLLSearchNoResult;
    private EditText mETSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);
        {
//            测试
            DBUtils.insertWatchDB();
        }
        initBTAddress();
        findView();
        initView();
        initData();

    }

    private void initBTAddress() {
        ShareUtils.getSetting().edit().putString("address", mAddress).apply();
    }


    private void findView() {
        mIVUserIcon = (ImageView) findViewById(R.id.user_icon);
        mTVUerName = (TextView) findViewById(R.id.user_name);
        mListView = (PullRefreshListView) findViewById(R.id.list_view);
        mPullToRefreshLayout = (PullToRefreshLayout) mListView.getParent();
        mPullToRefreshLayout.setOnRefreshListener(this);
        mIVConnect = (ImageView) findViewById(R.id.connect);
        mRLSearchBar = (RelativeLayout) findViewById(R.id.search_bar);
        mIVSearchBack = (ImageView) findViewById(R.id.search_back);
        mIVSearchButton = (ImageView) findViewById(R.id.search_button);
        mLLSearchLayout = (RelativeLayout) findViewById(R.id.search_layout);
        mLVSearch = (ListView) findViewById(R.id.search_list_view);
        mLLSearchNoResult = (LinearLayout) findViewById(R.id.search_no_result);
        mETSearch = (EditText) findViewById(R.id.search_content);
        mIVSearchClear = (ImageView) findViewById(R.id.search_clear);
    }

    private void initView() {
        mETSearch.addTextChangedListener(this);
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
                    mLoading = DialogUtils.loading(this);
                }
                break;
            case R.id.upload:

                break;
            case R.id.search:
                mPresenter.onSearchClick();
                break;
            case R.id.search_back:
                mPresenter.onSearchBackClick();
                break;
            case R.id.search_button:
                mPresenter.onSearch();
                break;
            case R.id.search_clear:
                initSearchLayout();
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

    @Override
    public boolean isConnect() {
        return isConnect;
    }

    @Override
    public void setConnect(boolean connect) {

        isConnect = connect;
        setConnectState(isConnect);
    }

    private void setConnectState(boolean connect) {
        if (connect) {
            mIVConnect.setImageResource(R.drawable.ic_bluetooth_connected_black_24dp);
        } else {
            mIVConnect.setImageResource(R.drawable.ic_bluetooth_disabled_black_24dp);
        }
    }

    @Override
    public void stopLoading() {
        if (mLoading != null) {
            mLoading.stopLoading();
        }
    }

    @Override
    public RelativeLayout getSearchBar() {
        return mRLSearchBar;
    }

    @Override
    public RelativeLayout getSearchLayout() {
        return mLLSearchLayout;
    }

    @Override
    public boolean isSearchBarVisibility() {
        return isSearchBarVisibility;
    }

    @Override
    public void setSearchBarVisibility(boolean searchBarVisibility) {
        isSearchBarVisibility = searchBarVisibility;
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isSearchBarVisibility) {
                mPresenter.onSearchBackClick();
            } else {
                mPresenter.quit();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public String getSearchContent() {
        return mETSearch.getText().toString();
    }

    @Override
    public void OnSearchResult(List<WatchBean> list) {
        if (list != null && list.size() > 0) {
            mLVSearch.setVisibility(View.VISIBLE);
            mLLSearchNoResult.setVisibility(View.GONE);
            mLVSearch.setAdapter(new WatchListAdapter(list, this, this));
        } else {
            mLVSearch.setVisibility(View.GONE);
            mLLSearchNoResult.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void initSearchLayout() {
        mLVSearch.setVisibility(View.GONE);
        mLLSearchNoResult.setVisibility(View.GONE);
        mETSearch.setText("");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(s)) {
            mIVSearchClear.setVisibility(View.GONE);
        } else {
            mIVSearchClear.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}