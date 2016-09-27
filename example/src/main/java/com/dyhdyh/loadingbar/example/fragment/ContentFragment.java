package com.dyhdyh.loadingbar.example.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dyhdyh.loadingbar.example.R;
import com.dyhdyh.widget.loadingbar.LoadingBar;

import java.util.Random;

/**
 * author  dengyuhan
 * created 2016/8/16 9:44
 */
public class ContentFragment extends Fragment {
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        View layout = inflater.inflate(R.layout.fragment_content, null);
        TextView tv = (TextView) layout.findViewById(R.id.tv);
        tv.setText(title);
        Random random = new Random();
        layout.setBackgroundColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        request();
    }

    /**
     * 模拟请求
     */
    public void request() {
        final View root = getView();
        LoadingBar.show(root);
        int delayMillis = new Random().nextInt(3000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadingBar.cancel(root);
            }
        }, delayMillis);
    }
}
