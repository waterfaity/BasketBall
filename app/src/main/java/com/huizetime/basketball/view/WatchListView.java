package com.huizetime.basketball.view;

import android.widget.RelativeLayout;

import com.huizetime.basketball.bean.WatchBean;

import java.util.List;
import java.util.Objects;

/**
 * Created by water_fairy on 2016/9/28.
 */
public interface WatchListView {
    /**
     * 刷新成功/第一次加载成功
     */
    void onRefreshSuccess();

    /**
     * 加载更多成功
     */

    void onLoadMoreSuccess();

    /**
     * 设置当前页
     *
     * @param page
     */

    void setPage(int page);

    /**
     * 获取当前页码
     *
     * @return
     */
    int getPage();

    /**
     * 加载更多失败
     */
    void onLoadMoreFailed();

    /**
     * 刷新失败
     */
    void onRefreshFailed();

    /**
     * 展示加载更多
     *
     * @param list
     */


    void onLoadMore(List<WatchBean> list);

    /**
     * 展示刷新
     *
     * @param list
     */

    void onRefresh(List<WatchBean> list);

    /**
     * 获取赛事列表
     *
     * @return
     */
    List<WatchBean> getList();

    /**
     * 点击比赛列表 跳转页面
     *
     * @param watchId
     */
    void jumpToActivityFromItem(int watchId, Class aClass);

    /**
     * 点击用户头像按钮 跳转页面
     *
     * @param aClass
     */

    void jumpToActivityFromMenu(Class aClass);

    /**
     * 连接状态
     *
     * @return
     */
    boolean isConnect();

    /**
     * 设置连接状态
     *
     * @param connect
     */
    void setConnect(boolean connect);

    /**
     * 取消连接等待
     */
    void stopLoading();


    /**
     * 返回上一页面
     */
    void back();

    /**
     * 获取搜索内容
     *
     * @return
     */
    String getSearchContent();

    /**
     * 得到搜索结果
     *
     * @param list
     */
    void OnSearchResult(List<WatchBean> list);

    /**
     * 初始化搜索layout
     */
    void initSearchLayout();

    /**
     * 获取搜索bar
     *
     * @return
     */
    RelativeLayout getSearchBar();

    /**
     * 获取搜索layout
     *
     * @return
     */
    RelativeLayout getSearchLayout();

    /**
     * @return
     */
    boolean isSearchBarVisibility();

    /**
     * @param searchBarVisibility
     */
    void setSearchBarVisibility(boolean searchBarVisibility);
}
