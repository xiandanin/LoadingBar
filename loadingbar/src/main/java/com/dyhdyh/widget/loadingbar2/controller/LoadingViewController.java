package com.dyhdyh.widget.loadingbar2.controller;

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
        if (mView == null) {
            this.mView = factory.onCreate(realParent);
        }

        factory.updateStatus(extras);

        if (mRealParent != null && mRealParent != realParent) {
            //如果跟已有的parent不一样 就先移除之前的
            mRealParent.removeView(mView);
        }
        this.mRealParent = realParent;
        if (mRealParent != null && mView != null && mView.getParent() == null) {
            mRealParent.addView(mView);
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
    }

    protected ParentStrategy getParentStrategy() {
        if (mParentStrategy == null) {
            mParentStrategy = new CoverParentStrategy();
        }
        return mParentStrategy;
    }
}
