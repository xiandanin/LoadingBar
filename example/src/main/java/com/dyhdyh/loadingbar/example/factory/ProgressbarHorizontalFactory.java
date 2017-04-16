package com.dyhdyh.loadingbar.example.factory;

import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dyhdyh.loadingbar.example.R;
import com.dyhdyh.widget.loading.factory.LoadingFactory;

/**
 * 自定义样式LoadingBar的例子
 * author  dengyuhan
 * created 2017/4/16 05:13
 */
public class ProgressbarHorizontalFactory implements LoadingFactory {
    private int duration;

    public ProgressbarHorizontalFactory(int duration) {
        this.duration = duration;
    }

    @Override
    public View onCreateView(ViewGroup parent) {
        View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_progressbar_horizontal, parent, false);
        ProgressBar pb = (ProgressBar) loadingView.findViewById(R.id.pb);
        //模拟一下
        ObjectAnimator.ofInt(pb, "progress", 0, 100).setDuration(duration).start();
        return loadingView;
    }
}
