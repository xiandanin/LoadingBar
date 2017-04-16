package com.dyhdyh.loadingbar.example.factory;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.dyhdyh.loadingbar.example.R;
import com.dyhdyh.widget.loading.factory.DialogFactory;

/**
 * 自定义样式dialog的例子
 * author  dengyuhan
 * created 2017/4/16 05:13
 */
public class CustomDialogFactory implements DialogFactory {
    @Override
    public Dialog onCreateDialog(Context context) {
        Dialog dialog = new Dialog(context, R.style.Dialog);
        dialog.setContentView(R.layout.layout_custom);
        return dialog;
    }

    @Override
    public void setMessage(Dialog dialog, CharSequence message) {
        TextView tv= (TextView) dialog.findViewById(R.id.tv_message);
        if (tv!=null){
            tv.setText(message);
        }
    }

    @Override
    public int getAnimateStyleId() {
        return R.style.Dialog_Alpha_Animation;
    }


}
