package com.dyhdyh.widget.loadingbar2.factory;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dyhdyh.widget.loadingbar2.R;

/**
 * 创建Material Loading样式Dialog的工厂
 *
 * @author dengyuhan
 * created 2019/3/14 15:52
 */
public class MaterialDialogFactory implements LoadingFactory<Context, Dialog> {
    private TextView mMessageView;

    @Override
    public Dialog onCreate(Context params) {
        final View view = LayoutInflater.from(params).inflate(R.layout.dialog_material_loading, null);
        mMessageView = view.findViewById(android.R.id.message);
        return new AlertDialog.Builder(params)
                .setView(view)
                .setCancelable(false)
                .create();
    }

    @Override
    public void updateStatus(@Nullable Object[] extras) {
        if (extras != null && extras.length > 0 && extras[0] instanceof CharSequence) {
            mMessageView.setVisibility(View.VISIBLE);
            mMessageView.setText((CharSequence) extras[0]);
        } else {
            mMessageView.setVisibility(View.GONE);
        }
    }

}
