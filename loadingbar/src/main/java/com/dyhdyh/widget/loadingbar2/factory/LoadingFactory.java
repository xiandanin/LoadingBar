package com.dyhdyh.widget.loadingbar2.factory;

import android.support.annotation.Nullable;

/**
 * Loading工厂 决定了loading的样式
 * @author dengyuhan
 * created 2019/3/14 15:51
 */
public interface LoadingFactory<P, L> {
    /**
     * 工厂类的标识符
     * 在cancel()之前多次调用show()时，当key相同时不会重新调用onCreate
     * @return
     */
    String getKey();

    L onCreate(P params);

    void updateStatus(@Nullable Object[] extras);

}
