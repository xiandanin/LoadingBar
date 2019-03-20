package com.dyhdyh.widget.loadingbar2.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dyhdyh.widget.loadingbar2.factory.LoadingFactory;

/**
 * Dialog的loading控制器
 *
 * @author dengyuhan
 * created 2019/3/14 11:22
 */
public class LoadingDialogController implements LoadingController<LoadingFactory<Context, Dialog>> {
    private Dialog mDialog;
    private Context mContext;

    private String mFactoryKey;

    public LoadingDialogController(Context context) {
        this.mContext = context;
    }

    @Override
    public void show(@NonNull LoadingFactory<Context, Dialog> factory, @Nullable Object[] extras) {
        final String factoryKey = factory.getKey();
        final boolean changed = mFactoryKey == null || !mFactoryKey.equals(factoryKey);
        Log.d("----------->", "dialog---show---->" + changed + "----->" + factoryKey);

        //如果样式发生变化 就先取消之前的
        if (changed && mDialog != null && mDialog.isShowing()) {
            mDialog.cancel();
        }

        //首次loading 或者样式发生变化 就重新创建
        if (mDialog == null || changed) {
            mDialog = factory.onCreate(mContext);
        }

        //更新Dialog状态
        factory.updateStatus(extras);

        if (isDialogValid() && !mDialog.isShowing()) {
            //没有在显示 就直接显示
            mDialog.show();
            this.mFactoryKey = factoryKey;
        }
    }

    @Override
    public void cancel() {
        if (isDialogValid() && mDialog.isShowing()) {
            mDialog.cancel();
        }
        mDialog = null;
        mFactoryKey = null;
    }

    @Override
    public boolean isCanRecycled() {
        return !isDialogValid();
    }


    protected boolean isDialogValid() {
        if (mDialog != null) {
            Context context = mDialog.getContext();
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            }
            //如果BaseContext还是ContextWrapper 再找一层
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            }
            return context instanceof Activity && !((Activity) context).isFinishing();
        }
        return false;
    }
}
