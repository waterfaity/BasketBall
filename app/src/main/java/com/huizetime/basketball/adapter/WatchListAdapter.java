package com.huizetime.basketball.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.huizetime.basketball.R;
import com.huizetime.basketball.bean.WatchBean;

import java.util.List;

/**
 * Created by water_fairy on 2016/9/28.
 */

public class WatchListAdapter extends BaseAdapter {
    private List<WatchBean> mList;
    private Context mContext;
    private OnItemClickListener mListener;


    public WatchListAdapter(List<WatchBean> mList, Context mContext, OnItemClickListener onItemClickListenerListener) {
        this.mList = mList;
        this.mContext = mContext;
        this.mListener = onItemClickListenerListener;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private ViewHolder mViewHolder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_watch_list, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        WatchBean watchBean = mList.get(position);
        mViewHolder = (ViewHolder) convertView.getTag();
        mViewHolder.mView.setTag(watchBean);
        mViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WatchBean entity = (WatchBean) v.getTag();
                mListener.onItemClick(entity.getId(), entity.getType());
            }
        });
        return convertView;
    }

    private class ViewHolder {

        private View mView;

        public ViewHolder(View convertView) {
            mView = convertView.findViewById(R.id.root_view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int watchId, int type);
    }
}
