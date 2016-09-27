package com.dyhdyh.loadingbar.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dyhdyh.widget.loadingbar.LoadingBar;

public class MainActivity extends AppCompatActivity {
    View mParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mParent = findViewById(R.id.content);
    }

    /**
     * 显示默认样式Loading
     *
     * @param v
     */
    public void showLoading(View v) {
        LoadingBar.show(mParent);
    }

    /**
     * 显示自定义样式Loading
     *
     * @param v
     */
    public void showCustomLoading(View v) {
        LoadingBar.show(mParent, View.inflate(this, R.layout.custom_loading, null), null);
    }

    /**
     * 带有点击事件Loading
     *
     * @param v
     */
    public void showClickLoading(View v) {
        LoadingBar.show(mParent, View.inflate(this, R.layout.custom_error, null), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这里处理点击LoadingBar后的要做的操作
                Toast.makeText(MainActivity.this, "重新加载", Toast.LENGTH_SHORT).show();
                showLoading(v);
            }
        });
    }

    /**
     * 隐藏单个Loading
     *
     * @param v
     */
    public void hideLoading(View v) {
        LoadingBar.cancel(mParent);
    }

    /**
     * 取消所有Loading
     *
     */
    public void cancelAll() {
        LoadingBar.cancelAll();
    }

    /**
     * 多页面显示Loading
     *
     * @param v
     */
    public void multiLoading(View v) {
        startActivity(new Intent(this, MutiFragmentActivity.class));
    }


    /**
     * 显示列表的Loading
     *
     * @param v
     */
    public void listLoading(View v) {
        startActivity(new Intent(this, ListViewActivity.class));
    }

}
