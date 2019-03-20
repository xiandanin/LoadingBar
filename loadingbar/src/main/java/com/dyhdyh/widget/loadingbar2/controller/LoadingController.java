package com.dyhdyh.widget.loadingbar2.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dyhdyh.widget.loadingbar2.factory.LoadingFactory;

/**
 * loading控制器 决定了loading如何显示
 *
 * @author dengyuhan
 * created 2019/3/14 11:23
 */
public interface LoadingController<F extends LoadingFactory> {

    void show(@NonNull F factory, @Nullable Object[] extras);

    void cancel();

    /**
     * 是否可以被回收
     * @return
     */
    boolean isCanRecycled();
}
