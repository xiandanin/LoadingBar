package com.dyhdyh.widget.loadingbar2.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;

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

    public LoadingDialogController(Context context) {
        this.mContext = context;
    }

    @Override
    public void show(LoadingFactory<Context, Dialog> factory, Object[] extras) {
        //首次loading需创建
        if (mDialog == null) {
            mDialog = factory.onCreate(mContext);
        }

        //更新Dialog状态
        factory.updateStatus(extras);

        if (!mDialog.isShowing()) {
            //没有在显示 就直接显示
            mDialog.show();
        }
    }

    @Override
    public void cancel() {
        if (mDialog.isShowing()) {
            mDialog.cancel();
        }
        mDialog = null;
    }


    protected boolean isValid() {
        if (mDialog != null) {
            Context context = mDialog.getContext();
            if (context instanceof ContextWrapper) {
                context = ((ContextWrapper) context).getBaseContext();
            }
            if (context instanceof Activity) {
                if (!((Activity) context).isFinishing()) {
                    return true;
                }
            }
        }
        return false;
    }
}
