package com.dyhdyh.widget.loadingbar2.factory;

import android.support.annotation.Nullable;

/**
 * Loading工厂 决定了loading的样式
 * @author dengyuhan
 * created 2019/3/14 15:51
 */
public interface LoadingFactory<P, L> {

    L onCreate(P params);

    void updateStatus(@Nullable Object[] extras);

}
