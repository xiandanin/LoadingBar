package com.dyhdyh.widget.loadingbar2;

import android.util.SparseArray;

/**
 * LoadingBar池
 *
 * @author dengyuhan
 * created 2019/3/14 11:12
 */
public final class LoadingBarPool {

    private static final SparseArray<LoadingBar> POOL = new SparseArray<>();


    static void put(Object key, LoadingBar instance) {
        POOL.put(System.identityHashCode(key), instance);
    }


    static LoadingBar get(Object key) {
        return POOL.get(System.identityHashCode(key));
    }


    static void remove(LoadingBar instance) {
        POOL.removeAt(POOL.indexOfValue(instance));
    }
}
