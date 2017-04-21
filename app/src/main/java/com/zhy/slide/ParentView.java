package com.zhy.slide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.FrameLayout;

public class ParentView extends FrameLayout {
    private CustomWebView mDispatchWebView;

    public ParentView (Context context) {
        super(context);
    }

    
    public ParentView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}


	public ParentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}


	public void preventParentTouchEvent (WebView view) {
        mDispatchWebView = (CustomWebView)view;
    }

    //�����¼������ж�
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /**
         * ����ҳ��ĳ�ڵ��touchstart�¼������preventHostViewTouchMoveʱ
         * ��ʱ���غ�����TouchMove�¼���mDispatchWebView
         * ������֮ǰmDispatchWebView���յ�һ��Touch Cancel��Ϣ
         * ��ʱӦ����mDispatchWebView��ʱ�������Cancel�¼�����TouchUp��ָ���
         * ������ҳ��DOM�ڵ�󶨵��¼��������̶����½ڵ㲻���ֻ���Ӧ
         * */
        if (ev.getAction() == MotionEvent.ACTION_MOVE && mDispatchWebView != null) {
            mDispatchWebView.ignoreTouchCancel(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mDispatchWebView != null){
            switch (ev.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    mDispatchWebView.onTouchEvent(ev);
                    break;
                default:    // ���½�����Touch Up ���� Touch Cancel����ȡ����ռ
                    mDispatchWebView.ignoreTouchCancel(false);
                    mDispatchWebView.onTouchEvent(ev);
                    mDispatchWebView = null;
                    break;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }
}