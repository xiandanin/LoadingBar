package com.dyhdyh.loadingbar.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dyhdyh.loadingbar.example.factory.CustomLoadingFactory;
import com.dyhdyh.widget.loading.bar.LoadingBar;
import com.dyhdyh.widget.loading.bar.OnLoadingBarListener;

/**
 * LoadingBar的例子
 * author  dengyuhan
 * created 2017/4/16 11:38
 */
public class LoadingBarActivity extends AppCompatActivity {
    private View mParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingbar);
        mParent = findViewById(R.id.loading_container);
    }


    public void clickLoading(View v) {
        LoadingBar.make(mParent).show();
    }


    public void clickCustomLoading(View v) {
        LoadingBar.make(mParent,new CustomLoadingFactory())
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoadingBarActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        }).setOnLoadingBarListener(new OnLoadingBarListener() {
            @Override
            public void onCancel(View parent) {
                Toast.makeText(LoadingBarActivity.this, "Loading取消了", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }


    public void clickCancelLoading(View v) {
        LoadingBar.cancel(mParent);
    }


    public void clickListLoading(View v) {
        startActivity(new Intent(this, ListLoadingActivity.class));
    }

}
