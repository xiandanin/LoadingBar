package com.dyhdyh.widget.loading.bar;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.dyhdyh.widget.loading.LoadingConfig;
import com.dyhdyh.widget.loading.factory.LoadingFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 加载进度
 * <p>可用于FrameLayout、RelativeLayout、DrawerLayout、CoordinatorLayout、CardView<p>
 *
 * @author dengyuhan
 *         github  https://github.com/dengyuhan
 *         created 2016/6/22 17:16
 */
public final class LoadingBar implements ILoadingBar {

    private static final Map<View, LoadingBar> LOADINGBARS = new HashMap<>();
    private static int LOADING_LIMIT = 15;//默认15,超过会检查资源释放

    private ViewGroup mParent;
    private View mView;
    private OnLoadingBarListener mListener;

    private LoadingBar(ViewGroup parent, LoadingFactory factory) {
        mParent = parent;
        mView = factory.onCreateView(mParent);
    }


    /**
     * 显示loading
     */
    @Override
    public void show() {
        if (mView != null) {
            mView.setVisibility(View.VISIBLE);
            if (mView.getParent() != null) {
                mParent.removeView(mView);
            }
            mParent.addView(mView);
        }
    }

    /**
     * 取消loading
     */
    @Override
    public void cancel() {
        if (mView != null) {
            mView.setVisibility(View.GONE);
            mParent.removeView(mView);
            mView = null;
            if (this.mListener != null) {
                this.mListener.onCancel(mParent);
            }
        }
    }

    public LoadingBar setOnClickListener(View.OnClickListener listener) {
        if (mView != null) {
            mView.setOnClickListener(listener);
        }
        return this;
    }

    public LoadingBar setOnLoadingBarListener(OnLoadingBarListener mListener) {
        this.mListener = mListener;
        return this;
    }

    public static LoadingBar make(View parent) {
        return make(parent, LoadingConfig.getLoadingFactory());
    }

    public static LoadingBar make(View parent, LoadingFactory factory) {
        //如果已经有Loading在显示了
        if (LOADINGBARS.containsKey(parent)) {
            LoadingBar loadingBar = LOADINGBARS.get(parent);
            loadingBar.mParent.removeView(loadingBar.mView);
        }
        LoadingBar newLoadingBar = new LoadingBar(findSuitableParent(parent), factory);
        LOADINGBARS.put(parent, newLoadingBar);
        return newLoadingBar;
    }


    public void show(final View loadingView) {
        make(mParent, new LoadingFactory() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return loadingView;
            }
        }).show();
    }

    /**
     * 根据父节点取消单个loading
     *
     * @param parent show传过来的父节点
     */
    public static void cancel(View parent) {
        LoadingBar loadingBar = LOADINGBARS.get(parent);
        if (loadingBar != null) {
            loadingBar.cancel();
        }
        LOADINGBARS.remove(parent);
    }

    /**
     * 取消所有loading
     */
    private static void cancelAll() {
        Iterator<Map.Entry<View, LoadingBar>> it = LOADINGBARS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<View, LoadingBar> entry = it.next();
            cancel(entry.getKey());
        }
    }


    public static void release() {
        release(LOADING_LIMIT);
    }

    /**
     * 释放无用的资源
     * <p>可在BaseActivity onDestroy调用</p>
     * @param limit loading池的限制,超过数量才检查资源释放
     */
    public static void release(int limit) {
        if (limit <= 0) {
            limit = LOADING_LIMIT;
        }
        if (LOADINGBARS.size() < limit) {
            return;
        }
        Log.d("LoadingBar","release before loading size - "+LOADINGBARS.size());
        Iterator<Map.Entry<View, LoadingBar>> it = LOADINGBARS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<View, LoadingBar> entry = it.next();
            Context context = entry.getKey().getContext();
            if (context instanceof Activity && ((Activity) context).isFinishing()) {
                it.remove();
            }
        }
        Log.d("LoadingBar","release after loading size - "+LOADINGBARS.size());
    }

    /**
     * 找到合适的父布局
     *
     * @param parent
     * @return
     */
    private static ViewGroup findSuitableParent(View parent) {
        if (parent == null) {
            return null;
        }
        View suitableParent = parent;
        do {
            if (suitableParent instanceof FrameLayout || suitableParent instanceof RelativeLayout ||
                    "android.support.v4.widget.DrawerLayout".equals(suitableParent.getClass().getName()) ||
                    "android.support.design.widget.CoordinatorLayout".equals(suitableParent.getClass().getName()) ||
                    "android.support.v7.widget.CardView".equals(suitableParent.getClass().getName())) {
                return (ViewGroup) suitableParent;
            } else {
                final ViewParent viewParent = suitableParent.getParent();
                suitableParent = viewParent instanceof View ? (View) viewParent : null;
                return (ViewGroup) suitableParent;
            }
        } while (suitableParent != null);
    }

}
