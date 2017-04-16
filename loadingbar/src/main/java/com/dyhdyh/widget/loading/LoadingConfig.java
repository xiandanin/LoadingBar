package com.dyhdyh.widget.loading;

import com.dyhdyh.widget.loading.factory.DialogFactory;
import com.dyhdyh.widget.loading.factory.LoadingFactory;
import com.dyhdyh.widget.loading.factory.MaterialDialogFactory;
import com.dyhdyh.widget.loading.factory.MaterialFactory;

/**
 * author  dengyuhan
 * created 2017/4/16 11:54
 */
public class LoadingConfig {
    private final static LoadingFactory DEFAULT_LOADING_FACTORY = new MaterialFactory();
    private final static DialogFactory DEFAULT_DIALOG_FACTORY = new MaterialDialogFactory();

    private static LoadingFactory mLoadingFactory = DEFAULT_LOADING_FACTORY;
    private static DialogFactory mDialogFactory = DEFAULT_DIALOG_FACTORY;

    /**
     * 全局配置
     * <p>在程序入口调用</p>
     * @param loadingFactory
     * @param dialogFactory
     */
    public static void setFactory(LoadingFactory loadingFactory,DialogFactory dialogFactory) {
        if (loadingFactory != null) {
            mLoadingFactory = loadingFactory;
        }
        if (dialogFactory != null) {
            mDialogFactory = dialogFactory;
        }
    }


    public static void defaultFactory() {
        setFactory(new MaterialFactory(),new MaterialDialogFactory());
    }


    public static LoadingFactory getLoadingFactory() {
        return mLoadingFactory;
    }

    public static DialogFactory getDialogFactory() {
        return mDialogFactory;
    }
}
