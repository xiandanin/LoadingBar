package com.dyhdyh.loadingbar.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dyhdyh.widget.loadingbar.LoadingBar;

public class MainActivity extends AppCompatActivity {
    View frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout=findViewById(R.id.content);
    }

    /**
     * 显示默认样式Loading
     * @param v
     */
    public void showLoading(View v){
        LoadingBar.show(frameLayout);
    }

    /**
     * 显示自定义样式Loading
     * @param v
     */
    public void showCustomLoading(View v){
        LoadingBar.show(frameLayout,View.inflate(this,R.layout.custom_loading,null),null);
    }

    /**
     * 带有点击事件Loading
     * @param v
     */
    public void showClickLoading(View v){
        LoadingBar.show(frameLayout, View.inflate(this, R.layout.custom_error, null), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这里处理点击LoadingBar后的要做的操作
                showLoading(v);
            }
        });
    }

    /**
     * 隐藏Loading
     * @param v
     */
    public void hideLoading(View v){
        LoadingBar.hide();
    }
}
