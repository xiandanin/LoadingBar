package com.dyhdyh.widget.loadingbar2.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.dyhdyh.widget.loadingbar2.factory.LoadingFactory;
import com.dyhdyh.widget.loadingbar2.strategy.CoverParentStrategy;
import com.dyhdyh.widget.loadingbar2.strategy.ParentStrategy;

/**
 * View的loading控制器
 *
 * @author dengyuhan
 * created 2019/3/14 11:22
 */
public class LoadingViewController implements LoadingController<LoadingFactory<ViewGroup, View>> {
    private View mParent;
    private ViewGroup mRealParent;
    private View mView;

    private String mFactoryKey;

    private ParentStrategy mParentStrategy;

    public LoadingViewController(View parent) {
        this.mParent = parent;
    }

    public LoadingViewController setParentStrategy(ParentStrategy parentStrategy) {
        this.mParentStrategy = parentStrategy;
        return this;
    }

    @Override
    public void show(@NonNull LoadingFactory<ViewGroup, View> factory, @Nullable Object[] extras) {
        final ParentStrategy strategy = getParentStrategy();
        ViewGroup realParent = strategy.findSuitableParent(mParent);

        final String factoryKey = factory.getKey();
        final boolean changed = mFactoryKey == null || !mFactoryKey.equals(factoryKey);

        //如果跟已有的parent不一样 或者样式发生变化 就先移除之前的View
        if (mRealParent != null && (mRealParent != realParent || changed)) {
            mRealParent.removeView(mView);
        }

        //如果还没有View 或者样式发生变化 就重新创建
        if (mView == null || changed) {
            this.mView = factory.onCreate(realParent);
        }

        factory.updateStatus(extras);

        this.mRealParent = realParent;
        //没有父级才添加
        if (mRealParent != null && mView != null && mView.getParent() == null) {
            mRealParent.addView(mView);
            this.mFactoryKey = factoryKey;
        }
    }

    @Override
    public void cancel() {
        if (mRealParent != null) {
            mRealParent.removeView(mView);
        }
        mParent = null;
        mRealParent = null;
        mView = null;
        mFactoryKey = null;
    }

    @Override
    public boolean isCanRecycled() {
        if (mRealParent != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                return !mRealParent.isAttachedToWindow();
            } else {
                final Context context = mRealParent.getContext();
                if (context instanceof Activity) {
                    return ((Activity) context).isFinishing();
                }
            }
        }
        return false;
    }

    protected ParentStrategy getParentStrategy() {
        if (mParentStrategy == null) {
            mParentStrategy = new CoverParentStrategy();
        }
        return mParentStrategy;
    }
}
