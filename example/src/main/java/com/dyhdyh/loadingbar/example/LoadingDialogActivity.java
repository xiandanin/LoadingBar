package com.dyhdyh.loadingbar.example;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.dyhdyh.loadingbar.example.factory.CustomDialogFactory;
import com.dyhdyh.widget.loading.dialog.LoadingDialog;

/**
 * LoadingDialog的例子
 * author  dengyuhan
 * created 2017/4/16 04:41
 */
public class LoadingDialogActivity extends BaseActivity {
    private EditText edMessage;
    private CheckBox cbCancelable;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_dialog);
        edMessage = (EditText) this.findViewById(R.id.ed_message);
        cbCancelable = (CheckBox) this.findViewById(R.id.cb_cancelable);
    }

    public void clickDefault(View v) {
        LoadingDialog.make(this).show();


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadingDialog.cancel();
            }
        }, 2000);
    }

    public void clickCustom(View v) {
        Dialog dialog = LoadingDialog.make(this, new CustomDialogFactory())
                .setMessage(edMessage.getText())//提示消息
                .setCancelable(cbCancelable.isChecked())
                .create();
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(LoadingDialogActivity.this, "Dialog取消了", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadingDialog.cancel();
            }
        }, 2000);
    }


}
