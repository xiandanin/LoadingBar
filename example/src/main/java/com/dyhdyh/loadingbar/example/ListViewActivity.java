package com.dyhdyh.loadingbar.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dyhdyh.widget.loadingbar.LoadingBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟列表的加载
 * author  dengyuhan
 * created 2016/7/27 18:29
 */
public class ListViewActivity extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        lv = (ListView) this.findViewById(R.id.lv);

        final View rootLayout = findViewById(R.id.root);
        LoadingBar.show(rootLayout);
        lv.postDelayed(new Runnable() {
            @Override
            public void run() {
                lv.setAdapter(new ArrayAdapter<String>(ListViewActivity.this, android.R.layout.simple_list_item_1, getTestData()));
                LoadingBar.cancel(rootLayout);
            }
        }, 2000);
    }

    private List<String> getTestData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            data.add("Item " + i);
        }
        return data;
    }
}
