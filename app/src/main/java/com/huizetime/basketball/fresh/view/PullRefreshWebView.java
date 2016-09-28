package com.huizetime.basketball.fresh.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class PullRefreshWebView extends WebView implements PullRefresh
{

	public PullRefreshWebView(Context context)
	{
		super(context);
	}

	public PullRefreshWebView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public PullRefreshWebView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public boolean canPullDown()
	{
		if (getScrollY() == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean canPullUp()
	{
		if (getScrollY() >= getContentHeight() * getScale()
				- getMeasuredHeight())
			return true;
		else
			return false;
	}
}
