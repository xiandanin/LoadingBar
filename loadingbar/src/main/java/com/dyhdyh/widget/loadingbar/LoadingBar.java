package com.dyhdyh.widget.loadingbar;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 加载进度
 *
 * @author dengyuhan
 *         <p>可用于FrameLayout、RelativeLayout、DrawerLayout、CoordinatorLayout、CardView<p>
 *         github https://github.com/dengyuhan
 *         version 1.3
 *         create 2016/6/22 17:16
 */
public class LoadingBar {
    /**
     * LoadingBar管理
     * key=父节点;value=LoadingBar
     */
    private static final Map<View, LoadingBar> mLoadingBars = new LinkedHashMap<>();

    private ViewGroup mParent;
    private Context mContext;
    private View mView;

    private LoadingBar(ViewGroup parent) {
        mParent = parent;
        mContext = parent.getContext();
    }


    /**
     * 改名叫cancelAll
     */
    @Deprecated
    public static void hide() {
        cancelAll();
    }

    /**
     * 取消所有loading
     */
    public static void cancelAll() {
        for (Map.Entry<View, LoadingBar> entry : mLoadingBars.entrySet()) {
            entry.getValue().cancel();
        }
    }

    /**
     * 根据父节点取消单个loading
     *
     * @param parent show传过来的父节点
     */
    public static void cancel(View parent) {
        mLoadingBars.get(parent).cancel();
    }


    /**
     * 取消loading
     */
    public void cancel() {
        if (mView != null) {
            hideView();
        }
    }

    public static LoadingBar show(View parent) {
        return show(parent, null, null);
    }

    public static LoadingBar show(View parent, View loadingView, View.OnClickListener onClickListener) {
        //如果已经有Loading在显示了
        LoadingBar loadingBar;
        if (mLoadingBars.containsKey(parent)) {
            loadingBar = mLoadingBars.get(parent);
        } else {
            //如果没有就新建一个
            loadingBar = new LoadingBar(findSuitableParent(parent));
            mLoadingBars.put(parent, loadingBar);
        }
        //
        loadingBar.mParent.removeView(loadingBar.mView);
        if (loadingView == null) {
            loadingBar.mView = loadingBar.createDefaultLoadingView();
        } else {
            loadingBar.mView = loadingView;
        }
        loadingBar.mParent.addView(loadingBar.mView);
        if (onClickListener != null) {
            loadingBar.mView.setOnClickListener(onClickListener);
        }
        loadingBar.showView();
        return loadingBar;
    }

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


    public View createDefaultLoadingView() {
        FrameLayout relativeLayout = new FrameLayout(mContext);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ProgressBar progressBar = new ProgressBar(mContext);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.loadingbar_progressbar_vertical));
        }
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        lp.gravity = Gravity.CENTER;
        progressBar.setLayoutParams(lp);
        relativeLayout.addView(progressBar);
        int color = relativeLayout.getResources().getColor(R.color.bg_loading);
        relativeLayout.setBackgroundColor(color);
        return relativeLayout;
    }

    final void showView() {
        mView.setVisibility(View.VISIBLE);
    }


    final void hideView() {
        mView.setVisibility(View.GONE);
    }
}
