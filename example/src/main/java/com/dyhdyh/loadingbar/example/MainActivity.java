package com.dyhdyh.loadingbar.example;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dyhdyh.widget.loading.LoadingConfig;
import com.dyhdyh.widget.loading.factory.DialogFactory;
import com.dyhdyh.widget.loading.factory.LoadingFactory;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void clickLoadingBar(View v) {
        startActivity(new Intent(this, LoadingBarActivity.class));
    }


    public void clickLoadingDialog(View v) {
        startActivity(new Intent(this, LoadingDialogActivity.class));
    }


    public void clickApplyGlobalConfig(View v) {
        LoadingConfig.setFactory(new LoadingFactory() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_global_loading, parent, false);
            }
        }, new DialogFactory() {
            @Override
            public Dialog onCreateDialog(Context context) {
                Dialog dialog = new Dialog(context, R.style.Dialog);
                dialog.setContentView(R.layout.layout_global_loading);
                dialog.setCancelable(false);
                return dialog;
            }

            @Override
            public void setMessage(Dialog dialog, CharSequence message) {
                TextView tv = (TextView) dialog.findViewById(R.id.tv_message);
                if (tv != null) {
                    tv.setText(message);
                }
            }

            @Override
            public int getAnimateStyleId() {
                return R.style.Dialog_Scale_Animation;
            }
        });
        Toast.makeText(this, "自定义Loading样式已经应用全局", Toast.LENGTH_SHORT).show();
    }


    public void clickResetGlobalConfig(View v) {
        LoadingConfig.defaultFactory();
        Toast.makeText(this, "Loading还原到默认样式", Toast.LENGTH_SHORT).show();
    }


}
