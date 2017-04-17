package com.dyhdyh.widget.loading.factory;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;

import com.dyhdyh.widget.loading.R;

/**
 * author  dengyuhan
 * created 2017/4/16 04:08
 */
public class MaterialDialogFactory implements DialogFactory<ProgressDialog> {

    @Override
    public ProgressDialog onCreateDialog(Context context) {
        ProgressDialog dialog;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog = new ProgressDialog(context, R.style.Dialog_AppCompat_Loading);
        } else {
            dialog = new ProgressDialog(context);
            dialog.setProgressStyle(android.support.v7.appcompat.R.style.Widget_AppCompat_ProgressBar);
        }
        dialog.setMessage(context.getText(R.string.loading_default_message));
        dialog.setCancelable(false);
        return dialog;
    }

    @Override
    public void setMessage(ProgressDialog dialog, CharSequence message) {
        dialog.setMessage(message);
    }

    @Override
    public int getAnimateStyleId() {
        return 0;
    }
}
