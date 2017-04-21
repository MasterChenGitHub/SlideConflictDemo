package com.zhy.slide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

public class CustomWebView extends WebView {


	public CustomWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomWebView(Context context) {
		super(context);
	}

	
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
    int action = ev.getAction();
    if (action == MotionEvent.ACTION_DOWN) {
        onScrollChanged(getScrollX(), getScrollY(), getScrollX(), getScrollY());
    }
    return action == MotionEvent.ACTION_CANCEL && mIgnoreTouchCancel || super.onTouchEvent(ev);
}
    
    boolean mIgnoreTouchCancel;

    public void ignoreTouchCancel (boolean val) {
        mIgnoreTouchCancel = val;
    }
}
