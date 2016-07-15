package com.dyhdyh.widget.loadingbar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * 加载进度
 * @author dengyuhan
 * <p>可用于FrameLayout、RelativeLayout、DrawerLayout、CoordinatorLayout<p>
 * github https://github.com/dengyuhan
 * version 1.0
 * create 2016/6/22 17:16
 */
public class LoadingBar {
    public static LoadingBar mLoadingBar;

    private final ViewGroup mParent;
    private final Context mContext;
    private View mView;

    private final int backgroundColor=0xEEEEEE;

    private LoadingBar(ViewGroup parent) {
        mParent = parent;
        mContext = parent.getContext();
    }

    private View createDefaultView() {
        RelativeLayout relativeLayout=new RelativeLayout(mContext);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        ProgressBar progressBar=new ProgressBar(mContext);
        RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        progressBar.setLayoutParams(lp);
        relativeLayout.addView(progressBar);
        return relativeLayout;
    }

    public static void hide() {
        if (mLoadingBar!=null){
            mLoadingBar.hideView();
        }
    }

    public static LoadingBar show(View parent) {
        return show(parent,null,null);
    }

    public static LoadingBar show(View parent, View loadingView, View.OnClickListener onClickListener) {
        if (mLoadingBar==null){
            mLoadingBar = new LoadingBar(findSuitableParent(parent));
        }
        mLoadingBar.mParent.removeView(mLoadingBar.mView);
        if (loadingView==null){
            mLoadingBar.mView=mLoadingBar.createDefaultView();
        }else{
            mLoadingBar.mView=loadingView;
        }
        if (onClickListener!=null){
            mLoadingBar.mView.setOnClickListener(onClickListener);
        }
        mLoadingBar.mParent.addView(mLoadingBar.mView);
        mLoadingBar.mView.setBackgroundColor(mLoadingBar.backgroundColor);
        mLoadingBar.showView();
        return mLoadingBar;
    }

    private static ViewGroup findSuitableParent(View parent) {
        if (parent==null){
            return null;
        }
        if (parent instanceof FrameLayout||parent instanceof RelativeLayout||"android.support.v4.widget.DrawerLayout".equals(parent.getClass().getName())||"android.support.design.widget.CoordinatorLayout".equals(parent.getClass().getName())) {
            return (ViewGroup) parent;
        }else{
            throw new IllegalStateException("parent 必须是FrameLayout|RelativeLayout|DrawerLayout|CoordinatorLayout");
        }
    }

    final void showView() {
        mView.setVisibility(View.VISIBLE);
    }


    final void hideView() {
        mView.setVisibility(View.GONE);
    }
}
