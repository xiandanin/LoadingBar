package com.dyhdyh.widget.loading.dialog;

import android.app.Dialog;
import android.content.Context;

import com.dyhdyh.widget.loading.LoadingConfig;
import com.dyhdyh.widget.loading.factory.DialogFactory;

/**
 * author  dengyuhan
 * created 2017/4/16 03:59
 */
public class LoadingDialog implements ILoadingDialog {
    private static LoadingDialog mLoadingDialog;

    private Dialog mDialog;
    private DialogFactory mFactory;

    public LoadingDialog(Context context, DialogFactory factory) {
        this.mDialog = factory.onCreateDialog(context);
        this.mFactory = factory;
        int animateStyleId = this.mFactory.getAnimateStyleId();
        if (animateStyleId > 0) {
            this.mDialog.getWindow().setWindowAnimations(animateStyleId);
        }
    }

    @Override
    public void show() {
        mDialog.show();
    }

    public void cancelDialog() {
        mDialog.cancel();
    }


    @Override
    public Dialog create() {
        return mDialog;
    }

    @Override
    public ILoadingDialog setCancelable(boolean flag) {
        mDialog.setCancelable(flag);
        return this;
    }

    @Override
    public ILoadingDialog setMessage(CharSequence message) {
        mFactory.setMessage(mDialog, message);
        return this;
    }


    public static LoadingDialog make(Context context) {
        return make(context, LoadingConfig.getDialogFactory());
    }

    public static LoadingDialog make(Context context, DialogFactory factory) {
        if (mLoadingDialog != null) {
            mLoadingDialog.cancel();
        }
        mLoadingDialog = new LoadingDialog(context, factory);
        return mLoadingDialog;
    }


    public static void cancel() {
        mLoadingDialog.cancelDialog();
    }

}
