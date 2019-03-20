package com.dyhdyh.loadingbar.example;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dyhdyh.widget.loadingbar2.LoadingBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    View loadingContainer;
    TextView tv_pool_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingContainer = findViewById(R.id.layout_loading_container);
        tv_pool_count = findViewById(R.id.tv_pool_count);

        startRefreshPoolCountTimer();
    }

    public void clickShowView(View view) {
        LoadingBar.view(loadingContainer).show();
    }

    public void clickCancelView(View view) {
        LoadingBar.view(loadingContainer).cancel();
    }

    public void clickShowViewExtras(View view) {
        final ValueAnimator animator = ValueAnimator.ofInt(0, 100).setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final int value = (int) animation.getAnimatedValue();

                //显示view 并携带参数
                LoadingBar.view(loadingContainer).extras(new Object[]{value + "%"}).show();
            }
        });
        animator.addListener(new SimpleAnimatorListener() {

            @Override
            public void onAnimationEnd(Animator animation) {
                //取消view
                LoadingBar.view(loadingContainer).cancel();
            }
        });
        animator.start();
    }

    public void clickCustomView(View view) {
        LoadingBar.view(loadingContainer)
                //.setFactory()
                .setFactoryFromView(view)
                .setFactoryFromResource(R.layout.layout_custom)
                .show();
    }

    public void clickShowDialog(View view) {
        //显示dialog
        LoadingBar.dialog(MainActivity.this).show();
        final ValueAnimator animator = ValueAnimator.ofInt(0, 100).setDuration(2000);
        animator.addListener(new SimpleAnimatorListener() {

            @Override
            public void onAnimationEnd(Animator animation) {
                //取消dialog
                LoadingBar.dialog(MainActivity.this).cancel();
            }
        });
        animator.start();
    }

    public void clickShowDialogExtras(View view) {
        final ValueAnimator animator = ValueAnimator.ofInt(0, 100).setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final int value = (int) animation.getAnimatedValue();

                //显示dialog 并携带参数
                LoadingBar.dialog(MainActivity.this).extras(new Object[]{value + "%"}).show();
            }
        });
        animator.addListener(new SimpleAnimatorListener() {

            @Override
            public void onAnimationEnd(Animator animation) {
                //取消dialog
                LoadingBar.dialog(MainActivity.this).cancel();
            }
        });
        animator.start();
    }

    public void clickCustomDialog(View view) {
        LoadingBar.dialog(MainActivity.this).setFactoryFromResource(R.layout.layout_custom).show();
        final ValueAnimator animator = ValueAnimator.ofInt(0, 100).setDuration(2000);
        animator.addListener(new SimpleAnimatorListener() {

            @Override
            public void onAnimationEnd(Animator animation) {
                //取消dialog
                LoadingBar.dialog(MainActivity.this).cancel();
            }
        });
        animator.start();
    }

    public void clickRecycling(MenuItem item) {
        LoadingBar.release();
    }

    public void clickListLoading(MenuItem item) {
        startActivity(new Intent(this, ListLoadingActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * 刷新池的数量
     */
    private Timer mTimer;

    private void startRefreshPoolCountTimer() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_pool_count.setText(String.valueOf(LoadingBar.getPoolCount()));
                    }
                });
            }
        }, 0, 500);
    }

    @Override
    public void finish() {
        super.finish();
        mTimer.cancel();
    }
}
